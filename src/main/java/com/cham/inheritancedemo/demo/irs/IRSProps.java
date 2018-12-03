package com.cham.inheritancedemo.demo.irs;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;

import java.io.IOException;

public class IRSProps  implements VersionedPortable {

    private String irsStrProp;
    private int irsIntProp;
    private Double irsDoubleProp;
    private Long commonLongProp;

    public IRSProps() {
    }

    public IRSProps(String irsStrProp, int irsIntProp, Double irsDoubleProp, Long commonLongProp) {
        this.irsStrProp = irsStrProp;
        this.irsIntProp = irsIntProp;
        this.irsDoubleProp = irsDoubleProp;
        this.commonLongProp = commonLongProp;
    }

    public String getIrsStrProp() {
        return irsStrProp;
    }

    public void setIrsStrProp(String irsStrProp) {
        this.irsStrProp = irsStrProp;
    }

    public int getIrsIntProp() {
        return irsIntProp;
    }

    public void setIrsIntProp(int irsIntProp) {
        this.irsIntProp = irsIntProp;
    }

    public Double getIrsDoubleProp() {
        return irsDoubleProp;
    }

    public void setIrsDoubleProp(Double irsDoubleProp) {
        this.irsDoubleProp = irsDoubleProp;
    }

    public Long getCommonLongProp() {
        return commonLongProp;
    }

    public void setCommonLongProp(Long commonLongProp) {
        this.commonLongProp = commonLongProp;
    }

    @Override
    public String toString() {
        return "IRSProps{" +
                "irsStrProp='" + irsStrProp + '\'' +
                ", irsIntProp=" + irsIntProp +
                ", irsDoubleProp=" + irsDoubleProp +
                ", commonLongProp=" + commonLongProp +
                '}';
    }

    @Override
    public int getClassId() {
        return IrsPortableFactoryImpl.IRS_PROPS_CLASS_ID;
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
        writer.writeUTF("irsStrProp", irsStrProp);
        writer.writeInt("irsIntProp", irsIntProp);
        writer.writeDouble("irsDoubleProp", irsDoubleProp);
        writer.writeLong("commonLongProp", commonLongProp);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        this.irsStrProp = reader.readUTF("irsStrProp");
        this.irsIntProp = reader.readInt("irsIntProp");
        this.irsDoubleProp = reader.readDouble("irsDoubleProp");
        this.commonLongProp = reader.readLong("commonLongProp");
    }

}
