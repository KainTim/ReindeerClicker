package com.example.reindeerclicker;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reindeerclicker.callbacks.MyCallback;
import com.example.reindeerclicker.databinding.FragmentItemBinding;
import com.example.reindeerclicker.logic.ClickerLogic;
import com.example.reindeerclicker.model.RewardType;
import com.example.reindeerclicker.model.ShopItem;
import com.example.reindeerclicker.model.Upgrade;

import java.util.List;
public class MyShopItemRecyclerViewAdapter extends RecyclerView.Adapter<MyShopItemRecyclerViewAdapter.ViewHolder> {

    private final List<ShopItem> mValues;
    private MyCallback mListener;
    private List<Upgrade> upgrades;
    private int clickCount;
    @androidx.annotation.NonNull
    private final ClickerLogic logic;

    public MyShopItemRecyclerViewAdapter(List<ShopItem> items, List<Upgrade> upgrades, ClickerLogic logic) {
        mValues = items;
        this.upgrades = upgrades;
        this.clickCount = logic.getClickCount();
        this.logic = logic;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ShopItem shopItem = mValues.get(position);
        holder.mItem = shopItem;
        holder.mBtnBuy.setText(shopItem.bought?"Unlocked already":"Unlock: "+ shopItem.upgrade.price);
        holder.mBtnBuy.setEnabled(!shopItem.bought);
        holder.mContentView.setText(shopItem.upgrade.description);
        holder.mBtnBuy.setEnabled(!shopItem.bought);
        if (!(shopItem.upgrade.price<=clickCount)){
            holder.mBtnBuy.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final Button mBtnBuy;
        public final TextView mContentView;
        public ShopItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mBtnBuy = binding.btnBuy;
            mContentView = binding.tvDescription;
            mBtnBuy.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btnBuy){
                if (!(mItem.upgrade.price<=clickCount)){
                    mBtnBuy.setEnabled(false);
                    return;
                }
                mItem.bought=true;
                mBtnBuy.setText("Unlocked already");
                mBtnBuy.setEnabled(false);
                upgrades.add(mValues.get(getLayoutPosition()).upgrade);
                logic.setAutoClickAmount(upgrades.stream().filter(upgrade -> upgrade.type== RewardType.AUTO).mapToInt(upgrade -> upgrade.rewardAmount).sum());
                logic.setIncrement(upgrades.stream().filter(upgrade -> upgrade.type== RewardType.CLICK).mapToInt(upgrade -> upgrade.rewardAmount).sum()+1);
                logic.setClickCount(logic.getClickCount()-mValues.get(getLayoutPosition()).upgrade.price);
                clickCount= logic.getClickCount();
            }
        }
    }
}