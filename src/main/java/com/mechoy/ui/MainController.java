package com.mechoy.ui;

import com.mechoy.core.service.MenuService;
import com.mechoy.core.service.WsService;
import com.mechoy.util.LogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.java_websocket.enums.ReadyState;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public TextArea logTextArea;

    @FXML
    private TextArea reqTextArea;

    @FXML
    private TextArea resTextArea;

    @FXML
    private TextField targetAddressTextField;

    @FXML
    private Ellipse connStatus;

    static WsService wsService;

    static MenuService menuService;

    @FXML
    protected void onOpenConnButtonClick() {
        String targetAddress = targetAddressTextField.getText();
        if (targetAddress.length() > 5) {
            if (!targetAddress.substring(0, 5).equals("ws://") && !targetAddress.substring(0, 5).equals("wss://")) {
                logTextArea.appendText(LogUtil.ADDRESS_ERROR);
                return;
            }
            wsService = WsService.initConn(targetAddressTextField.getText(), logTextArea, resTextArea, connStatus);
            wsService.openConnect();
            if (connStatus.getFill() == Color.GREEN) {
                targetAddressTextField.setEditable(false);
                return;
            }
        }
        logTextArea.appendText(LogUtil.ADDRESS_ERROR);
    }

    @FXML
    protected void onCloseConnButtonClick() {
        if (wsService == null) {
            logTextArea.appendText(LogUtil.CONN_CLOSED);
            targetAddressTextField.setEditable(true);
            wsService = null;
            return;
        }
        wsService.closeConnect();
        if (wsService.getReadyState() == ReadyState.CLOSED) {
            targetAddressTextField.setEditable(true);
            wsService = null;
        }
    }

    // 发送请求,在未建立连接时可直接发送请求，但需要先建立连接
    @FXML
    protected void onSendButtonClick() {
        if (wsService != null) {
            if (wsService.getReadyState() != ReadyState.OPEN) {
                logTextArea.setText(logTextArea.getText() + LogUtil.CONN_NOT_ESTABLISHED);
//                logTextArea.appendText(LogUtil.CONN_NOT_ESTABLISHED);
                logTextArea.appendText(LogUtil.TRY_ESTABLISH);
                onOpenConnButtonClick();
            }
            if (wsService.getReadyState()==ReadyState.OPEN) {
                wsService.sendReq(reqTextArea.getText());
            }
        }else {
            onOpenConnButtonClick();
        }
    }

    // 关于
    @FXML
    protected void onMenuHelpAboutClick() throws IOException {
        menuService.menuHelpAbout();
    }

    // 日志清理
    @FXML
    protected void onMenuEditLogCleaningClick() {
        logTextArea.clear();
    }

    public void init(){
        menuService = new MenuService();
    }
}