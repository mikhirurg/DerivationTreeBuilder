package io.github.mikhirurg.derivationtreebuilder;

import java.awt.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class App {
    private static final ResourceBundle resourceBundle;

    static {
        ResourceBundle resourceBundleTmp;
        try {
            resourceBundleTmp = PropertyResourceBundle.getBundle("AppBundle");
        } catch (MissingResourceException e) {
            resourceBundleTmp = PropertyResourceBundle.getBundle("AppBundle", Locale.forLanguageTag("en"));
        }
        resourceBundle = resourceBundleTmp;
    }

    public static String getString(String key) {
        return resourceBundle.getString(key);
    }

    public static Dimension getScreenDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }


}
