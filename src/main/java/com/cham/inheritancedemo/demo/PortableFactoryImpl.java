package com.cham.inheritancedemo.demo;


import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class PortableFactoryImpl implements PortableFactory {

    static final int IRS_TRADE_CLASS_ID =2;
    static final int FX_TRADE_CLASS_ID = 3;
    static final int TRADE_COMMON_ATTRIBUTES_CLASS_ID = 4;

    static final int FACTORY_ID = 1;

    public Portable create(int classId) {
        switch (classId) {
            case IRS_TRADE_CLASS_ID:
                return new IrsTrade();
            case FX_TRADE_CLASS_ID:
                return new FxTrade();
            case TRADE_COMMON_ATTRIBUTES_CLASS_ID:
                return new CommonAttributes();
            default:
                return null;
        }
    }
}
