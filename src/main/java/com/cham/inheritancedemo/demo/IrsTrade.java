package com.cham.inheritancedemo.demo;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;

import java.io.IOException;
import java.io.Serializable;

public class IrsTrade extends TradeParent implements Serializable, VersionedPortable {

    private String tradeName;
    private int tradeAmount;

    private static final int classVersionId=1;

    public IrsTrade(){}

    public IrsTrade(String tradeId, String traderName, String tradeName, int tradeAmount ){
        super(tradeId,traderName);
        this.tradeName = tradeName;
        this.tradeAmount = tradeAmount;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public int getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(int tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    @Override
    public String toString() {
        return "IrsTrade{" +
                "tradeName='" + tradeName + '\'' +
                ", tradeAmount=" + tradeAmount +
                '}';
    }

    @Override
    public int getClassId() {
        return PortableFactoryImpl.IRS_TRADE_CLASS_ID;
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
        System.out.println("Serialize IrsTrade");
        writer.writeUTF("tradeName", tradeName);
        writer.writeInt("tradeAmount", tradeAmount);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        System.out.println("Deserialize IrsTrade");
        this.tradeName = reader.readUTF("tradeName");
        this.tradeAmount = reader.readInt("tradeAmount");
    }

}
