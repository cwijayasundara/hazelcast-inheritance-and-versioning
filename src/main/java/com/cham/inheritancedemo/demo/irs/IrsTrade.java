package com.cham.inheritancedemo.demo.irs;

import com.cham.inheritancedemo.demo.*;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

public class IrsTrade implements TradeParent {

    private CommonAttributes commonAttributes;
    private String counterParty;
    private String notional;
    private int margin;
    private IRSProps irsProps;

    private static final int classVersionId=4;


    @Override
    public String toString() {
        return "IrsTrade{" +
                "commonAttributes=" + commonAttributes +
                ", counterParty='" + counterParty + '\'' +
                ", notional=" + notional +
                ", margin=" + margin +
                ", irsProps=" + irsProps +
                "} ";
    }

    public IrsTrade(){}

    public IrsTrade(CommonAttributes commonAttributes, String counterParty, String notional, int margin, IRSProps irsProps ){
        this.commonAttributes = commonAttributes;
        this.counterParty = counterParty;
        this.notional = notional;
        this.margin = margin;
        this.irsProps = irsProps;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public String getNotional() {
        return notional;
    }

    public void setNotional(String notional) {
        this.notional = notional;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public CommonAttributes getCommonAttributes() {
        return commonAttributes;
    }

    public void setCommonAttributes(CommonAttributes commonAttributes) {
        this.commonAttributes = commonAttributes;
    }

    public IRSProps getIrsProps() {
        return irsProps;
    }

    public void setIrsProps(IRSProps irsProps) {
        this.irsProps = irsProps;
    }

    @Override
    public int getClassId() {
        return IrsPortableFactoryImpl.IRS_TRADE_CLASS_ID;
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
        writer.writePortable("commonAttributes", commonAttributes);
        writer.writeUTF("counterParty", counterParty);
        writer.writeUTF("notional", notional);
        if(margin > 0)
            writer.writeInt("margin", margin);
        writer.writePortable("irsProps",irsProps);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        this.commonAttributes = reader.readPortable("commonAttributes");
        this.counterParty = reader.readUTF("counterParty");
        this.notional = reader.readUTF("notional");
        this.margin = reader.readInt("margin");
        this.irsProps = reader.readPortable("irsProps");
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
