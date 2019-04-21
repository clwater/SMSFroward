package com.clwater.smsfroward.email;

import java.util.Properties;

/**
 * Create by clwater on 2019/4/20.
 */
public class MailInfo {

    public String mailServerHost;// 发送邮件的服务器的IP
    public String mailServerPort;// 发送邮件的服务器的端口
    public String fromAddress;// 邮件发送者的地址
    public String toAddress;   // 邮件接收者的地址
    public String userName;// 登陆邮件发送服务器的用户名
    public String password;// 登陆邮件发送服务器的密码
    public boolean validate = true;// 是否需要身份验证
    public String subject;// 邮件主题
    public String content;// 邮件的文本内容
    public String[] attachFileNames;// 邮件附件的文件名

    /**
     * 获得邮件会话属性
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }
}
