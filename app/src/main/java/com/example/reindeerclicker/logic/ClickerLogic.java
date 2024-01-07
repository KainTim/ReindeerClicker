package com.example.reindeerclicker.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ClickerLogic {
    private volatile Double clickCount = 0d;
    private double increment = 1;
    private double autoClickAmount = 0;

    public void setClickCount(Double clickCount) {
        this.clickCount = clickCount;
    }

    public double getAutoClickAmount() {
        return autoClickAmount;
    }

    public void setAutoClickAmount(double autoClickAmount) {
        this.autoClickAmount = autoClickAmount;
    }

    public int getClickCount() {
        return (int) clickCount.doubleValue();
    }

    public void setClickCount(int clickCount) {
        this.clickCount= (double) clickCount;
    }
    public synchronized void increaseBy(double amount){
        clickCount+=amount;
    }
    public synchronized void increaseByIncrement(){
        clickCount+=increment;
    }

    public int getIncrement() {
        return (int) increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

}
