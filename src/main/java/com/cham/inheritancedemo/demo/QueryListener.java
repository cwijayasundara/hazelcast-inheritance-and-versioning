package com.cham.inheritancedemo.demo;

import com.cham.inheritancedemo.demo.fx.FxPortableFactoryImpl;
import com.cham.inheritancedemo.demo.irs.IrsPortableFactoryImpl;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.map.QueryCache;
import com.hazelcast.query.SqlPredicate;

public class QueryListener {

    public static void main(String[] args) {

        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig srzConfig = clientConfig.getSerializationConfig();
        srzConfig.addPortableFactoryClass(IrsPortableFactoryImpl.FACTORY_ID, IrsPortableFactoryImpl.class);
        srzConfig.addPortableFactoryClass(FxPortableFactoryImpl.FACTORY_ID, FxPortableFactoryImpl.class);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<String, TradeParent> tradeMap = client.getMap("trade-map");

        // createdBy user predicate listener
        SqlPredicate createdByUserPredicate = new SqlPredicate("commonAttributes.createdBy = Tom");

        QueryCache tradeCache = tradeMap.getQueryCache("tradeCache", createdByUserPredicate, true);
        tradeCache.addEntryListener(new TradeEventListener("createdByUserPredicate"), true);

        // FX trade clientPrice predicate listener
        SqlPredicate fxPricePredicate = new SqlPredicate("clientPrice > 400");

        QueryCache fxTradeCache = tradeMap.getQueryCache("fxCache", fxPricePredicate, true);
        fxTradeCache.addEntryListener(new TradeEventListener("fxPricePredicate"), true);

    }


}
