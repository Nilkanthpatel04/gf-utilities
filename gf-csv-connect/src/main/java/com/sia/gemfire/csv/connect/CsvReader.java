package com.sia.gemfire.csv.connect;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.sia.gemfire.gfclient.GFCache;
import com.sia.gemfire.gfclient.impl.LocalCacheImpl;


import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class CsvReader {

    public static List<String[]> readAll(Reader reader) {

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        List<String[]> list = new ArrayList<>();
        try {
            list = csvReader.readAll();
            reader.close();
            csvReader.close();
        } catch (Exception ex) {
            //Helpers.err(ex);
            System.out.println("NNN Exception :: " + " " + ex);
        }
        return list;
    }

    public static List<String[]> oneByOne(Reader reader) {
        List<String[]> list = new ArrayList<>();

        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1) //skip headers - column heading
                    .withCSVParser(parser)
                    .build();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if(list.size() == 2){
                    saveBatch(list);
                    /*
                    System.out.println("Preparing Batch of size = 2");
                    for(String[] row : list) {
                        System.out.println(" => " + Arrays.toString(row));

                        Long key = new Long(row[0]);
                        String value =  Arrays.asList(row).stream().skip(1).collect(Collectors.joining(","));
                    }
                    */
                    list.clear();
                }
                list.add(line);
            }
            //Prepare the last chunk/batch
            /*
            System.out.println("Preparing final Batch of size <= 2");
            for(String[] row : list) {
                System.out.println(" => " + Arrays.toString(row));
            }
            */
            saveBatch(list);
            list.clear();

            reader.close();
            csvReader.close();
        } catch (Exception ex) {
            System.out.println("NNN Exception :: " + " " + ex);
        }
        return list;
    }

    private static void saveBatch(List<String[]> list){
        GFCache cache = new LocalCacheImpl();
        Map<Long, String> localMap = new HashMap<>();

        System.out.println("Preparing Batch of size = 2");
        for(String[] row : list) {
            System.out.println(" => " + Arrays.toString(row));

            Long key = new Long(row[0]); //row[0] is ceid (cst_nbr)
            //TODO: fetch (OQL) RUPU_ID(int_id), KF_ID(acc_nbr) from /customer
            cache.getCustomerDetailsByCEID();

            //String value =  Arrays.asList(row).stream().skip(1).collect(Collectors.joining(","));
            String value =  Arrays.asList(row).stream().collect(Collectors.joining(","));
            localMap.put(key, value);
        }
        cache.saveAll("localMap", localMap);
    }

}



