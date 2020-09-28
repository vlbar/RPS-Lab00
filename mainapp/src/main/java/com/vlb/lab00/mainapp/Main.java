package com.vlb.lab00.mainapp;

import com.vlb.lab00.proplib.PropertyHandler;
import com.vlb.lab00.langlib.LocalePropertyHandler;

import java.util.Locale;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Properties;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.io.UnsupportedEncodingException;

public class Main {
    public static final String DEFAULT_CONSOLE_ENCODING = "UTF-8";
    public static final String CONSOLE_ENCODING_PROPERTY = "consoleEncoding";

    private final Locale locale = new Locale("ru", "RU");
    private final LocalePropertyHandler localePropertyHandler = new LocalePropertyHandler();
    private final PropertyHandler propertyHandler = new PropertyHandler();

    private ResourceBundle langProperties;
    private ArrayList<Plan> serverPlans = new ArrayList<Plan>();

    public static void main(String[] args) {
        //создаём класс приложения
        Main main = new Main();
        //запускаем выполнение
        main.run();
    }

    private void run() {
        //устанавливаемый нужный нам язык
        localePropertyHandler.setLocale(locale);
        //получаем бандл
        langProperties = localePropertyHandler.getBundle();

        //добавляем планы хостинга
        serverPlans.add(getPlan("basic"));
        serverPlans.add(getPlan("premium"));
        serverPlans.add(getPlan("enterprise"));

        //выводим всю информацию о хостинге
        printHostingInfo();
    }

    public void printHostingInfo() {
        System.out.println(langProperties.getString("title.welcome"));
        System.out.println(langProperties.getString("title.plans") + ":");

        for (Plan plan : serverPlans) {
            //вывод информации о конкретном плане
            printPlanInfo(plan);
        }
    }

    public void printPlanInfo(Plan plan) {
        //установка форматирования валюты
        NumberFormat priceFormat = NumberFormat.getCurrencyInstance(locale);
        String support = plan.isIncludeSupport() ? "24/7" : "-";

        System.out.println();
        System.out.println(langProperties.getString("plan.name." + plan.getName()));
        System.out.println(langProperties.getString("plan.storage") + ": " + plan.getStorage() + " " + langProperties.getString("unit.gigabyte"));
        System.out.println(langProperties.getString("plan.ram") + ": " + plan.getRAM() + " " + langProperties.getString("unit.gigabyte"));
        System.out.println(langProperties.getString("plan.badwidth") + ": " + plan.getBadwidth() + " " + langProperties.getString("unit.terabyte"));
        System.out.println(langProperties.getString("plan.websites") + ": " + plan.getWebsiteCount());
        System.out.println(langProperties.getString("plan.domian") + ": " + plan.getDomianCount());
        System.out.println(langProperties.getString("plan.support") + ": " + support);
        System.out.println(langProperties.getString("plan.price") + ": " + priceFormat.format(plan.getPrice()) + " / " + langProperties.getString("plan.mount"));
    }

    private Plan getPlan(String name) {
        Properties planProperties = new Properties();
        propertyHandler.setPropertyName(name);

        try {
            planProperties = propertyHandler.getProperties();
        } catch (IOException e) {
            System.out.println("Server plan with name " + name + "not found! :c");
        }

        return new Plan(
                name,
                Float.parseFloat(planProperties.getProperty("plan.storage")),
                Float.parseFloat(planProperties.getProperty("plan.ram")),
                Float.parseFloat(planProperties.getProperty("plan.badwidth")),
                Integer.parseInt(planProperties.getProperty("plan.websites")),
                Integer.parseInt(planProperties.getProperty("plan.domian")),
                Boolean.parseBoolean(planProperties.getProperty("plan.support")),
                Double.parseDouble(planProperties.getProperty("plan.price." + locale.getCountry().toLowerCase()))
        );
    }

    private static void setConsoleEncoding() {
        //чтение системной переменной
        String consoleEncoding = System.getProperty(CONSOLE_ENCODING_PROPERTY, DEFAULT_CONSOLE_ENCODING);

        try {
            //установить кодировку стандартной консоли вывода
            System.setOut(new PrintStream(System.out, true, consoleEncoding));
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Unsupported encoding set for console: " + consoleEncoding);
        }
    }
}