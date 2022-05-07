package com.example.finalclick;

import static com.example.finalclick.R.id.about;

import android.annotation.SuppressLint;
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

        clickButton.setOnClickListener(v -> {
            userScore.Click();
            ScoreUpdate();
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
        else if(9786<exp && exp<19785){
            userScore.setLevel(5);
            float p = 100 - ((19785-userScore.getExp())*100) / (19785-9786);
            userScore.setProgress(p);
        }
        else if(19786<exp && exp<26385){
            userScore.setLevel(6);
            float p = 100 - ((26385-userScore.getExp())*100) / (26385-19786);
            userScore.setProgress(p);
        }
        else if(26386<exp && exp<39295){
            userScore.setLevel(7);
            float p = 100 - ((39295-userScore.getExp())*100) / (39295-26386);
            userScore.setProgress(p);
        }
        else if(39296<exp && exp<52295){
            userScore.setLevel(8);
            float p = 100 - ((52295-userScore.getExp())*100) / (52295-39296);
            userScore.setProgress(p);
        }
        else if(52296<exp && exp<67295){
            userScore.setLevel(9);
            float p = 100 - ((67295-userScore.getExp())*100) / (67295-52296);
            userScore.setProgress(p);
        }
        else if(67296<exp && exp<73295){
            userScore.setLevel(10);
            float p = 100 - ((73295-userScore.getExp())*100) / (73295-67296);
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