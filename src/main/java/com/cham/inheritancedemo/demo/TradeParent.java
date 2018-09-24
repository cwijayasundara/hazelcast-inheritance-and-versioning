package com.cham.inheritancedemo.demo;

import com.hazelcast.nio.serialization.VersionedPortable;

import java.io.Serializable;

public interface TradeParent extends Serializable, VersionedPortable {
    ProductType getProductType();
    String getProductSubType();
}
