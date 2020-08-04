package com.example.materialtest;


public class Pirate {
    private String name;
    private int imageId;
    public Pirate(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public  String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}