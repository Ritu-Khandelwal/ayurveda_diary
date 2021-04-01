package com.example.ayurvedadiary;

public class ProductData {
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private String itemPrice;
    private String key;

    public ProductData(String itemName, String itemDescription, String itemPrice, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public ProductData (){

    }



    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
