package com.dennyschuldt.panic.utils;

import android.text.TextUtils;

/**
 * Created by denny on 2/7/16.
 */
public class Utils {

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
