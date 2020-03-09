package com.example.grocerylistmanagementapplication;

import java.util.ArrayList;

public class GroceryListList {

    private ArrayList<GroceryList> groceryLists;


    public String  getNameAt(int pos){
        return groceryLists.get(pos).getStoreName();
    }
}
