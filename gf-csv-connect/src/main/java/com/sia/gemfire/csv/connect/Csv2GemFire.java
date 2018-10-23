package com.sia.gemfire.csv.connect;

import com.sia.gemfire.csv.config.Constants;
import com.sia.gemfire.gfclient.impl.LocalCacheImpl;

import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

public class Csv2GemFire {
    //public static void main(String[] args){
    //final String csvPath = "/home/nilkanth/work/code/SIA/bitbucket/gf-utilities/gf-csv-connect/src/main/resources/data/data.csv";

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
        oneByOneSyncExample();



        Iterator<Map.Entry<Long, String>> entries = LocalCacheImpl.getLocalMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Long, String> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        //readAllSyncExample();
    }
}

