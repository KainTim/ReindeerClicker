package com.example.reindeerclicker.model;

public class Upgrade {
    public RewardType type;
    public int rewardAmount;
    public int price;
    public String description;

    public Upgrade(RewardType type, int rewardAmount, int price, String description) {
        this.type = type;
        this.rewardAmount = rewardAmount;
        this.price = price;
        this.description = description;
    }
}
