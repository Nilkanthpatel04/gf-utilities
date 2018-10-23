package com.sia.gemfire.gfclient.impl;

import com.sia.gemfire.gfclient.GFCache;
import com.sia.gemfire.gfclient.config.GFCacheConfig;
import com.sia.gemfire.gfclient.util.GFCacheUtils;
import org.apache.geode.cache.query.*;

import java.util.List;
import java.util.Map;

import static com.sia.gemfire.gfclient.util.GFCacheUtils.*;

public class GFCacheImpl implements GFCache {
    @Override
    public void saveAll(String regionName, Map<Long, String> map) {
        //TODO: implement
        getRegion(regionName).putAll(map);
    }

    @Override
    public String get(Long key) {
        return null;
    }


    @Override
    public List<String> getCustomerDetailsByCEID(String ceid) throws NameResolutionException, TypeMismatchException, QueryInvocationTargetException, FunctionDomainException {
        // Identify your query string.
        String queryString = "SELECT  acc_nbr, int_id FROM /customer where cst_nbr ='" +  ceid + "'";

        // Get QueryService from client pool.
        QueryService queryService = GFCacheProvider.getCache().getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery(queryString);

        // Execute Query locally. Returns results set.
        SelectResults results = (SelectResults)query.execute();

        // Find the Size of the ResultSet.
        int size = results.size();


        //return acc_nbr or int_id
        return null;
    }
}
