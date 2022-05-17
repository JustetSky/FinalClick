package com.example.finalclick;

public class UserScore{
    //Statistics
    private int coins = 0;
    private int coef = 1;
    private float exp = 0;
    private int level;
    private float progress = 0;
    private boolean isSuper = false;
    private int chance = 3;

    public void Click() {
        if (isSuper){
            double a = 1 + Math.random()*10;
            a = Math.round(a);
            if (a % chance == 0){
                coins += coef * 5;
                exp += coef * 5 * 25;
            }
            else{
                coins += coef;
                exp += coef * 25;
            }
        }
        else{
            coins += coef;
            exp += coef * 25;
        }
    }

    //Getters
    public int getCoins() {
        return coins;
    }

    public float getExp() {
        return exp;
    }

    public int getCoef() {
        return coef;
    }

    public int getLevel(){
        return level;
    }

    public float getProgress() {
        return progress;
    }

    public boolean getIsSuper() {
        return isSuper;
    }

    public int getChance(){
        return chance;
    }

    //Setters
    public void setCoef(int coef) {
        this.coef = coef;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setIsSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
}
