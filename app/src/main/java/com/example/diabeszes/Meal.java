package com.example.diabeszes;

public class Meal {
    private String name;
    private double maxCarbs, recCarbs;

    public Meal() {}

    public Meal(String name, double maxCarbs, double recCarbs) {
        this.name = name;
        this.maxCarbs = maxCarbs;
        this.recCarbs = recCarbs;
    }

    public String getName() {
        return name;
    }

    public double getMaxCarbs() {
        return maxCarbs;
    }

    public double getRecCarbs() {
        return recCarbs;
    }
}
