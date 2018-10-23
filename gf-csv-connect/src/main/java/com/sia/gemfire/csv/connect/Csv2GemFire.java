package com.sia.gemfire.csv.connect;

import com.sia.gemfire.csv.config.Constants;
import com.sia.gemfire.gfclient.GFCache;
import com.sia.gemfire.gfclient.impl.GFCacheImpl;
import com.sia.gemfire.gfclient.impl.GFCacheProvider;
import com.sia.gemfire.gfclient.impl.LocalCacheImpl;
import com.sia.gemfire.gfclient.util.GFCacheUtils;
import org.apache.geode.pdx.PdxInstance;

import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

        Map<String, PdxInstance> custData = new HashMap<>();

        //PdxInstance (cst_nbr, acc_nbr, int_id)

        PdxInstance pi_1 = GFCacheProvider.getCache().createPdxInstanceFactory("com.company.DomainObject")
                .writeString("cst_nbr", "5000000008002222203")
                .markIdentityField("cst_nbr")
                .writeString("acc_nbr", "111111")
                .writeString("int_id", "")
                .create();

        PdxInstance pi_2 = GFCacheProvider.getCache().createPdxInstanceFactory("com.company.DomainObject")
                .writeString("cst_nbr","5000000008002222207")
                .markIdentityField("cst_nbr")
                .writeString("acc_nbr", "2222222")
                .writeString("int_id", "")
                .create();

        PdxInstance pi_3 = GFCacheProvider.getCache().createPdxInstanceFactory("com.company.DomainObject")
                .writeString("cst_nbr","5000000000029429109")
                .markIdentityField("cst_nbr")
                .writeString("acc_nbr", "3333333")
                .writeString("int_id", "")
                .create();

        PdxInstance pi_4 = GFCacheProvider.getCache().createPdxInstanceFactory("com.company.DomainObject")
                .writeString("cst_nbr","5000000008002220004")
                .markIdentityField("cst_nbr")
                .writeString("acc_nbr", "888888")
                .writeString("int_id", "")
                .create();

        PdxInstance pi_5 = GFCacheProvider.getCache().createPdxInstanceFactory("com.company.DomainObject")
                .writeString("cst_nbr","5274252")
                .markIdentityField("cst_nbr")
                .writeString("acc_nbr", "999999")
                .writeString("int_id", "")
                .create();



        GFCacheUtils.getRegion("customer").put("5000000008002222203", pi_1);
        GFCacheUtils.getRegion("customer").put("5000000008002222207", pi_2);
        GFCacheUtils.getRegion("customer").put("5000000000029429109", pi_3);
        GFCacheUtils.getRegion("customer").put("5000000008002220004", pi_4);
        GFCacheUtils.getRegion("customer").put("5274252", pi_5);


        System.out.println("NNN => Customer region populated..!!");
        oneByOneSyncExample();



        /*Iterator<Map.Entry<String, String>> entries = LocalCacheImpl.getLocalMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }*/
        //readAllSyncExample();
    }
}
