package com.mechoy.util;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/8/31
 * Description
 */
public class LogUtil {

    public static String PREF = ">>> ";

    public static String SUFFIX = "\n";
    public static String CONN_SUCCESS = PREF + "连接成功..." + SUFFIX;

    public static String CONN_FAILED = PREF + "连接失败..." + SUFFIX;

    public static String CONN_INTERRUPTION = PREF + "连接中断..." + SUFFIX;

    public static String CONN_EXCEPTION = PREF + "连接异常..." + SUFFIX;
    public static String CONN_CLOSE = PREF + "连接关闭..." + SUFFIX;

    public static String CONN_NOT_ESTABLISHED = PREF + "未建立连接..." + SUFFIX;

    public static String TRY_ESTABLISH = PREF + "尝试建立连接..." + SUFFIX;

    public static String CONN_CLOSED = PREF + "连接已关闭..." + SUFFIX;

    public static String ESTABLISH_CONN = PREF + "建立连接: ";

    public static String ADDRESS_ERROR = PREF + "目标地址错误..." + SUFFIX;

    public static String SEND_REQ = ">>> 发送请求";

    public static String RE_SEND = ">>> 重新发送消息..." + SUFFIX;

    public static String RECEIVE_NULL = ">>> 接收响应: 响应内容为空" + SUFFIX;

    public static String RECEIVE = ">>> 接收响应: ";
}
