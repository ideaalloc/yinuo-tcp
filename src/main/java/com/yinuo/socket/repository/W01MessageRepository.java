package com.yinuo.socket.repository;

import com.yinuo.socket.model.BaseMessage;
import com.yinuo.socket.util.ExceptionUtil;
import com.yinuo.socket.util.IdUtil;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-05
 */
public class W01MessageRepository {
    static final Logger LOGGER = LoggerFactory.getLogger(W01MessageRepository.class);

    JDBCClient client;

    public W01MessageRepository(JDBCClient client) {
        this.client = client;
    }

    public void insert(BaseMessage message, int status) {
        client.getConnection(res -> {
            if (res.succeeded()) {
                SQLConnection connection = res.result();
                connection.setAutoCommit(false, res3 -> {
                    if (res3.succeeded()) {
                        String insert = "INSERT INTO MSG_W01_MESSAGES (ID, IDENTIFIER, MSG_TIME, IMEI, ENDPOINT_TYPE, INSTRUCTION, PARAMETERS, PLAIN, STATUS) VALUES (?,?,?,?,?,?,?,?,?)";
                        JsonArray params = new JsonArray().
                                add(IdUtil.generateId()).add(message.getIdentifier()).add(message.getTime())
                                .add(message.getImei()).add(String.valueOf(message.getEndPointType())).add(message.getInstruction())
                                .add(message.getParameters() == null ? null : message.getParameters().toString())
                                .add(message.getPlainText()).add(status);
                        LOGGER.info("params: {}", params.toString());
                        connection.updateWithParams(insert, params, res4 -> {
                            if (res4.succeeded()) {
                                UpdateResult updateResult = res4.result();
                                LOGGER.info("No. of rows inserted: " + updateResult.getUpdated());
                                connection.commit(res5 -> {
                                    if (res5.succeeded()) {
                                        LOGGER.info("commit success");
                                    } else {
                                        LOGGER.error("commit error");
                                    }
                                    connection.close();
                                });
                            } else {
                                LOGGER.error("insert error: {}", ExceptionUtil.getRootCauseMessage(res4.cause()));
                                connection.close();
                            }
                        });
                    } else {
                        LOGGER.error("error to set auto commit");
                        connection.close();
                    }
                });
            } else {
                LOGGER.error("Failed to get connection");
            }
        });
    }
}
