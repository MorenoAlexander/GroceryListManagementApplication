package com.example.grocerylistmanagementapplication;


import groceryObjects.*;


public class groceryObjectTestHelper {

    public static GroceryListList testListofLists = null;


    public static GroceryListList get() {

        if(testListofLists == null){
            testListofLists = GenerateTestList();
        }
        return testListofLists;
    }


    private static GroceryListList GenerateTestList(){
        testListofLists = new GroceryListList();
        //Create new list for Aldi's
        testListofLists.AddList("Weekly groceries","Adli's");
        //Create Simple meat list for recently created Aldi's list
        testListofLists.AddItemToList(0, GroceryItem.type.MEAT,"Sirloin Steak",35.67,15,2);
        return testListofLists;
    }
}
