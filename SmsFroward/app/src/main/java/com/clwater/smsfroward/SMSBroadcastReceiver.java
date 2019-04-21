package com.clwater.smsfroward;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

import com.clwater.smsfroward.email.SendMailUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by clwater on 2019/4/20.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {
    public static String sender = "", time = "", content = "";
    public SMSBroadcastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu : pdus) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long date = smsMessage.getTimestampMillis();
            Date timeDate = new Date(date);

            if (sender.equals(smsMessage.getDisplayOriginatingAddress()) && time.equals(simpleDateFormat.format(timeDate))){
                content += smsMessage.getMessageBody();
            }else {
                sender = smsMessage.getDisplayOriginatingAddress();
                content = smsMessage.getMessageBody();
                time = simpleDateFormat.format(timeDate);
            }
        }
        String text = "Sender: " + sender + "\n Time: " + time + "\n" + content;
        SendMailUtil.send(text);

    }
}