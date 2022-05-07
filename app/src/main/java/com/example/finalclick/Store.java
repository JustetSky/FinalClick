package com.example.finalclick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Store extends AppCompatActivity {
    private Button doubleButton;
    private Button tripleButton;
    private Button superButton;
    private ImageView doubleBought, tripleBought, superBought;
    //Check for update
    private boolean doubleUpdCheck;
    private boolean tripleUpdCheck;
    private boolean superUpdCheck;
    //Alert dialog
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    //SharedPreferences
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        //Update buttons
        doubleButton = findViewById(R.id.doubleUpgrade);
        tripleButton = findViewById(R.id.tripleUpgrade);
        superButton = findViewById(R.id.superClick);
        //Null button
        Button nullButton = findViewById(R.id.nullButton);
        //Check
        doubleBought = findViewById(R.id.doubleBought);

        tripleBought = findViewById(R.id.tripleBought);

        superBought = findViewById(R.id.superBought);

        loadSave();

        doubleButton.setOnClickListener(view -> {
            int check = doubleCheck(MainActivity.userScore.getLevel(),
                MainActivity.userScore.getCoins());
            if(check == 1){
                MainActivity.userScore.setCoef(2);
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-100);
                doubleUpdCheck = true;
                doubleButton.setVisibility(View.GONE);
                doubleBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                "U need level 3 to unlock this", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                "U need 100 coins to unlock this", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        tripleButton.setOnClickListener(view -> {
            int check = tripleCheck(MainActivity.userScore.getLevel(),
                    MainActivity.userScore.getCoins());
            if(check == 1){
                MainActivity.userScore.setCoef(3);
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-250);
                tripleUpdCheck = true;
                tripleButton.setVisibility(View.GONE);
                tripleBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "U need level 4 to unlock this", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "U need 250 coins to unlock this", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        superButton.setOnClickListener(view -> {
            int check = superCheck(MainActivity.userScore.getLevel(),
                    MainActivity.userScore.getCoins());
            if(check == 1){
                MainActivity.userScore.setIsSuper(true);
                MainActivity.userScore.setCoins(MainActivity.userScore.getCoins()-550);
                superUpdCheck = true;
                superButton.setVisibility(View.GONE);
                superBought.setVisibility(View.VISIBLE);
                coinsUpdate();
                saveData();
            }
            else if(check == 2){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "U need level 6 to unlock this", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(check == 3){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "U need 550 coins to unlock this", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        nullButton.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Warning");
            builder.setMessage("Are u really want to NULL ur progress?");
            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                doubleUpdCheck = false;
                tripleUpdCheck = false;
                superUpdCheck = false;
                saveData();
                setVisibilities();
                MainActivity.userScore.setCoins(0);
                MainActivity.userScore.setLevel(1);
                MainActivity.userScore.setCoef(1);
                MainActivity.userScore.setExp(0);
                MainActivity.userScore.setProgress(0);
                infoUpdate();
            });
            builder.setNegativeButton("cancel", (dialogInterface, i) -> dialog.dismiss());
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
        //Check visibility
        doubleBought.setVisibility(View.GONE);
        tripleBought.setVisibility(View.GONE);
        superBought.setVisibility(View.GONE);
    }

    private int doubleCheck(int level, int coins){
        if(level >= 3 && coins >= 100 &&  !doubleUpdCheck){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 3){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 100){
            return 3;
        }
        return 0;
    }

    private int tripleCheck(int level, int coins){
        if(level >= 4 && coins >= 250 &&  !tripleUpdCheck){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 4){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 250){
            return 3;
        }
        return 0;
    }

    private int superCheck(int level, int coins){
        if(level >= 6 && coins >= 550 &&  !superUpdCheck){
            return 1;
        }
        else if(MainActivity.userScore.getLevel() < 6){
            return 2;
        }
        else if(MainActivity.userScore.getCoins() < 550){
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
        editor.commit();
    }

    public void loadSave(){
        preferences = getPreferences(MODE_PRIVATE);
        doubleUpdCheck = preferences.getBoolean("double", false);
        tripleUpdCheck = preferences.getBoolean("triple", false);
        superUpdCheck = preferences.getBoolean("super", false);
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
    }
}