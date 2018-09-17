package com.cham.inheritancedemo.demo;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;

import java.util.Collection;
import java.util.Iterator;

public class InheritanceDemoMain {

    public static void main(String args[]){

        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig srzConfig = clientConfig.getSerializationConfig();
        srzConfig.addPortableFactoryClass(1, "com.cham.inheritancedemo.demo.PortableFactoryImpl");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<String, TradeParent> tradeMap = client.getMap("trade-map");

        TradeParent parentTrade1 = new TradeParent("T001", "John");
        TradeParent parentTrade2 = new TradeParent("T002", "Simon");

        IrsTrade Irs1 = new IrsTrade("Irs-001", "Tom", "IRS", 1000);
        IrsTrade Irs2 = new IrsTrade("Irs-002", "Jack", "IRS", 2000);

        FxTrade fx1 = new FxTrade("Fx-001", "Claire", "Open", 2);
        FxTrade fx2 = new FxTrade("Fx-002", "Paul", "Closed", 1);

        tradeMap.set("T001",parentTrade1);
        tradeMap.set("T002",parentTrade2);

        tradeMap.set("Irs-001", Irs1);
        tradeMap.set("Irs-002", Irs2);

        tradeMap.set("Fx-001", fx1);
        tradeMap.set("Fx-002", fx2);

        Collection<TradeParent> trades = tradeMap.values(new SqlPredicate( "traderName like J% OR tradeAmount > 1000 OR status like Open"));

        Iterator itr = trades.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
