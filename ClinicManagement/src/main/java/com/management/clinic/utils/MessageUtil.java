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
            if (messageParam.equalsIgnoreCase("USERNAME_EXISTED")) {
                message = MessageConstant.USERNAME_EXISTED;
                alert = "danger";
            }
            if (messageParam.equalsIgnoreCase("UPDATE_PROFILE_SUCCESS")) {
                message = MessageConstant.UPDATE_PROFILE_SUCCESS;
                alert = "success";
            }
            if (messageParam.equalsIgnoreCase("CHANGE_PASSWORD_SUCCESS")) {
                message = MessageConstant.CHANGE_PASSWORD_SUCCESS;
                alert = "success";
            }
            request.setAttribute("messageParam", message);
            request.setAttribute("alert", alert);
        }
    }
}
