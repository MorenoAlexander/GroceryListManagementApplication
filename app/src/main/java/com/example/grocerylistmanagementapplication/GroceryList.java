package com.example.grocerylistmanagementapplication;

import java.io.Serializable;
import java.util.ArrayList;

import groceryObjects.GroceryItem;
import groceryObjects.Meat;

public class GroceryList implements Serializable {
    private String listName;
    private String StoreName;


    private ArrayList<GroceryItem> list;

    public GroceryList(String name, String store) {
        list = new ArrayList<GroceryItem>();
        listName = name;
        StoreName = store;
    }


    public void AddItem(GroceryItem.type type,String Name,double cost,double weight,int quantity){
        switch(type){
            case MEAT:
                list.add(new Meat(Name, cost,weight,quantity));
                break;
            case BEVERAGE:
                //TODO implement Beverage
                break;
        }
    }


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
