package com.cham.inheritancedemo.demo.irs;

import com.cham.inheritancedemo.demo.ProductType;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;
import com.hazelcast.util.StringUtil;

import java.io.IOException;
import java.io.Serializable;

public class CommonAttributes implements Serializable, VersionedPortable {

    private String tradeId;
    private String createdBy;
    private ProductType productType;
    private String productSubType;
    private String description;
    private String timestamp;

    @Override
    public String toString() {
        return "CommonAttributes{" +
                "tradeId='" + tradeId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", productType='" + productType.name() + '\'' +
                ", productSubType='" + productSubType + '\'' +
                ", description='" + description + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    private final static int classVersionId = 6;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public CommonAttributes(){}

    public CommonAttributes(String id, String createdBy, ProductType productType, String productSubType, String description, String timestamp){
        this.tradeId = id;
        this.createdBy = createdBy;
        this.productType = productType;
        this.productSubType = productSubType;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductSubType() {
        return productSubType;
    }

    public void setProductSubType(String productSubType) {
        this.productSubType = productSubType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int getClassId() {
        return IrsPortableFactoryImpl.IRS_COMMON_ATTRIBUTES_CLASS_ID;
    }

    @Override
    public int getFactoryId() {
        return IrsPortableFactoryImpl.FACTORY_ID;
    }

    @Override
    public int getClassVersion(){
        return IrsPortableFactoryImpl.CLASS_VERSION_ID;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeUTF("tradeId", tradeId);
        writer.writeUTF("createdBy", createdBy);
        writer.writeUTF("productType", productType == null ? null :productType.name());
        writer.writeUTF("productSubType", productSubType);
        writer.writeUTF("description", description);
        writer.writeUTF("timestamp", timestamp);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        this.tradeId = reader.readUTF("tradeId");
        this.createdBy = reader.readUTF("createdBy");
        String dbProductType = reader.readUTF("productType");
        this.productType = StringUtil.isNullOrEmpty(dbProductType) ? null : ProductType.valueOf(dbProductType);
        this.productSubType = reader.readUTF("productSubType");
        this.description = reader.readUTF("description");
        this.timestamp = reader.readUTF("timestamp");
    }

}
