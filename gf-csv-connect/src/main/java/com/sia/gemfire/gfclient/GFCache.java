package com.sia.gemfire.gfclient;

import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.TypeMismatchException;

import java.util.List;
import java.util.Map;

/**
 * @author Nilkanthkumar Patel - nipatel@pivotal.io
 * Singapore Airlines - CEM Project
 */
public interface GFCache {
    /**
     * Load data into gemfire distributed cache
     * @param map, collection to store in gemfire.
     */
    public void saveAll(String regionName, Map<String, String> map);

    public String get(String key);

    public List<String> getCustomerDetailsByCEID(String ceid) throws NameResolutionException, TypeMismatchException, QueryInvocationTargetException, FunctionDomainException;
}