package com.management.clinic.utils;

import com.management.clinic.constants.MessageConstant;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
    public static void showMessage(HttpServletRequest request) {
        if (request.getParameter("message") != null) {
            String messageParam = request.getParameter("message");
            String message = "";
            String alert = "";
            if (messageParam.equalsIgnoreCase("LOGIN_FAILED")) {
                message = MessageConstant.LOGIN_FAILED;
                alert = "danger";
            }
            request.setAttribute("messageParam", message);
            request.setAttribute("alert", alert);
        }
    }
}
