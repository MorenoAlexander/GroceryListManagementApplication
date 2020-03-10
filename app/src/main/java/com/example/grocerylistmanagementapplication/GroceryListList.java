package com.example.grocerylistmanagementapplication;

import java.io.Serializable;
import java.util.ArrayList;

import groceryObjects.GroceryItem;

public class GroceryListList implements Serializable {

    private ArrayList<GroceryList> groceryLists;


    public GroceryListList(){
        groceryLists = new ArrayList<GroceryList>();
    }

    public void AddList(String name,String store){
        groceryLists.add(new GroceryList(name,store));
    }

    public void AddItemToList(int pos, GroceryItem.type type, String Name, double cost, double wieght, int quantity){
        groceryLists.get(pos).AddItem(type, Name, cost, wieght,quantity);
    }


    public String  getNameAt(int pos){
        return groceryLists.get(pos).getListName();
    }

    public String getStoreNameAt(int pos) {
        return groceryLists.get(pos).getStoreName();
    }

    public String getListCountAt(int pos){
        return Integer.toString(groceryLists.get(pos).getCount());
    }

    public int length() {
        return groceryLists.size();
    }
}
