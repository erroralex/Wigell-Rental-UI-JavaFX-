package com.nilsson.camping.model.items;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "itemType"
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = Gear.class, name = "gear"),
        @JsonSubTypes.Type(value = RecreationalVehicle.class, name = "vehicle"),

        // Add specific RecreationalVehicle subtypes
        @JsonSubTypes.Type(value = RecreationalVehicle.class, name = "Motorhome"),
        @JsonSubTypes.Type(value = RecreationalVehicle.class, name = "Caravan"),
        @JsonSubTypes.Type(value = RecreationalVehicle.class, name = "Campervan"),

        // Add specific Gear subtypes
        @JsonSubTypes.Type(value = Gear.class, name = "Tent"),
        @JsonSubTypes.Type(value = Gear.class, name = "Backpack"),
        @JsonSubTypes.Type(value = Gear.class, name = "Other Gear")
})

public abstract class Item {

    private int itemId;
    private double dailyPrice;

    public Item() {

    }

    public Item(int itemId, double dailyPrice) {
        this.itemId = itemId;
        this.dailyPrice = dailyPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
}