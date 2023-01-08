package com.andrey.spring.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {
    public static void main(String[] args) {
        Locale locale = new Locale("ru","RU");
        var translations = ResourceBundle.getBundle("translations");
    }
}
