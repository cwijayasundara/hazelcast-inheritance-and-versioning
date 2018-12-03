package com.cham.inheritancedemo.demo.irs;


import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class IrsPortableFactoryImpl implements PortableFactory {

    static final int IRS_TRADE_CLASS_ID =1;
    static final int IRS_COMMON_ATTRIBUTES_CLASS_ID = 2;
     static final int IRS_PROPS_CLASS_ID = 3;

    public static final int FACTORY_ID = 1;
    static final int CLASS_VERSION_ID = 1;

    public Portable create(int classId) {
        switch (classId) {
            case IRS_TRADE_CLASS_ID:
                return new IrsTrade();
            case IRS_COMMON_ATTRIBUTES_CLASS_ID:
                return new CommonAttributes();
            case IRS_PROPS_CLASS_ID:
                return new IRSProps();
            default:
                return null;
        }
    }
}
