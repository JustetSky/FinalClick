package com.example.finalclick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class Store extends AppCompatActivity {
    private Button doubleButton, tripleButton, superButton, ultimateButton, heavenButton;
    private ImageView doubleBought, tripleBought, superBought, ultimateBought, heavenBought;
    //Check for update
    private boolean doubleUpdCheck;
    private boolean tripleUpdCheck;
    private boolean superUpdCheck;
    private boolean ultimateUpdCheck;
    private boolean heavenUpdCheck;
    //Alert dialog
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    //SharedPreferences
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Update buttons
        doubleButton = findViewById(R.id.doubleUpgrade);

        tripleButton = findViewById(R.id.tripleUpgrade);

        superButton = findViewById(R.id.superClick);

        ultimateButton = findViewById(R.id.ultimateClick);

        heavenButton = findViewById(R.id.heavenClick);
        //Staff button
        Button nullButton = findViewById(R.id.nullButton);
        //Check
        doubleBought = findViewById(R.id.doubleBought);

        tripleBought = findViewById(R.id.tripleBought);

        superBought = findViewById(R.id.superBought);

        ultimateBought = findViewById(R.id.ultimateBought);

        heavenBought = findViewById(R.id.heavenBought);

        loadSave();
        //Обработка покупки двойного клика
        doubleButton.setOnClickListener(view -> {
            int check = doubleCheck(MainActivity.userScore.getLevel(),
                MainActivity.userScore.getCoins());
            if(check == 1){
                preferences = getApplication().getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("coins", MainActivity.userScore.getCoins()-150);
                editor.putInt("coef", 2);
                editor.apply();
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-150);
                MainActivity.userScore.setCoef(2);
                doubleUpdCheck = true;
                doubleButton.setVisibility(View.GONE);
                doubleBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                "Ваш уровень слишком низок", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                "Недостаточно монет для разблокировки", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //Обработка покупки тройного клика
        tripleButton.setOnClickListener(view -> {
            int check = tripleCheck(MainActivity.userScore.getLevel(),
                    MainActivity.userScore.getCoins());
            if(check == 1){
                preferences = getApplication().getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("coins", MainActivity.userScore.getCoins()-600);
                editor.putInt("coef", 3);
                editor.apply();
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-600);
                MainActivity.userScore.setCoef(3);
                tripleUpdCheck = true;
                tripleButton.setVisibility(View.GONE);
                tripleBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш уровень слишком низок", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Недостаточно монет для разблокировки", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //Обработка покупки супер клика
        superButton.setOnClickListener(view -> {
            int check = superCheck(MainActivity.userScore.getLevel(),
                    MainActivity.userScore.getCoins());
            if(check == 1){
                preferences = getApplication().getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("coins", MainActivity.userScore.getCoins()-900);
                editor.putBoolean("isSuper", true);
                editor.apply();
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-900);
                MainActivity.userScore.setIsSuper(true);
                superUpdCheck = true;
                superButton.setVisibility(View.GONE);
                superBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш уровень слишком низок", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Недостаточно монет для разблокировки", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //Обработка покупки ультимейт клика
        ultimateButton.setOnClickListener(view -> {
            int check = ultimateCheck(MainActivity.userScore.getLevel(),
                    MainActivity.userScore.getCoins());
            if(check == 1){
                preferences = getApplication().getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("coins", MainActivity.userScore.getCoins()-3850);
                editor.putBoolean("isSuper", true);
                editor.putInt("chance", 2);
                editor.apply();
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-3850);
                MainActivity.userScore.setIsSuper(true);
                MainActivity.userScore.setChance(2);
                ultimateUpdCheck = true;
                ultimateButton.setVisibility(View.GONE);
                ultimateBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш уровень слишком низок", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Недостаточно монет для разблокировки", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //Обработка покупки небесного клика
        heavenButton.setOnClickListener(view -> {
            int check = heavenCheck(MainActivity.userScore.getLevel(),
                    MainActivity.userScore.getCoins());
            if(check == 1){
                preferences = getApplication().getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("coins", MainActivity.userScore.getCoins()-4050);
                editor.putInt("coef", 7);
                editor.apply();
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-4050);
                MainActivity.userScore.setChance(7);
                heavenUpdCheck = true;
                heavenButton.setVisibility(View.GONE);
                heavenBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш уровень слишком низок", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Недостаточно монет для разблокировки", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //Сброс всех характеристик
        nullButton.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Warning");
            builder.setMessage("Вы действительно хотите сбросить прогресс?");
            builder.setPositiveButton("Да", (dialogInterface, i) -> {
                doubleUpdCheck = false;
                tripleUpdCheck = false;
                superUpdCheck = false;
                ultimateUpdCheck = false;
                heavenUpdCheck = false;
                saveData();
                setVisibilities();
                preferences = getApplication().getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("coins", 0);
                editor.putInt("coef", 1);
                editor.putInt("level", 1);
                editor.putFloat("exp", 0);
                editor.putBoolean("isSuper", false);
                editor.putInt("chance", 3);
                editor.apply();
                MainActivity.userScore.setCoins(0);
                MainActivity.userScore.setCoef(1);
                MainActivity.userScore.setLevel(1);
                MainActivity.userScore.setExp(0);
                MainActivity.userScore.setIsSuper(false);
                MainActivity.userScore.setChance(3);
                infoUpdate();
            });
            builder.setNegativeButton("Отмена", (dialogInterface, i) -> dialog.dismiss());
            dialog = builder.create();
            dialog.show();
        });
    }

    private void coinsUpdate(){
        MainActivity.coinsView.setText("Coins: " + MainActivity.userScore.getCoins());
    }

    private void infoUpdate(){
        coinsUpdate();
        MainActivity.levelView.setText("Level: " + MainActivity.userScore.getLevel());
        MainActivity.progressView.setText("Progress: 0%");
    }

    private void setVisibilities(){
        //Button visibility
        doubleButton.setVisibility(View.VISIBLE);
        tripleButton.setVisibility(View.VISIBLE);
        superButton.setVisibility(View.VISIBLE);
        ultimateButton.setVisibility(View.VISIBLE);
        heavenButton.setVisibility(View.VISIBLE);
        //Check visibility
        doubleBought.setVisibility(View.GONE);
        tripleBought.setVisibility(View.GONE);
        superBought.setVisibility(View.GONE);
        ultimateBought.setVisibility(View.GONE);
        heavenBought.setVisibility(View.GONE);
    }

    private int doubleCheck(int level, int coins){
        if(level >= 3 && coins >= 150){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 3){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 150){
            return 3;
        }
        return 0;
    }

    private int tripleCheck(int level, int coins){
        if(level >= 5 && coins >= 600){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 5){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 600){
            return 3;
        }
        return 0;
    }

    private int superCheck(int level, int coins){
        if(level >= 7 && coins >= 900){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 7){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 900){
            return 3;
        }
        return 0;
    }

    private int ultimateCheck(int level, int coins){
        if(level >= 11 && coins >= 3850){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 11){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 3850){
            return 3;
        }
        return 0;
    }

    private int heavenCheck(int level, int coins){
        if(level >= 13 && coins >= 4050){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 13){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 4050){
            return 3;
        }
        return 0;
    }

    public void saveData(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("double", doubleUpdCheck);
        editor.putBoolean("triple", tripleUpdCheck);
        editor.putBoolean("super", superUpdCheck);
        editor.putBoolean("ultimate", ultimateUpdCheck);
        editor.putBoolean("heaven", heavenUpdCheck);
        editor.apply();
    }

    public void loadSave(){
        preferences = getPreferences(MODE_PRIVATE);
        doubleUpdCheck = preferences.getBoolean("double", false);
        tripleUpdCheck = preferences.getBoolean("triple", false);
        superUpdCheck = preferences.getBoolean("super", false);
        ultimateUpdCheck = preferences.getBoolean("ultimate", false);
        heavenUpdCheck = preferences.getBoolean("heaven", false);
        //Double button
        if (doubleUpdCheck){
            doubleBought.setVisibility(View.VISIBLE);
            doubleButton.setVisibility(View.GONE);
        }
        else{
            doubleBought.setVisibility(View.GONE);
            doubleButton.setVisibility(View.VISIBLE);
        }
        //Triple button
        if (tripleUpdCheck){
            tripleBought.setVisibility(View.VISIBLE);
            tripleButton.setVisibility(View.GONE);
        }
        else{
            tripleBought.setVisibility(View.GONE);
            tripleButton.setVisibility(View.VISIBLE);
        }
        //Super button
        if (superUpdCheck){
            superBought.setVisibility(View.VISIBLE);
            superButton.setVisibility(View.GONE);
        }
        else{
            superBought.setVisibility(View.GONE);
            superButton.setVisibility(View.VISIBLE);
        }
        //Ultimate button
        if (ultimateUpdCheck){
            ultimateBought.setVisibility(View.VISIBLE);
            ultimateButton.setVisibility(View.GONE);
        }
        else{
            ultimateBought.setVisibility(View.GONE);
            ultimateButton.setVisibility(View.VISIBLE);
        }
        //Heaven button
        if (heavenUpdCheck){
            heavenBought.setVisibility(View.VISIBLE);
            heavenButton.setVisibility(View.GONE);
        }
        else{
            heavenBought.setVisibility(View.GONE);
            heavenButton.setVisibility(View.VISIBLE);
        }
    }
}