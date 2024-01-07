package com.example.reindeerclicker.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.reindeerclicker.logic.ClickerLogic;
import com.example.reindeerclicker.model.RewardType;
import com.example.reindeerclicker.model.ShopItem;
import com.example.reindeerclicker.model.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    public static final Integer SHOW_MENU = 0;
    public static final Integer SHOW_GAME = 1;
    public static final Integer SHOW_STORE = 2;
    private MutableLiveData<Integer> _state = new MutableLiveData<>(0);
    public LiveData<Integer> state = _state;
    private MutableLiveData<List<Upgrade>> _upgradeList = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Upgrade>> upgradeList = _upgradeList;
    public ClickerLogic clickerLogic;
    private List<ShopItem> shopItems = new ArrayList<>();

    public void showMenu(){
        _state.postValue(SHOW_MENU);
    }
    public void showGame(){
        _state.postValue(SHOW_GAME);
    }

    public void showStore() {
        _state.postValue(SHOW_STORE);
    }

    public List<ShopItem> getShopItemList() {
        return shopItems;
    }

    public LiveData<List<Upgrade>> getUpgradeList() {
        return upgradeList;
    }

    public void setUpgradeList(LiveData<List<Upgrade>> upgradeList) {
        this.upgradeList = upgradeList;
    }

    public void setShopItemList(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }
    public void initAndShow(Integer showID){
        _upgradeList = new MutableLiveData<>(new ArrayList<>());
        clickerLogic = new ClickerLogic();
        shopItems.clear();
        shopItems.add(new ShopItem(new Upgrade(RewardType.CLICK,2,15,"Gain 2 Additionally")));
        shopItems.add(new ShopItem(new Upgrade(RewardType.CLICK,5,50,"Gain 5 Additionally")));
        shopItems.add(new ShopItem(new Upgrade(RewardType.CLICK,9,500,"Gain 9 Additionally")));
        shopItems.add(new ShopItem(new Upgrade(RewardType.CLICK,15,2000,"Gain 15 Additionally")));
        shopItems.add(new ShopItem(new Upgrade(RewardType.AUTO,2,100,"Gain +2 Per Second")));
        shopItems.add(new ShopItem(new Upgrade(RewardType.AUTO,8,600,"Gain +6 Per Second")));
        shopItems.add(new ShopItem(new Upgrade(RewardType.AUTO,10,2500,"Gain +10 Per Second")));
        shopItems.add(new ShopItem(new Upgrade(RewardType.AUTO,20,1,"Gain +20 Per Second")));
        _state.postValue(showID);
    }
}
