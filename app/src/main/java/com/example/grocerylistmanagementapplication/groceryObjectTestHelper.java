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
        testListofLists.AddList("Weekly groceries","Aldi's");
        //Create Simple meat list for recently created Aldi's list
        testListofLists.AddItemToList(0, GroceryItem.type.MEAT,"Sirloin Steak",35.67,15,2);


        testListofLists.AddList("Christmas Party", "Walmart");
        testListofLists.AddItemToList(1,GroceryItem.type.MEAT,"T-Bone Steak",17.0, 2,2);
        return testListofLists;
    }
}
