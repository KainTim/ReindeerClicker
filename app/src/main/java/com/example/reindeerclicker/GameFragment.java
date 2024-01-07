package com.example.reindeerclicker;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reindeerclicker.databinding.FragmentGameBinding;
import com.example.reindeerclicker.logic.ClickerLogic;
import com.example.reindeerclicker.model.RewardType;
import com.example.reindeerclicker.model.Upgrade;
import com.example.reindeerclicker.viewmodels.MainViewModel;

import java.util.List;

public class GameFragment extends Fragment implements View.OnClickListener {
    MainViewModel viewModel;
    private ClickerLogic clickerLogic = new ClickerLogic();
    private FragmentGameBinding binding;
    private Thread autoClicker = new Thread();


    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.clickerLogic = clickerLogic;
        List<Upgrade> upgrade = viewModel.upgradeList.getValue();
        if (upgrade != null) {
            try {
                autoClicker.interrupt();
                autoClicker.join();
                Handler handler = new Handler();
                autoClicker = new Thread(new AutoClickerThread(clickerLogic, handler));
                autoClicker.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        binding.iVImage.setOnClickListener(this);
        binding.tvScoreNum.setText("" + clickerLogic.getClickCount());
        binding.btnStore.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iVImage) {
            clickerLogic.increaseByIncrement();
            binding.tvScoreNum.setText("" + clickerLogic.getClickCount());
            ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(binding.iVImage, "scaleX", 1.13f);
            ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(binding.iVImage, "scaleY", 1.13f);
            scaleUpX.setDuration(100);
            scaleUpY.setDuration(100);
            AnimatorSet scaleUp = new AnimatorSet();
            scaleUp.play(scaleUpX).with(scaleUpY);
            scaleUp.start();
            CountDownTimer timer = new CountDownTimer(100,100) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(binding.iVImage, "scaleX", 0.87f);
                    ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(binding.iVImage, "scaleY", 0.87f);
                    scaleDownX.setDuration(100);
                    scaleDownY.setDuration(100);
                    AnimatorSet scaleDown= new AnimatorSet();
                    scaleDown.play(scaleDownX).with(scaleDownY);
                    scaleDown.start();
                }
            }.start();
        }
        if (v.getId() == R.id.btnStore) {
            viewModel.showStore();
        }
    }

    private class AutoClickerThread implements Runnable {
        ClickerLogic logic;
        private final Handler handler;

        public AutoClickerThread(ClickerLogic logic, Handler handler) {
            this.logic = logic;
            this.handler = handler;
        }

        @Override
        public void run() {
            while (true) {
                Log.d("GAME","Amount: "+clickerLogic.getAutoClickAmount()/4);
                int clickCount = clickerLogic.getClickCount();
                clickerLogic.increaseBy(clickerLogic.getAutoClickAmount()/4);
                Log.d("GAME", "Difference: "+(clickerLogic.getClickCount()-clickCount));
                handler.post(() -> binding.tvScoreNum.setText("" + clickerLogic.getClickCount()));
                if (clickerLogic.getAutoClickAmount()>0){
                    handler.post(()->{
                        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(binding.iVImage, "scaleX", 1.05f);
                        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(binding.iVImage, "scaleY", 1.05f);
                        scaleUpX.setDuration(75);
                        scaleUpY.setDuration(75);
                        AnimatorSet scaleUp = new AnimatorSet();
                        scaleUp.play(scaleUpX).with(scaleUpY);
                        scaleUp.start();
                        CountDownTimer timer = new CountDownTimer(90,90) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(binding.iVImage, "scaleX", 0.95f);
                                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(binding.iVImage, "scaleY", 0.95f);
                                scaleDownX.setDuration(75);
                                scaleDownY.setDuration(75);
                                AnimatorSet scaleDown= new AnimatorSet();
                                scaleDown.play(scaleDownX).with(scaleDownY);
                                scaleDown.start();
                            }
                        }.start();
                    });
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}