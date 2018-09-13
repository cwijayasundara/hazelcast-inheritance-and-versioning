package com.cham.inheritancedemo.demo;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;

import java.io.IOException;
import java.io.Serializable;

public class TradeParent implements Serializable, VersionedPortable {

    private String tradeId;
    private String traderName;

    @Override
    public String toString() {
        return "TradeParent{" +
                "tradeId='" + tradeId + '\'' +
                ", traderName='" + traderName + '\'' +
                '}';
    }

    private final static int classVersionId = 1;

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public TradeParent(){}

    public TradeParent(String id, String traderName){
        this.tradeId = id;
        this.traderName = traderName;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public int getClassId() {
        return PortableFactoryImpl.TRADE_PARENT_CLASS_ID;
    }

    @Override
    public int getFactoryId() {
        return PortableFactoryImpl.FACTORY_ID;
    }

    @Override
    public int getClassVersion(){
        return classVersionId;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        System.out.println("Serialize TradeParent");
        writer.writeUTF("tradeId", tradeId);
        writer.writeUTF("traderName", traderName);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        System.out.println("Deserialize TradeParent");
        this.tradeId = reader.readUTF("tradeId");
        this.traderName = reader.readUTF("traderName");
    }

}
