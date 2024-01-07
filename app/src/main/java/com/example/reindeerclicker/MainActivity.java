package com.example.reindeerclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.reindeerclicker.model.RewardType;
import com.example.reindeerclicker.model.ShopItem;
import com.example.reindeerclicker.model.Upgrade;
import com.example.reindeerclicker.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.initAndShow(MainViewModel.SHOW_MENU);
        viewModel.state.observe(this, state -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (state){
                case 0:
                    fragmentTransaction.replace(R.id.clMain,MenuFragment.newInstance());
                    break;
                case 1:
                    fragmentTransaction.replace(R.id.clMain,GameFragment.newInstance())
                            .addToBackStack(null);
                    break;
                case 2:
                    fragmentTransaction.replace(R.id.clMain,ShopItemFragment.newInstance(1))
                            .addToBackStack(null);
                    break;
                default:
                    fragmentTransaction.replace(R.id.clMain,MenuFragment.newInstance());
            }
            fragmentTransaction.commit();
        });
        setContentView(R.layout.activity_main);
    }
}