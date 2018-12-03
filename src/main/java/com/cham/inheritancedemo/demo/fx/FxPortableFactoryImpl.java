package com.cham.inheritancedemo.demo.fx;


import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class FxPortableFactoryImpl implements PortableFactory {

    static final int FX_TRADE_CLASS_ID = 1;
    static final int FX_COMMON_ATTRIBUTES_CLASS_ID = 2;
    static final int FX_PROPS_CLASS_ID = 3;

    public static final int FACTORY_ID = 2;
    static final int CLASS_VERSION_ID = 1;

    public Portable create(int classId) {
        switch (classId) {
            case FX_TRADE_CLASS_ID:
                return new FxTrade();
            case FX_COMMON_ATTRIBUTES_CLASS_ID:
                return new CommonAttributes();
            case FX_PROPS_CLASS_ID:
                return new FxProps();
            default:
                return null;
        }
    }
}
