package com.cham.inheritancedemo.demo.fx;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;

import java.io.IOException;

public class FxProps implements VersionedPortable {

    private String fxStrProp;
    private int fxIntProp;
    private Double fxDoubleProp;
    private Long commonLongProp;


    public FxProps() {
    }

    public FxProps(String strProp, int intProp, Double doubleProp, Long commonLongProp) {
        this.fxStrProp = strProp;
        this.fxIntProp = intProp;
        this.fxDoubleProp = doubleProp;
        this.commonLongProp = commonLongProp;
    }

    public String getFxStrProp() {
        return fxStrProp;
    }

    public void setFxStrProp(String fxStrProp) {
        this.fxStrProp = fxStrProp;
    }

    public int getFxIntProp() {
        return fxIntProp;
    }

    public void setFxIntProp(int fxIntProp) {
        this.fxIntProp = fxIntProp;
    }

    public Double getFxDoubleProp() {
        return fxDoubleProp;
    }

    public void setFxDoubleProp(Double fxDoubleProp) {
        this.fxDoubleProp = fxDoubleProp;
    }

    public Long getCommonLongProp() {
        return commonLongProp;
    }

    public void setCommonLongProp(Long commonLongProp) {
        this.commonLongProp = commonLongProp;
    }

    @Override
    public String toString() {
        return "FxProps{" +
                "fxStrProp='" + fxStrProp + '\'' +
                ", fxIntProp=" + fxIntProp +
                ", fxDoubleProp=" + fxDoubleProp +
                ", commonLongProp=" + commonLongProp +
                '}';
    }

    @Override
    public int getClassId() {
        return FxPortableFactoryImpl.FX_PROPS_CLASS_ID;
    }

    @Override
    public int getFactoryId() {
        return FxPortableFactoryImpl.FACTORY_ID;
    }

    @Override
    public int getClassVersion(){
        return FxPortableFactoryImpl.CLASS_VERSION_ID;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeUTF("fxStrProp", fxStrProp);
        writer.writeInt("fxIntProp", fxIntProp);
        writer.writeDouble("fxDoubleProp", fxDoubleProp);
        writer.writeLong("commonLongProp", commonLongProp);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        this.fxStrProp = reader.readUTF("fxStrProp");
        this.fxIntProp = reader.readInt("fxIntProp");
        this.fxDoubleProp = reader.readDouble("fxDoubleProp");
        this.commonLongProp = reader.readLong("commonLongProp");
    }

}
