package com.clwater.smsfroward.email;

import android.util.Log;

import com.clwater.smsfroward.MainActivity;


/**
 * Create by clwater on 2019/4/20.
 */
public class SendMailUtil {
    private static final String HOST = "smtp.163.com";
    private static final String PORT = "25";
    private static final String FROM_ADD = "@163.com";
    private static final String FROM_PSW = "";

    public static void send(String text){
        for (String toAdd: MainActivity.list) {
            Log.d("gzb", toAdd + " " + text);
            final MailInfo mailInfo = creatMail(toAdd, text);
            final MailSender sms = new MailSender();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sms.sendTextMail(mailInfo);
                }
            }).start();
        }
    }

    private static MailInfo creatMail(String address, String text) {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.mailServerHost = HOST;
        mailInfo.mailServerPort = PORT;
        mailInfo.validate = true;
        mailInfo.userName = FROM_ADD;
        mailInfo.password = FROM_PSW;
        mailInfo.fromAddress = FROM_ADD;
        mailInfo.toAddress = address;
        mailInfo.subject = "SMS_Forward";
        mailInfo.content = text;
        return mailInfo;
    }
}
