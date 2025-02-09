package com.example.diabeszes;

public class Food {
    public int id;
    public double carbs;
    public int categroy, ebed, give, group, potvacsora, reggeli, tizorai, type, uzsonna, vacsora;
    public boolean gluten, laktoz, full;
    public String name, imageFilename;

    public Food() {}

    public Food(int id, double carbs, int categroy, int ebed, int give, int group, int potvacsora, int reggeli, int tizorai, int type, int uzsonna, int vacsora, boolean gluten, boolean laktoz, String name, String imageFilename) {
        this.id = id;
        this.carbs = carbs;
        this.categroy = categroy;
        this.ebed = ebed;
        this.give = give;
        this.group = group;
        this.potvacsora = potvacsora;
        this.reggeli = reggeli;
        this.tizorai = tizorai;
        this.type = type;
        this.uzsonna = uzsonna;
        this.vacsora = vacsora;
        this.gluten = gluten;
        this.laktoz = laktoz;
        this.name = name;
        this.imageFilename = imageFilename;
    }

    public Food copy() {
        Food f = new Food();
        f.id = id;
        f.carbs = carbs;
        f.categroy = categroy;
        f.ebed = ebed;
        f.give = give;
        f.group = group;
        f.potvacsora = potvacsora;
        f.reggeli = reggeli;
        f.tizorai = tizorai;
        f.type = type;
        f.uzsonna = uzsonna;
        f.vacsora = vacsora;
        f.gluten = gluten;
        f.laktoz = laktoz;
        f.name = name;
        f.imageFilename = imageFilename;
        return f;
    }
}
