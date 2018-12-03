package com.cham.inheritancedemo.demo;

import com.cham.inheritancedemo.demo.fx.FxPortableFactoryImpl;
import com.cham.inheritancedemo.demo.fx.FxProps;
import com.cham.inheritancedemo.demo.fx.FxTrade;
import com.cham.inheritancedemo.demo.irs.CommonAttributes;
import com.cham.inheritancedemo.demo.irs.IrsPortableFactoryImpl;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.map.QueryCache;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.PredicateBuilder;

import java.util.Collection;

public class QueryMain {

    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig srzConfig = clientConfig.getSerializationConfig();
        srzConfig.addPortableFactoryClass(IrsPortableFactoryImpl.FACTORY_ID, IrsPortableFactoryImpl.class);
        srzConfig.addPortableFactoryClass(FxPortableFactoryImpl.FACTORY_ID, FxPortableFactoryImpl.class);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<String, TradeParent> tradeMap = client.getMap("trade-map");

        CommonAttributes fx4Common = new CommonAttributes("Fx-004", "charlee", ProductType.FX, "fx4", "fx4 type trade","789");
        TradeParent fx4 = new FxTrade(fx4Common, "test", 1200, "AUD",new FxProps("str", 4, 40.50, 400000l));
        tradeMap.put(fx4Common.getTradeId(), fx4);


        EntryObject entryObject = new PredicateBuilder().getEntryObject();
        PredicateBuilder predicateBuilder = entryObject.get("clientPrice").greaterThan("400");
//        PredicateBuilder predicateBuilder = entryObject.get("fxProps.fxDoubleProp").greaterThan("30.0");
//        PredicateBuilder predicateBuilder = entryObject.get("fxProps.commonLongProp").greaterThan("100000");
//        PredicateBuilder predicateBuilder = entryObject.get("irsProps.irsIntProp").greaterThan("5");

        QueryCache queryCache = tradeMap.getQueryCache("fxCache", predicateBuilder, true);
//        QueryCache queryCache = tradeMap.getQueryCache(predicateBuilder.toString(), predicateBuilder, true);
        Collection values = queryCache.values();
        System.out.println("values.size() = " + values.size());
        System.out.println("values = " + values);
    }
}
