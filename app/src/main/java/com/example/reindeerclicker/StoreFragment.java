package com.example.reindeerclicker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reindeerclicker.databinding.FragmentStoreBinding;
import com.example.reindeerclicker.logic.StoreLogic;
import com.example.reindeerclicker.viewmodels.MainViewModel;

public class StoreFragment extends Fragment implements View.OnClickListener {
    MainViewModel viewmodel;
    FragmentStoreBinding binding;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(inflater, container, false);
        viewmodel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        int clickCount = viewmodel.clickerLogic.getClickCount();
        if (clickCount< StoreLogic.UPGRADE_1){
            binding.btnShop1.setEnabled(false);
        }else {
            binding.btnShop1.setEnabled(true);
        }
        if (clickCount< StoreLogic.UPGRADE_2){
            binding.btnShop2.setEnabled(false);
        }else {
            binding.btnShop2.setEnabled(true);
        }
        if (clickCount< StoreLogic.UPGRADE_3){
            binding.btnShop3.setEnabled(false);
        }else {
            binding.btnShop3.setEnabled(true);
        }
        if (clickCount< StoreLogic.UPGRADE_4){
            binding.btnShop4.setEnabled(false);
        }else {
            binding.btnShop4.setEnabled(true);
        }
        if (clickCount< StoreLogic.UPGRADE_5){
            binding.btnShop5.setEnabled(false);
        }else {
            binding.btnShop5.setEnabled(true);
        }

        binding.btnShop1.setOnClickListener(this);
        binding.btnShop1.setText(StoreLogic.UPGRADE_1+" Needed");
        binding.btnShop2.setOnClickListener(this);
        binding.btnShop2.setText(StoreLogic.UPGRADE_2+" Needed");
        binding.btnShop3.setOnClickListener(this);
        binding.btnShop3.setText(StoreLogic.UPGRADE_3+" Needed");
        binding.btnShop4.setOnClickListener(this);
        binding.btnShop4.setText(StoreLogic.UPGRADE_4+" Needed");
        binding.btnShop5.setOnClickListener(this);
        binding.btnShop5.setText(StoreLogic.UPGRADE_5+" Needed");
        /*switch (viewmodel.upgradeList.getValue()){
            case 5:
                binding.btnShop5.setEnabled(false);
                binding.btnShop5.setText("Bought Already");
            case 4:
                binding.btnShop4.setEnabled(false);
                binding.btnShop4.setText("Bought Already");
            case 3:
                binding.btnShop3.setEnabled(false);
                binding.btnShop3.setText("Bought Already");
            case 2:
                binding.btnShop2.setEnabled(false);
                binding.btnShop2.setText("Bought Already");
            case 1:
                binding.btnShop1.setEnabled(false);
                binding.btnShop1.setText("Bought Already");
                break;
            default:
        }*/
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {/*
        if (v.getId() == R.id.btnShop1){
            binding.btnShop1.setEnabled(false);
            binding.btnShop1.setText("Bought Already");
            viewmodel.setUpgradeCount(1);
        }
        if (v.getId() == R.id.btnShop2){
            binding.btnShop1.setEnabled(false);
            binding.btnShop1.setText("Bought Already");
            binding.btnShop2.setEnabled(false);
            binding.btnShop2.setText("Bought Already");
            viewmodel.setUpgradeCount(2);
        }
        if (v.getId() == R.id.btnShop3){
            binding.btnShop1.setEnabled(false);
            binding.btnShop1.setText("Bought Already");
            binding.btnShop2.setEnabled(false);
            binding.btnShop2.setText("Bought Already");
            binding.btnShop3.setEnabled(false);
            binding.btnShop3.setText("Bought Already");
            viewmodel.setUpgradeCount(3);
        }
        if (v.getId() == R.id.btnShop4){
            binding.btnShop1.setEnabled(false);
            binding.btnShop1.setText("Bought Already");
            binding.btnShop2.setEnabled(false);
            binding.btnShop2.setText("Bought Already");
            binding.btnShop3.setEnabled(false);
            binding.btnShop3.setText("Bought Already");
            binding.btnShop4.setEnabled(false);
            binding.btnShop4.setText("Bought Already");
            viewmodel.setUpgradeCount(4);
        }
        if (v.getId() == R.id.btnShop5){
            binding.btnShop1.setEnabled(false);
            binding.btnShop1.setText("Bought Already");
            binding.btnShop2.setEnabled(false);
            binding.btnShop2.setText("Bought Already");
            binding.btnShop3.setEnabled(false);
            binding.btnShop3.setText("Bought Already");
            binding.btnShop4.setEnabled(false);
            binding.btnShop4.setText("Bought Already");
            binding.btnShop5.setEnabled(false);
            binding.btnShop5.setText("Bought Already");
            viewmodel.setUpgradeCount(5);
        }*/
    }
}