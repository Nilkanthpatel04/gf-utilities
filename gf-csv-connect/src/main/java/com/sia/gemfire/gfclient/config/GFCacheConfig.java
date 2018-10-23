package com.sia.gemfire.gfclient.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Nilkanthkumar Patel - nipatel@pivotal.io
 * Singapore Airlines - CEM Project
 */
public class GFCacheConfig {
    /*
    public static  String LOCATOR_HOST = "10.80.36.180";
    public static  int LOCATOR_PORT = 10443;
    public static  String LOG_LEVEL = "ALL";
    public static  String LOG_FILE_PATH = "E:\\pivotal-gf\\GFClient.log";
    public static  String PASSENGER_REGION = "passenger";
    */

    public String LOCATOR_HOST = null;
    public int LOCATOR_PORT;
    public String LOG_LEVEL = null;
    public String LOG_FILE_PATH = null;
    public static final String PASSENGER_REGION = "passenger";

    public void getProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("appllication.properties");

            LOCATOR_HOST = prop.getProperty("locator.host");
            LOCATOR_PORT = Integer.parseInt(prop.getProperty("locator.port"));
            LOG_LEVEL = prop.getProperty("log.level");
            LOG_FILE_PATH = prop.getProperty("log.file.path");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLOCATOR_HOST() {
        return LOCATOR_HOST;
    }

    public int getLOCATOR_PORT() {
        return LOCATOR_PORT;
    }

    public String getLOG_LEVEL() {
        return LOG_LEVEL;
    }

    public String getLOG_FILE_PATH() {
        return LOG_FILE_PATH;
    }
}