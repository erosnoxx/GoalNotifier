package com.erosnox.seeurun.domain.utils;

import java.util.regex.Pattern;

public class EntityUtils {
    private static final String passwordRegexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+" +
            "\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
    private static final String emailRegexp = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
    private static final String usernameRegexp = "^[a-zA-Z0-9_.-]{3,15}$";

    public static boolean isValidEmail(String email) {
        return EntityUtils.pattern(emailRegexp).matcher(email).matches();
    }

    public static boolean isValidUsername(String username) {
        return EntityUtils.pattern(usernameRegexp).matcher(username).matches();
    }

    public static boolean isValidPassword(String password) {
        return EntityUtils.pattern(passwordRegexp).matcher(password).matches();
    }

    private static Pattern pattern(String regexp) {
        return Pattern.compile(regexp);
    }
}
