package com.sia.gemfire.gfclient.impl;

import com.sia.gemfire.gfclient.GFCache;
import com.sia.gemfire.gfclient.config.GFCacheConfig;
import com.sia.gemfire.gfclient.util.GFCacheUtils;
import org.apache.geode.cache.query.*;
import org.apache.geode.cache.query.internal.StructBag;
import org.apache.geode.cache.query.internal.StructImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.sia.gemfire.gfclient.util.GFCacheUtils.*;

public class GFCacheImpl implements GFCache {
    @Override
    public void saveAll(String regionName, Map<String, String> map) {
        //TODO: implement
        getRegion(regionName).putAll(map);
    }

    @Override
    public String get(String key) {
        return null;
    }


    @Override
    public List<String> getCustomerDetailsByCEID(String ceid) throws NameResolutionException, TypeMismatchException, QueryInvocationTargetException, FunctionDomainException {
        // Identify your query string.
        List<String> result = new ArrayList<>();
        String queryString = "SELECT  acc_nbr, int_id FROM /customer where cst_nbr ='" +  ceid + "'";

        // Get QueryService from client pool.
        QueryService queryService = GFCacheProvider.getCache().getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery(queryString);

        // Execute Query locally. Returns results set.
        SelectResults results = (SelectResults)query.execute();

        Iterator iterator = results.iterator();
        if(iterator.hasNext()) {
            StructImpl value = (StructImpl) iterator.next();


            result.add(String.valueOf(value.get("acc_nbr")));
            result.add(String.valueOf(value.get("int_id")));
            // Find the Size of the ResultSet.
            //int size = results.size();

        }

        //return acc_nbr or int_id


        return result;
    }
}