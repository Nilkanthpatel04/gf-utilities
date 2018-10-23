package com.sia.gemfire.gfclient.util;

import com.sia.gemfire.gfclient.impl.GFCacheProvider;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientRegionShortcut;

/**
 * @author Nilkanthkumar Patel - nipatel@pivotal.io
 * Singapore Airlines - CEM Project
 */
public class GFCacheUtils {
    public static Region<Object, Object> getRegion(final String regionName){
        Region<Object, Object> region = GFCacheProvider.getCache().getRegion(regionName);
        if(region == null) {
            region = GFCacheProvider.getCache()
                    .<Object, Object>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                    .create(regionName);
        }
        return region;
    }
}
