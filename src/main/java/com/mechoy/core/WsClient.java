package com.mechoy.core;

import com.mechoy.util.LogUtil;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Ellipse;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/8/30
 * Description
 */
public class WsClient extends WebSocketClient {

    public TextArea logTextArea;

    public TextArea resTextArea;

    public Ellipse connStatus;

    protected String uri;

    public WsClient(String uri, TextArea logTextArea, TextArea resTextArea, Ellipse connStatus) {
        super(URI.create(uri));
        this.logTextArea = logTextArea;
        this.resTextArea = resTextArea;
        this.connStatus = connStatus;
        this.uri = uri;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }

    public TextArea getLogTextArea() {
        return logTextArea;
    }

    public void setLogTextArea(TextArea logTextArea) {
        this.logTextArea = logTextArea;
    }

    public TextArea getResTextArea() {
        return resTextArea;
    }

    public void setResTextArea(TextArea resTextArea) {
        this.resTextArea = resTextArea;
    }

    public Ellipse getConnStatus() {
        return connStatus;
    }

    public void setConnStatus(Ellipse connStatus) {
        this.connStatus = connStatus;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
