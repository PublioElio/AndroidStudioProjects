package com.example.ejercicio_06;

public class Data {

    private final String heroId;
    private final int heroImg;

    public Data(int heroImg, String heroId) {
        this.heroImg = heroImg;
        this.heroId = heroId;
    }
    public String getheroId() {
        return heroId;
    }

    public int getHeroImg() { return heroImg; }

}
