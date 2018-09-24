package com.cham.inheritancedemo.demo;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

public class FxTrade implements TradeParent  {

    private CommonAttributes commonAttributes;
    private String status;
    private int clientPrice;
    private String currency;

    private static final int classVersionId = 6;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CommonAttributes getCommonAttributes() {
        return commonAttributes;
    }

    public void setCommonAttributes(CommonAttributes commonAttributes) {
        this.commonAttributes = commonAttributes;
    }

    @Override
    public String toString() {
        return "FxTrade{" +
                "commonAttributes=" + commonAttributes +
                ", status='" + status + '\'' +
                ", clientPrice=" + clientPrice +
                ", currency=" + currency +
                "} " ;
    }

    public FxTrade(){}

    public FxTrade(CommonAttributes commonAttributes, String status, int clientPrice, String currency ){
        this.commonAttributes = commonAttributes;
        this.status = status;
        this.clientPrice = clientPrice;
        this.currency = currency;
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
        writer.writePortable("commonAttributes", commonAttributes);
        writer.writeUTF("status", status);
        writer.writeInt("clientPrice", clientPrice);
        writer.writeUTF("currency", currency);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        this.commonAttributes = reader.readPortable("commonAttributes");
        this.status = reader.readUTF("status");
        this.clientPrice = reader.readInt("clientPrice");
        this.currency = reader.readUTF("currency");
    }

    @Override
    public ProductType getProductType() {
        return commonAttributes.getProductType();
    }

    @Override
    public String getProductSubType() {
        return commonAttributes.getProductSubType();
    }
}


//    Exception in thread "main" com.hazelcast.nio.serialization.HazelcastSerializationException: Incompatible class-definitions with same class-id:
//        ClassDefinition{factoryId=1, classId=4, version=1,
//        fieldDefinitions=[FieldDefinitionImpl{index=0, fieldName='tradeId', type=UTF, classId=0, factoryId=0, version=0},
//        FieldDefinitionImpl{index=1, fieldName='createdBy', type=UTF, classId=0, factoryId=0, version=0},
//        FieldDefinitionImpl{index=2, fieldName='productType', type=UTF, classId=0, factoryId=0, version=0},
//        FieldDefinitionImpl{index=3, fieldName='productSubType', type=UTF, classId=0, factoryId=0, version=0}]} VS
//        ClassDefinition{factoryId=1, classId=4, version=1,
//        fieldDefinitions=[FieldDefinitionImpl{index=0, fieldName='tradeId', type=UTF, classId=0, factoryId=0, version=1},
//        FieldDefinitionImpl{index=1, fieldName='createdBy', type=UTF, classId=0, factoryId=0, version=1},
//        FieldDefinitionImpl{index=2, fieldName='productType', type=UTF, classId=0, factoryId=0, version=1},
//        FieldDefinitionImpl{index=3, fieldName='productSubType', type=UTF, classId=0, factoryId=0, version=1}]}
