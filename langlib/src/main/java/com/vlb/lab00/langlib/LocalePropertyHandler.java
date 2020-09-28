package com.vlb.lab00.langlib;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;

public class LocalePropertyHandler {
    private static final String BUNDLE_BASE_NAME = "lang";
    private Locale locale = new Locale("en");

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ResourceBundle getBundle() {
        return getResourceBundle(locale);
    }

    // Загрузка бандлов с учётом выбранного языка (локали)
    private ResourceBundle getResourceBundle(Locale locale) {
        return PropertyResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
    }
}