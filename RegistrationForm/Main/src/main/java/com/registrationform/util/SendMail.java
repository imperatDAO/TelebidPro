package com.registrationform.util;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    public static void send(String toMail){
        final String myMail = "koce1tst@gmail.com";
        final String myPass = "$$97Kocetoto97";

        Properties props = new Properties();
        //props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable","true");
        //props.put("mail.smtp.host","smtp.gmail.com");
        //props.put("mail.smtp.port","587");

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        try {
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myMail, myPass);
                }
            });
            //MimeMessage msg = new MimeMessage(Session.getDefaultInstance(props));
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(myMail));
            msg.addRecipient(Message.RecipientType.TO,new InternetAddress(toMail));
            msg.setSubject("Email Verification");
            msg.setText("Please click the link to verify your email "
                        + "http://localhost:8080/VerifyEmail?key1=" + toMail
                        + "tokenTry=random123");
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
