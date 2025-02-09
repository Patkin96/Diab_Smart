package com.example.diabeszes;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Background {
    private final Context context;
    private UserData userData;
    private ArrayList<Food> foods = new ArrayList<>();
    public  ArrayList<Food> storedFoods = new ArrayList<>();;
    public int currentMeal = 0;
    public double currentCarbs = 0;

    public Background(Context context) {
        this.context = context;
        userData = new UserData();
        try {
            loadJsonData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadJsonData() throws JSONException {
        JSONObject jsonObj = new JSONObject(readJSONFromAsset());
        JSONArray foods = jsonObj.getJSONArray("foods");

        for (int i = 0; i < foods.length(); i++) {
            JSONObject f = foods.getJSONObject(i);
            int id = Integer.parseInt(f.getString("imageFilename").split("\\.")[0]);
            this.foods.add(new Food(
                    id,
                    f.getDouble("amountOfCarbs"),
                    f.getInt("category"),
                    f.getInt("ebed"),
                    f.getInt("give"),
                    f.getInt("group"),
                    f.getInt("potvacsora"),
                    f.getInt("reggeli"),
                    f.getInt("tizorai"),
                    f.getInt("type"),
                    f.getInt("uzsonna"),
                    f.getInt("vacsora"),
                    f.getBoolean("gluten"),
                    f.getBoolean("laktoz"),
                    f.getString("name"),
                    "plate"+id
            ));
        }
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("foods.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void currentMealUp() {
        resetStorage();
        currentMeal = currentMeal+1;
    }

    public void currentMealDown() {
        resetStorage();
        currentMeal = currentMeal-1;
    }

    public double getCalorieOfIndex(int id) {
        return getFood(id).carbs;
    }

    public void resetStorage() {
        storedFoods = new ArrayList<>();
        currentCarbs = 0;
    }

    private static class UserData {
        ArrayList<Meal> meals = new ArrayList<>();

        public UserData() {
            meals.add(new Meal("Reggeli", 100, 15));
            meals.add(new Meal("Tízórai", 100, 15));
            meals.add(new Meal("Ebéd", 100, 40));
            meals.add(new Meal("Uzsonna", 100, 20));
            meals.add(new Meal("Vacsora", 100, 30));
            meals.add(new Meal("Pótvacsora", 100, 10));
        }

        public Meal getMeal(int index) {
            return meals.get(index);
        }
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    private Food getStoredFood(int id, boolean full) {
        Food food = null;
        for (Food f: storedFoods) {
            if (f.id == id && f.full == full) {
                food = f;
                break;
            }
        }
        return food;
    }

    private Food getFood(int id) {
        Food food = null;
        for (Food f: foods) {
            if (f.id == id) {
                food = f;
                break;
            }
        }

        return food;
    }

    public int getFoodImageResource(int id) {
        Food f = getFood(id);
        return context.getResources().getIdentifier(f.imageFilename, "drawable", context.getPackageName());
    }

    public Meal getMeal(int index) {
        return userData.getMeal(index);
    }

    public void incCurrentCarbs(int id, boolean full) {
        Food f = getFood(id).copy();
        f.full = full;
        this.currentCarbs += f.full ? f.carbs : f.carbs/2;
        storedFoods.add(f);
    }

    public void decCurrentCarbs(int id, boolean full) {
        Food f = getStoredFood(id, full);
        this.currentCarbs -= full ? f.carbs : f.carbs/2;
        storedFoods.remove(f);
    }
}
