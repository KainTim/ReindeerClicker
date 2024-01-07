package com.example.reindeerclicker.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ClickerLogic {
    private volatile Integer clickCount = 0;
    private int increment = 1;
    private int autoClickAmount = 0;

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public int getAutoClickAmount() {
        return autoClickAmount;
    }

    public void setAutoClickAmount(int autoClickAmount) {
        this.autoClickAmount = autoClickAmount;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount=clickCount;
    }
    public synchronized void increaseBy(int amount){
        clickCount+=amount;
    }
    public synchronized void increaseByIncrement(){
        clickCount+=increment;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

}
