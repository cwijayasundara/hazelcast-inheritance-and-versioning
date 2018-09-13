package com.cham.inheritancedemo.demo;


import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class PortableFactoryImpl implements PortableFactory {

    static final int TRADE_PARENT_CLASS_ID = 1;
    static final int IRS_TRADE_CLASS_ID =2;

    static final int FACTORY_ID = 1;

    public Portable create(int classId) {
        switch (classId) {
            case TRADE_PARENT_CLASS_ID:
                return new TradeParent();
            case IRS_TRADE_CLASS_ID:
                return new IrsTrade();
            default:
                return null;
        }
    }
}
