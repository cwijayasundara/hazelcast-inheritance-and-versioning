package com.cham.inheritancedemo.demo;

import com.cham.inheritancedemo.demo.fx.FxPortableFactoryImpl;
import com.cham.inheritancedemo.demo.fx.FxProps;
import com.cham.inheritancedemo.demo.fx.FxTrade;
import com.cham.inheritancedemo.demo.irs.CommonAttributes;
import com.cham.inheritancedemo.demo.irs.IRSProps;
import com.cham.inheritancedemo.demo.irs.IrsPortableFactoryImpl;
import com.cham.inheritancedemo.demo.irs.IrsTrade;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.TimeUnit;

public class InheritanceDemoMain {

    public static void main(String args[]){

        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig srzConfig = clientConfig.getSerializationConfig();
        srzConfig.addPortableFactoryClass(IrsPortableFactoryImpl.FACTORY_ID, IrsPortableFactoryImpl.class);
        srzConfig.addPortableFactoryClass(FxPortableFactoryImpl.FACTORY_ID, FxPortableFactoryImpl.class);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<String, TradeParent> tradeMap = client.getMap("trade-map");

        CommonAttributes irs1Common = new CommonAttributes("Irs-001", "Tom", ProductType.IRS, "irs1", "irs1 type trade", "123");
        CommonAttributes irs2Common = new CommonAttributes("Irs-002", "Elon", ProductType.IRS, "irs2", "irs2 type trade", "456");

        CommonAttributes fx1Common = new CommonAttributes("Fx-001", "Paul", ProductType.FX, "fx1", "fx1 type trade", "890");
        CommonAttributes fx2Common = new CommonAttributes("Fx-002", "Tom", ProductType.FX, "fx2", "fx2 type trade", "345");
        CommonAttributes fx3Common = new CommonAttributes("Fx-003", "charlee", ProductType.FX, "fx3", "fx3 type trade","678");

        TradeParent Irs1 = new IrsTrade(irs1Common,"Halifax", "4000000", 1000, new IRSProps("irsStr", 5, 50.5, 500000l));
        TradeParent Irs2 = new IrsTrade(irs2Common, "LBG","1000000" ,20000, new IRSProps("irsStr", 6, 60.5, 600000l));

        TradeParent fx1 = new FxTrade(fx1Common, "Open", 500, "GBP", new FxProps("str", 1, 10.50, 100000l));
        TradeParent fx2 = new FxTrade(fx2Common, "Closed", 600, "USD",new FxProps("str", 2, 20.50,200000l));
        TradeParent fx3 = new FxTrade(fx3Common, "test", 900, "EUR",new FxProps("str", 3, 30.50, 300000l));

        tradeMap.put(fx1Common.getTradeId(), fx1);
        tradeMap.put(fx2Common.getTradeId(), fx2);
        tradeMap.put(fx3Common.getTradeId(), fx3);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tradeMap.put(irs1Common.getTradeId(), Irs1);
        tradeMap.put(irs2Common.getTradeId(), Irs2);

        System.out.println("map data::"+ tradeMap.values());

//        Collection<TradeParent> trades = tradeMap.values(new SqlPredicate( "commonAttributes.createdBy like T% or notional < 5000000"));
//
//        Iterator itr = trades.iterator();
//        while (itr.hasNext()){
//            System.out.println(itr.next());
//        }
//
//        //updating a record with new price
//        TradeParent Irs2_updated = new IrsTrade(irs2Common, "LBG", "7000000", 75000);
//        tradeMap.put(irs2Common.getTradeId(), Irs2_updated);
//
//        System.out.println("updated map data::"+ tradeMap.entrySet());
//
//        Collection<TradeParent> trades1 = tradeMap.values(new SqlPredicate( "commonAttributes.createdBy like T% or notional < 5000000"));
//
//        Iterator itr1 = trades1.iterator();
//        while (itr1.hasNext()){
//            System.out.println(itr1.next());
//        }
//
//
//        //updating a record with new createdBy
//       // fx1Common.setCreatedBy("John");
//        TradeParent fx1_updated = new FxTrade(fx1Common, "Open", 25);
//        tradeMap.put(fx1Common.getTradeId(), fx1_updated);
//
//        System.out.println("updated map data::"+ tradeMap.entrySet());


    }
}
