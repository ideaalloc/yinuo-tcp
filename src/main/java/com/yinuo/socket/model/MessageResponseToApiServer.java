package com.yinuo.socket.model;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-04
 */
public class MessageResponseToApiServer implements Serializable {
    String echoMessage;
    ServerMessage echoMessageObject;
    int status;
    String remarks;

    public MessageResponseToApiServer() {
    }

    public MessageResponseToApiServer(String echoMessage, ServerMessage echoMessageObject, int status, String remarks) {
        this.echoMessage = echoMessage;
        this.echoMessageObject = echoMessageObject;
        this.status = status;
        this.remarks = remarks;
    }

    public String getEchoMessage() {
        return echoMessage;
    }

    public void setEchoMessage(String echoMessage) {
        this.echoMessage = echoMessage;
    }

    public ServerMessage getEchoMessageObject() {
        return echoMessageObject;
    }

    public void setEchoMessageObject(ServerMessage echoMessageObject) {
        this.echoMessageObject = echoMessageObject;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "MessageResponseToApiServer{" +
                "echoMessage='" + echoMessage + '\'' +
                ", echoMessageObject=" + echoMessageObject +
                ", status=" + status +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
