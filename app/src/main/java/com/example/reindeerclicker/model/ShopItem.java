package com.example.reindeerclicker.model;

public class ShopItem {
    public Upgrade upgrade;
    public boolean bought = false;
    public ShopItem(Upgrade upgrade) {
        this.upgrade=upgrade;
    }
}