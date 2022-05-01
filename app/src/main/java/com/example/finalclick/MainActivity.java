package com.example.finalclick;

import static com.example.finalclick.R.id.about;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.finalclick.Fragments.AboutFragment;
import com.example.finalclick.Fragments.HideFragment;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    public static UserScore userScore;
    public static TextView coinsView, levelView, progressView;
    private static Button clickButton, storeButton, aboutButton;
    private SharedPreferences preferences;

    private AtomicInteger changeFragmentIndex = new AtomicInteger();
    private HideFragment hdAbout = new HideFragment();
    private AboutFragment frAbout = new AboutFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Buttons
        clickButton = findViewById(R.id.clickButton);
        storeButton = findViewById(R.id.storeButton);
        aboutButton = findViewById(R.id.aboutButton);
        //Views
        coinsView = findViewById(R.id.totalCoins);
        progressView = findViewById(R.id.progress);
        levelView = findViewById(R.id.level);
        //User object
        userScore = new UserScore();
        loadSave();

        clickButton.setOnClickListener(v -> {
            userScore.Click();
            ScoreUpdate();
        });

        storeButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Store.class);
            startActivity(intent);
        });

        //About
        FragmentTransaction fstart = getSupportFragmentManager().beginTransaction();
        fstart.replace(about, hdAbout);
        fstart.commit();
        aboutButton.setOnClickListener(view -> {
            setChangeFragment();
        });


    }

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
        if(0<=exp && exp<175){
            userScore.setLevel(1);
            float p = 100 - ((175-userScore.getExp())*100) / (175);
            userScore.setProgress(p);
        }
        else if(176<exp && exp<875){
            userScore.setLevel(2);
            float p = 100 - ((875-userScore.getExp())*100) / (875-176);
            userScore.setProgress(p);
        }
        else if(876<exp && exp<3575){
            userScore.setLevel(3);
            float p = 100 - ((3575-userScore.getExp())*100) / (3575-876);
            userScore.setProgress(p);
        }
        else if(3576<exp && exp<9785){
            userScore.setLevel(4);
            float p = 100 - ((9785-userScore.getExp())*100) / (9785-3576);
            userScore.setProgress(p);
        }
        else if(9786<exp && exp<18785){
            userScore.setLevel(5);
            float p = 100 - ((18785-userScore.getExp())*100) / (18785-9786);
            userScore.setProgress(p);
        }
        else if(18785<exp && exp<25385){
            userScore.setLevel(6);
            float p = 100 - ((25385-userScore.getExp())*100) / (25385-16785);
            userScore.setProgress(p);
        }
        else if(25385<exp && exp<37295){
            userScore.setLevel(7);
            float p = 100 - ((37295-userScore.getExp())*100) / (37295-21385);
            userScore.setProgress(p);
        }
        else if(37295<exp && exp<49295){
            userScore.setLevel(8);
            float p = 100 - ((49295-userScore.getExp())*100) / (49295-37295);
            userScore.setProgress(p);
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
        editor.commit();
    }

    public void loadSave(){
        preferences = getPreferences(MODE_PRIVATE);
        userScore.setCoins(preferences.getInt("coins", 0));
        userScore.setCoef(preferences.getInt("coef", 1));
        userScore.setLevel(preferences.getInt("level", 1));
        userScore.setExp(preferences.getFloat("exp", 0));
        userScore.setIsSuper(preferences.getBoolean("isSuper", false));
        ScoreUpdate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}