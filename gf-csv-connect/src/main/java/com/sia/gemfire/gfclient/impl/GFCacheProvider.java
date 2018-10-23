package com.sia.gemfire.gfclient.impl;

import com.sia.gemfire.gfclient.config.GFCacheConfig;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.util.Properties;

/**
 * @author Nilkanthkumar Patel - nipatel@pivotal.io
 * Singapore Airlines - CEM Project
 */
public class GFCacheProvider {
    private static volatile ClientCache cache = null;

    /**
     * Get instance of GemFire client cache.
     * @param props configuration for client cache.
     * @return instance of GF client cache.
     */
    public static ClientCache getCache(Properties props){
        GFCacheConfig gfCacheConfig = new GFCacheConfig();
        gfCacheConfig.getProperties();

        if(cache == null){
            synchronized (GFCacheProvider.class){
                if(cache == null){
                    System.out.println("Test creating cache..!");
                    cache = new ClientCacheFactory()
                            .addPoolLocator(gfCacheConfig.getLOCATOR_HOST(), gfCacheConfig.getLOCATOR_PORT())
                            .set("log-level", gfCacheConfig.getLOG_LEVEL())
                            .set("log-file", gfCacheConfig.getLOG_FILE_PATH())
                            .create();
                }
            }
        }

        return cache;
    }

    public static ClientCache getCache(){
        return getCache(null);
    }

    /**
     * API to close GF client cache
     */
    public static void closeCache(){
        if(cache != null && !cache.isClosed()){
            cache.close();
        }
    }
}
