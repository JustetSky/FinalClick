package com.example.finalclick;

import static com.example.finalclick.R.id.about;
import static com.example.finalclick.R.id.clickButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.finalclick.Fragments.AboutFragment;
import com.example.finalclick.Fragments.HideFragment;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    public static UserScore userScore;
    public static TextView coinsView, levelView, progressView;
    private Button aboutButton;
    private SharedPreferences preferences;

    private final AtomicInteger changeFragmentIndex = new AtomicInteger();
    private final HideFragment hdAbout = new HideFragment();
    private final AboutFragment frAbout = new AboutFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Buttons
        Button clickButton = findViewById(R.id.clickButton);
        Button storeButton = findViewById(R.id.storeButton);
        aboutButton = findViewById(R.id.aboutButton);
        //Views
        coinsView = findViewById(R.id.totalCoins);
        progressView = findViewById(R.id.progress);
        levelView = findViewById(R.id.level);
        //User object
        userScore = new UserScore();
        loadSave();
        ScoreUpdate();

        clickButton.setOnClickListener(v -> {
            userScore.Click();
            ScoreUpdate();
            saveData();
        });

        storeButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Store.class);
            startActivity(intent);
        });

        //About
        FragmentTransaction startFragment = getSupportFragmentManager().beginTransaction();
        startFragment.replace(about, hdAbout);
        startFragment.commit();
        aboutButton.setOnClickListener(view -> {
            setChangeFragment();
        });
    }

    @SuppressLint("SetTextI18n")
    private void setChangeFragment(){
        changeFragmentIndex.addAndGet(1);
        if(changeFragmentIndex.get() %2 == 0){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(about, hdAbout);
            ft.commit();
            aboutButton.setText("About Game");
        }
        else{
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(about, frAbout);
            ft.commit();
            aboutButton.setText("Close");
        }
    }

    private void ScoreUpdate(){
        setLevel(userScore.getExp());
        coinsView.setText("Coins: " + userScore.getCoins());
        levelView.setText("Level: " + userScore.getLevel());
        String formattedProgress = String.format("%.2f", userScore.getProgress());
        progressView.setText("Progress: " + formattedProgress + "%");
    }

    private void setLevel(float exp){
        if(0<=exp && exp<375){
            userScore.setLevel(1);
            float p = 100 - ((375-userScore.getExp())*100) / (375);
            userScore.setProgress(p);
        }
        else if(376<exp && exp<1675){
            userScore.setLevel(2);
            float p = 100 - ((1675-userScore.getExp())*100) / (1675-376);
            userScore.setProgress(p);
        }
        else if(1676<exp && exp<5575){
            userScore.setLevel(3);
            float p = 100 - ((5575-userScore.getExp())*100) / (5575-1676);
            userScore.setProgress(p);
        }
        else if(5576<exp && exp<12785){
            userScore.setLevel(4);
            float p = 100 - ((12785-userScore.getExp())*100) / (12785-5576);
            userScore.setProgress(p);
        }
        else if(12786<exp && exp<21785){
            userScore.setLevel(5);
            float p = 100 - ((21785-userScore.getExp())*100) / (21785-12786);
            userScore.setProgress(p);
        }
        else if(21786<exp && exp<31385){
            userScore.setLevel(6);
            float p = 100 - ((31385-userScore.getExp())*100) / (31385-21786);
            userScore.setProgress(p);
        }
        else if(31386<exp && exp<43295){
            userScore.setLevel(7);
            float p = 100 - ((43295-userScore.getExp())*100) / (43295-31386);
            userScore.setProgress(p);
        }
        else if(43296<exp && exp<68295){
            userScore.setLevel(8);
            float p = 100 - ((68295-userScore.getExp())*100) / (68295-43296);
            userScore.setProgress(p);
        }
        else if(68296<exp && exp<95295){
            userScore.setLevel(9);
            float p = 100 - ((95295-userScore.getExp())*100) / (95295-68296);
            userScore.setProgress(p);
        }
        else if(95296<exp && exp<125295){
            userScore.setLevel(10);
            float p = 100 - ((125295-userScore.getExp())*100) / (125295-95296);
            userScore.setProgress(p);
        }
        else if(125296<exp && exp<158295){
            userScore.setLevel(11);
            float p = 100 - ((158295-userScore.getExp())*100) / (158295-125296);
            userScore.setProgress(p);
        }
        else if(158296<exp && exp<199295){
            userScore.setLevel(12);
            float p = 100 - ((199295-userScore.getExp())*100) / (199295-158296);
            userScore.setProgress(p);
        }
        else if(199296<exp && exp<244295){
            userScore.setLevel(13);
            float p = 100 - ((244295-userScore.getExp())*100) / (244295-199296);
            userScore.setProgress(p);
        }
        else if(244296<exp && exp<298295){
            userScore.setLevel(14);
            float p = 100 - ((298295-userScore.getExp())*100) / (298295-244296);
            userScore.setProgress(p);
        }
        else if(298296<exp && exp<495295){
            userScore.setLevel(15);
            float p = 100 - ((495295-userScore.getExp())*100) / (495295-298296);
            userScore.setProgress(p);
        }
        else if(495296<exp && exp<685295){
            userScore.setLevel(16);
            float p = 100 - ((685295-userScore.getExp())*100) / (685295-495296);
            userScore.setProgress(p);
        }
        else if(685296<exp){
            userScore.setLevel(17);
            userScore.setProgress(100);
        }
    }

    //Save and load statistic
    public void saveData(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("coins", userScore.getCoins());
        editor.putInt("coef", userScore.getCoef());
        editor.putInt("level", userScore.getLevel());
        editor.putFloat("exp", userScore.getExp());
        editor.putBoolean("isSuper", userScore.getIsSuper());
        editor.putInt("chance", userScore.getChance());
        editor.commit();
    }

    public void loadSave(){
        preferences = getPreferences(MODE_PRIVATE);
        userScore.setCoins(preferences.getInt("coins", 0));
        userScore.setCoef(preferences.getInt("coef", 1));
        userScore.setLevel(preferences.getInt("level", 1));
        userScore.setExp(preferences.getFloat("exp", 0));
        userScore.setIsSuper(preferences.getBoolean("isSuper", false));
        userScore.setChance(preferences.getInt("chance", 3));
        ScoreUpdate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}