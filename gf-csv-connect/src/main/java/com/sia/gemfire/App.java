package com.sia.gemfire;

/**
 * Hello world!
 */

import com.sia.gemfire.csv.config.Constants;
import com.sia.gemfire.csv.connect.CsvReader;
import com.sun.deploy.ref.Helpers;

import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    /*
     *  CSV Reader Examples
     */

    public static Path multiColumnCsvPath() throws URISyntaxException {
        System.out.println("File Path => " + Constants.CSV_PERSONALIZED_DATA);
        URI uri = ClassLoader.getSystemResource(Constants.CSV_PERSONALIZED_DATA).toURI();
        return Paths.get(uri);
    }

    public static String oneByOneSyncExample() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(multiColumnCsvPath());
        } catch (Exception ex) {
            //Helpers.err(ex);
            System.out.println("NNN Exception :: " + " " + ex);
        }
        return CsvReader.oneByOne(reader).toString();
    }

    public static String readAllSyncExample() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(multiColumnCsvPath());
        } catch (Exception ex) {
            //Helpers.err(ex);
            System.out.println("NNN Exception :: " + " " + ex);
        }
        return CsvReader.readAll(reader).toString();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //oneByOneSyncExample();
        String[] list1 = new String[] {"123456", "ABC", "EFG", "HIGL", "LMNOP"};
        List<String> list = Arrays.asList(list1);
        String result = list.stream().skip(1).collect(Collectors.joining(","));

        System.out.println("OP => " + result);
        //readAllSyncExample();
    }
}
