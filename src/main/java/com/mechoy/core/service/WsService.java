package com.mechoy.core.service;

import com.mechoy.core.WsClient;
import com.mechoy.util.LogUtil;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.java_websocket.enums.ReadyState;

import java.net.URI;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/8/30
 * Description
 */
public class WsService extends WsClient {

    private static WsService wsService;


    public WsService(String uri, TextArea logTextArea, TextArea resTextArea, Ellipse connStatus) {
        super(uri, logTextArea, resTextArea, connStatus);
    }

    public static WsService initConn(String uri, TextArea logTextArea, TextArea resTextArea, Ellipse connStatus) {
        wsService = new WsService(uri, logTextArea, resTextArea, connStatus);
        return wsService;
    }

    public void openConnect() {
        for (int i = 0; i < 10; i++) {
            // 留给建立连接的时间
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ReadyState readyState = wsService.getReadyState();
            if (readyState == ReadyState.OPEN) {
                super.getConnStatus().setFill(Color.GREEN);
                logTextArea.appendText(LogUtil.ESTABLISH_CONN + uri + LogUtil.SUFFIX);
                logTextArea.appendText(LogUtil.CONN_SUCCESS);
                return;
            }
            try {
                wsService.connect();
            } catch (Exception e) {
                logTextArea.appendText(LogUtil.CONN_FAILED);
                connStatus.setFill(Color.RED);
                return;
            }
        }
    }

    public void closeConnect() {
        ReadyState readyState = wsService.getReadyState();
        if (readyState == ReadyState.CLOSING || readyState == ReadyState.CLOSED) {
            connStatus.setFill(Color.RED);
            logTextArea.appendText(LogUtil.CONN_CLOSED);
            return;
        } else if (readyState == ReadyState.OPEN) {
            wsService.close();
            for (int i = 0; i < 10; i++) {
                if (wsService.getReadyState() == ReadyState.CLOSED) {
                    connStatus.setFill(Color.RED);
                    super.getLogTextArea().appendText(LogUtil.CONN_CLOSE);
                    return;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void sendReq(String req) {
        if (wsService.getReadyState() == ReadyState.OPEN) {
            super.getLogTextArea().appendText(LogUtil.SEND_REQ + req + LogUtil.SUFFIX);
            wsService.send(req);
            resTextArea.clear();
        } else {
            logTextArea.appendText(LogUtil.CONN_EXCEPTION);
            wsService.connStatus.setFill(Color.RED);
        }
    }

    @Override
    public void onMessage(String message) {
        if (message == null || message.length() < 1) {
            super.getLogTextArea().appendText(LogUtil.RECEIVE_NULL);
        } else {
            super.getLogTextArea().appendText(LogUtil.RECEIVE + message + LogUtil.SUFFIX);
            super.getResTextArea().appendText(message);
        }
    }
}
