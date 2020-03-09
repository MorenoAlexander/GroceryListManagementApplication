package com.example.grocerylistmanagementapplication;

import java.io.Serializable;
import java.util.ArrayList;

import groceryObjects.GroceryItem;

public class GroceryList implements Serializable {
    private String listName;
    private String StoreName;


    private ArrayList<GroceryItem> list;


    public int getCount(){
        int sum = 0;
        for (GroceryItem i : list){
            sum += i.getQuantity();
        }
        return sum;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }
}
