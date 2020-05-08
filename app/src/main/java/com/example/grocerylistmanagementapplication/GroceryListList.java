package com.example.grocerylistmanagementapplication;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

import groceryObjects.GroceryItem;

public class GroceryListList implements Serializable {

    private ArrayList<GroceryList> groceryLists;


    /***
     * One and only constructor that initializes the new array of grocery lists
     * RETURNS: refence to new List of Grocery Lists
     *
     */
    public GroceryListList(){
        groceryLists = new ArrayList<GroceryList>();
    }


    /***
     * Adds a new & empty list to the list of grocery lists
     * @param name
     * @param store
     */
    public void AddList(String name,String store){
        groceryLists.add(new GroceryList(name,store));
    }


    public void removeList(int pos){
        groceryLists.remove(pos);
    }

    /***
     *
     * @param pos position of the grocery list that we're modifiyng
     * @param type type of the product (products are stored in GroceryItem class)
     * @param Name name of the desired product
     * @param cost cost of the desired product
     * @param weight weight of the item (used to calculate $/(v/lb)
     * @param quantity
     */
    public void AddItemToList(int pos, GroceryItem.type type, String Name, double cost, double weight, int quantity){
        groceryLists.get(pos).AddItem(type, Name, cost, weight,quantity);
    }

    /**
     *
     * @param pos
     * @return
     */
    public String  getNameAt(int pos){
        return groceryLists.get(pos).getListName();
    }


    /**
     *
     * @param pos
     * @return
     */
    public String getStoreNameAt(int pos) {
        return groceryLists.get(pos).getStoreName();
    }

    /**
     *
     * @param pos
     * @return
     */

    public void setStoreNameAt(int pos,String newName) {
        groceryLists.get(pos).setStoreName(newName);

    }
    public void setListNameAt(int pos, String newName){
        groceryLists.get(pos).setListName(newName);
    }


    public String getListCountAt(int pos){
        return Integer.toString(groceryLists.get(pos).getCount());
    }

    /**
     *
     * @param position
     * @return
     */
    public GroceryList getListAt(int position) {
        return groceryLists.get(position);
    }

    /**
     *
     * @return integer representing the length of the list duh
     */
    public int length() {
        return groceryLists.size();
    }


    /**
     *
     * @param position
     * @param newList updated list to placed at position
     * @return
     */
    public int replaceList(int position, GroceryList newList) {
        try{
            groceryLists.set(position,newList);
        }
        catch (IndexOutOfBoundsException e)
        {
            Log.d("ERROR GroceryList", e.getStackTrace().toString());
        }

        return 1;

    }







}
