package com.example.aysarproject.model;

public class ShopItemModel {

    private String Id;
    private String ImageURL;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Name;
    private String NameAr;
    private String Description;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameAr() {
        return NameAr;
    }

    public void setNameAr(String nameAr) {
        NameAr = nameAr;
    }
}
