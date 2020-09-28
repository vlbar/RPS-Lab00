package com.vlb.lab00.langlib;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalePropertyHandlerTest {

    private final static String[] SOURCE_KEYS = new String[] {
            "plan.name.basic",
            "plan.name.premium",
            "plan.name.enterprise",
            "plan.storage","plan.ram",
            "plan.badwidth",
            "plan.websites",
            "plan.domian",
            "plan.support",
            "plan.mount",
            "plan.price"};

    @Test
    public void testEnglishLang()
    {
        boolean actual = true;
        LocalePropertyHandler propertyHandler = new LocalePropertyHandler();
        propertyHandler.setLocale(new Locale("en", "US"));
        ResourceBundle bundle = propertyHandler.getBundle();

        for (int i = 0; i < SOURCE_KEYS.length; i++)
        {
            if(!isCharBettween(bundle.getString(SOURCE_KEYS[i]).charAt(0), 'a', 'z' )) actual = false;
        }
        assertTrue(actual);
    }

    @Test
    public void testRussianLang()
    {
        boolean actual = true;
        LocalePropertyHandler propertyHandler = new LocalePropertyHandler();
        propertyHandler.setLocale(new Locale("ru", "RU"));
        ResourceBundle bundle = propertyHandler.getBundle();

        for (int i = 0; i < SOURCE_KEYS.length; i++)
        {
            if(!isCharBettween(bundle.getString(SOURCE_KEYS[i]).charAt(0), 'а', 'я' )) actual = false;
        }
        assertTrue(actual);
    }

    //проверяем принадлежит ли символ
    private boolean isCharBettween(char symbol, char from, char to)
    {
        if(Character.isDigit(symbol)) return true; //ну так как то
        char otherFrom = (Character.isLowerCase(from)) ? Character.toUpperCase(from) : Character.toLowerCase(from);
        char otherTo = (Character.isLowerCase(to)) ? Character.toUpperCase(to) : Character.toLowerCase(to);
        return (symbol >= from && symbol <= to) || (symbol >= otherFrom && symbol <= otherTo);
    }
}