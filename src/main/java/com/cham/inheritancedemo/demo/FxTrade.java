package com.cham.inheritancedemo.demo;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;

import java.io.IOException;
import java.io.Serializable;

public class FxTrade extends TradeParent implements Serializable, VersionedPortable {

    private String status;
    private int clientPrice;
    private static final int classVersionId = 1;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClientPrice() {
        return clientPrice;
    }

    public void setClientPrice(int clientPrice) {
        this.clientPrice = clientPrice;
    }

    @Override
    public String toString() {
        return "FxTrade{" +
                "status='" + status + '\'' +
                ", clientPrice=" + clientPrice +
                '}';
    }

    public FxTrade(){}

    public FxTrade(String tradeId, String traderName, String status, int clientPrice ){
        super(tradeId,traderName);
        this.status = status;
        this.clientPrice = clientPrice;
    }

    @Override
    public int getClassId() {
        return PortableFactoryImpl.FX_TRADE_CLASS_ID;
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
        System.out.println("Serialize FxTrade");
        writer.writeUTF("status", status);
        writer.writeInt("clientPrice", clientPrice);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        System.out.println("Deserialize FxTrade");
        this.status = reader.readUTF("status");
        this.clientPrice = reader.readInt("clientPrice");
    }

}
