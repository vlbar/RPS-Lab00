package com.vlb.lab00.mainapp;

public class Plan {
    private String name;
    private float storage; //in gb
    private float ram; //in gb
    private float badwidth; //in tb
    private int websiteCount;
    private int domianCount;
    private boolean isHaveSupport;
    private double price; //coutry - price

    public Plan(String name, float storage, float ram, float badwidth) {
        this.name = name;
        this.storage = storage;
        this.ram = ram;
        this.badwidth = badwidth;
    }

    public Plan(String name, float storage, float ram, float badwidth, int websiteCount, int domianCount, boolean isHaveSupport, double price) {
        this.name = name;
        this.storage = storage;
        this.ram = ram;
        this.badwidth = badwidth;
        this.websiteCount = websiteCount;
        this.domianCount = domianCount;
        this.isHaveSupport = isHaveSupport;
        this.price = price;
    }

    public String getName() { return name; }
    public float getStorage() { return storage; }
    public float getRAM() { return ram; }
    public float getBadwidth() { return badwidth; }
    public int getWebsiteCount() { return websiteCount; }
    public int getDomianCount() { return domianCount; }
    public boolean isIncludeSupport() { return isHaveSupport; }
    public double getPrice() { return price; }
}