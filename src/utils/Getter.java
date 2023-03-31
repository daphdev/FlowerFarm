package utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class Getter {

    public Getter() {}

    public static Object getProperty(Object o, String propertyName) {
        Object propertyValue = null;
        try {
            propertyValue = PropertyUtils.getProperty(o, propertyName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }

}
