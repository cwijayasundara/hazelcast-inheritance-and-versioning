package com.cham.inheritancedemo.demo;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;

public class TradeEventListener implements EntryAddedListener<String, TradeParent>, EntryUpdatedListener<String, TradeParent> {
    private String predicate;

    public TradeEventListener(String predicate) {

        this.predicate = predicate;
    }

    @Override
    public void entryAdded(EntryEvent<String, TradeParent> event) {
        processAddEvent(event);
    }

    @Override
    public void entryUpdated(EntryEvent<String, TradeParent> event) {
        processUpdateEvent(event);
    }

    private void processAddEvent(EntryEvent<String, TradeParent> event) {
        String key = event.getKey();
        TradeParent value = event.getValue();
        System.out.println("add event key::"+key + ":: predicate::"+predicate);


        System.out.println("producttype::"+value.getProductType());
        System.out.println("productsubtype::"+value.getProductSubType());

        if(value instanceof IrsTrade){
            IrsTrade irsTrade = (IrsTrade) value;
            System.out.println("predicate::"+predicate+"::irs add event::"+irsTrade.toString());
        } else if(value instanceof FxTrade){
            FxTrade fxTrade = (FxTrade) value;
            System.out.println("predicate::"+predicate+"::fx add event::"+fxTrade.toString());
        } else {
            System.out.println("receive unknown add event"+ event.getKey() + "::"+event.getValue());
        }
    }

    private void processUpdateEvent(EntryEvent<String, TradeParent> event) {
        String key = event.getKey();
        TradeParent value = event.getValue();
        System.out.println("update event key::"+key+ ":: predicate::"+predicate);

        System.out.println("producttype::"+value.getProductType());
        System.out.println("productsubtype::"+value.getProductSubType());

        if(value instanceof IrsTrade){
            IrsTrade irsTrade = (IrsTrade) value;
            System.out.println("predicate::"+predicate+"::irs update event::"+irsTrade.toString());
        } else if(value instanceof FxTrade){
            FxTrade fxTrade = (FxTrade) value;
            System.out.println("predicate::"+predicate+"::fx update event::"+fxTrade.toString());
        } else {
            System.out.println("receive unknown update event"+ event.getKey() + "::"+event.getValue());
        }

    }

}
