package com.vlb.lab00.proplib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {
    private static final String PROPERTY_PREFIX = "plan_";
    private String propertyName;

    //установка названия файла properties (без префикса :( )
    public void setPropertyName(String name) {
        propertyName = name.toLowerCase();
    }

    // Загрузка свойств из файла
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        //получение пути до папки resource
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_PREFIX + propertyName + ".properties");
        //загрузка properties из стрима
        properties.load(inputStream);
        return properties;
    }
}
