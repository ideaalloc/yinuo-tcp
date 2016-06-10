package com.yinuo.socket;

import com.alibaba.fastjson.JSON;
import com.yinuo.socket.cache.SessionCache;
import com.yinuo.socket.config.VerticleConfig;
import com.yinuo.socket.constant.Constants;
import com.yinuo.socket.exception.MessageFormatException;
import com.yinuo.socket.exception.NoSuchApiException;
import com.yinuo.socket.factory.ServiceFactory;
import com.yinuo.socket.model.*;
import com.yinuo.socket.repository.W01MessageRepository;
import com.yinuo.socket.util.ExceptionUtil;
import com.yinuo.socket.util.MessageUtil;
import com.yinuo.socket.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.yinuo.socket.constant.Constants.ENCODING;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-31
 */
public class TcpServerVerticle extends AbstractVerticle {
    static final Logger LOGGER = LoggerFactory.getLogger(TcpServerVerticle.class);
    static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    static final SessionCache sessionCache = SessionCache.getInstance();
    static final VerticleConfig verticleConfig = VerticleConfig.getInstance();
    W01MessageRepository w01MessageRepository;

    public static void main(String[] args) {
        Runner.run(TcpServerVerticle.class);
    }

    @Override
    public void start() throws Exception {
        w01MessageRepository = new W01MessageRepository(sessionCache.getJDBCClient(vertx));

        for (int pos = 0; pos < verticleConfig.getInstanceNum(); pos++) {
            createInstance(pos + 1);
        }

    }

    private void createInstance(int instanceId) {
        vertx.createNetServer().connectHandler(netSocket -> netSocket.handler(inBuffer -> {
            final String writeHandlerID = netSocket.writeHandlerID();

            netSocket.closeHandler(v -> {
                sessionCache.invalidateActor(writeHandlerID);
                LOGGER.info("The socket has been closed");
            }).exceptionHandler(v -> {
                LOGGER.error("The socket error happened, error message {}", ExceptionUtil.getRootCauseMessage(v));
            });

            final String message = inBuffer.toString(ENCODING);
            LOGGER.info("RECV MSG: {}", message);

            Buffer outBuffer = Buffer.buffer();

            final List<String> messageGroups = MessageUtil.parseMessage(message);

            if (messageGroups != null && !messageGroups.isEmpty()) {
                messageGroups.forEach(m -> {
                    LOGGER.info("Processing message: {}", m);
                    final Message receivedMessage;
                    try {
                        receivedMessage = serviceFactory.getAssembler().handle(m);
                    } catch (MessageFormatException e) {
                        LOGGER.error("Message format error: ", e);
                        return;
                    }
                    if (receivedMessage == null) {
                        return;
                    }

                    final String imei = receivedMessage.getImei();

                    int responseCode = 0;
                    switch (receivedMessage.getEndPointType()) {
                        case 'T':
                            // register terminal device with imei
                            if (receivedMessage.getInstruction() == 1) {
                                LOGGER.info("intent to register with imei: {}", imei);
                                sessionCache.registerActor(imei, writeHandlerID);
                                LOGGER.info("imei {} registered successfully", imei);
                                responseCode = 0;
                            } else if (receivedMessage.getInstruction() == 2) {
                                final String actorID = sessionCache.retrieveActor(imei);
                                if (StringUtils.isBlank(actorID)) {
                                    LOGGER.error("imei {} not registered", imei);
                                    responseCode = -1;
                                } else {
                                    LOGGER.info("imei {} registered", imei);
                                    responseCode = 0;
                                }
                            }

                            if (responseCode == 0) {
                                try {
                                    responseCode = serviceFactory.getMessageService().invokeRemoteApi((TerminalMessage) receivedMessage);
                                } catch (IOException e) {
                                    LOGGER.error("invoke remote api error", e);
                                    responseCode = -2;
                                } catch (NoSuchApiException e) {
                                    LOGGER.info("no such api");
                                    responseCode += 0;
                                }
                            }

                            w01MessageRepository.insert((BaseMessage) receivedMessage, responseCode);
                            if (responseCode == 0) {
                                writeMessage(netSocket, receivedMessage.transform().getPlainText());
                            }
                            break;
                        case 'S':
                            ServerMessage serverMessage = (ServerMessage) receivedMessage;

                            final String actorID = sessionCache.retrieveActor(imei);

                            if (StringUtils.isBlank(actorID)) {
                                LOGGER.error("imei {} not registered", imei);
                                w01MessageRepository.insert(serverMessage, -1);
                                responseToApiServer(-1, String.format("imei %s not registered", imei), m, (ServerMessage) receivedMessage, netSocket);
                                return;
                            }
                            w01MessageRepository.insert(serverMessage, 0);
                            vertx.eventBus().publish(actorID, outBuffer.appendString(receivedMessage.getPlainText()));
                            responseToApiServer(0, "success", m, (ServerMessage) receivedMessage, netSocket);
                            break;
                        default:
                            throw new IllegalArgumentException("incorrect endpoint type");
                    }
                });
            }
        })).listen(Constants.PORT, res -> {
            if (res.succeeded()) {
                LOGGER.info("\nInstance {} started successfully.\n\n", instanceId);
            } else {
                LOGGER.info("Failed to bind!");
            }
        });
    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("close JDBC connection");
        sessionCache.getJDBCClient(vertx).close();
    }

    private void responseToApiServer(int status, String remarks, String plainMessage, ServerMessage serverMessage, NetSocket netSocket) {
        final MessageResponseToApiServer messageResponseToApiServer = new MessageResponseToApiServer(plainMessage, serverMessage, status, remarks);
        final String jsonString = JSON.toJSONString(messageResponseToApiServer);
        writeMessage(netSocket, jsonString);
    }

    private void writeMessage(NetSocket netSocket, String writeMessage) {
        final Buffer outBuffer = Buffer.buffer();
        outBuffer.appendString(writeMessage, ENCODING);
        netSocket.write(outBuffer);
    }

}
