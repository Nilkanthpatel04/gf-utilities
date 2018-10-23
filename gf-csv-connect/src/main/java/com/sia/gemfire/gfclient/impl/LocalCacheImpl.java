package com.sia.gemfire.gfclient.impl;

import com.sia.gemfire.gfclient.GFCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LocalCacheImpl implements GFCache {

    public static Map<Long, String> localMap = new HashMap<>();

    public LocalCacheImpl() {
        //this.localMap = new HashMap<>();
    }

    public static Map<Long, String> getLocalMap() {
        return localMap;
    }

    @Override
    public void saveAll(String regionName, Map<Long, String> map) {
        //TODO: implement
        getLocalMap().putAll(map);
    }

    @Override
    public String get(Long key) {
        return null;
    }

    @Override
    public List<String> getCustomerDetailsByCEID() {
        return null;
    }

}