package com.nilsson.camping.model.items;

public interface IRentable {

    double getDailyPrice();

    boolean isRented();
    void setRented(boolean rented);

    String getItemType();
    String getItemName();

    int getItemId();
    void setItemId(int Id);
}
