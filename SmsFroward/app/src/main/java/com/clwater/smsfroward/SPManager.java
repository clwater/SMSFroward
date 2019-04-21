package com.clwater.smsfroward;

import android.content.Context;

public class SPManager {
    private static final String EMAILS = "emails";

    public static void setEMAILS(Context context, String email){
        SPUtils.getInstance(context).putString(EMAILS, email);
    }

    public static String getEMAILS(Context context){
        return SPUtils.getInstance(context).getString(EMAILS);
    }

}
