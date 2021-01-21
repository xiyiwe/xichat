package com.xiyiwe.xichat.utils.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    /**
     * Function Name               stackTraceToString
     *
     * @param e
     * @return
     * @description 将异常堆栈转成string
     * Modify History:              Date             Programmer       Notes
     * ---------        ---------------  ---------
     * 2017年5月10日 下午4:42:41           舒小龙                     Initial
     * *********************************************************************
     */
    public static String stackTraceToString(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

}
