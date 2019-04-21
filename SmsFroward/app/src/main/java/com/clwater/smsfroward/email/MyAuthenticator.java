package com.clwater.smsfroward.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Create by clwater on 2019/4/20.
 */
public class MyAuthenticator extends Authenticator {
    String userName = null;
    String password = null;
    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}