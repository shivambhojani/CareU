package com.group6.careu.setting;

import lombok.Data;

/**
 * Created by Bijitashya on 03, 2022
 */
@Data
public class EmailSetting {

    private final String MAIL_HOST = "smtp.gmail.com";
    private final int MAIL_PORT = 587;
    private final String MAIL_USERNAME = "careumail@gmail.com";
    private final String MAIL_PASSWORD = "llyzgryceuvhssuz";
    private final String SMTP_AUTH = "true";
    private final String SMTP_SECURED = "true";
    private final String MAIL_FROM = "CareU";
    private final String MAIL_SENDER_NAME = "CareU Team";
    private final String MAIL_FORGOT_PASSWORD_SUBJECT = "Reset Password";
    private final String MAIL_FORGOT_PASSWORD_TEMPLATE_BODY = "<p>Dear [[user]],</p>\n" +
            "<p>&nbsp;</p>\n" +
            "<pre>Click on the link below to reset your password<br /><br /><strong><a href=\"[[url]]\">VERIFY<br /></a><br /><br />Thanks,<br />CareU Team</strong></pre>\n" +
            "<p>&nbsp;</p>\n" +
            "<p>&nbsp;</p>";


}
