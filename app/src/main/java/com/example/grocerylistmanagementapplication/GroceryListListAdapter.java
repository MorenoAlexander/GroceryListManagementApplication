package com.example.grocerylistmanagementapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import groceryObjects.*;

import androidx.recyclerview.widget.RecyclerView;

public class GroceryListListAdapter extends RecyclerView.Adapter<GroceryListListAdapter.GroceryListViewHolder> {
    private GroceryListList list;



    public static class GroceryListViewHolder extends RecyclerView.ViewHolder{

        public TextView viewName;
        public TextView viewStoreName;
        public TextView viewNumItem;

        public GroceryListViewHolder(View v,TextView name,TextView storeName,TextView NumItems) {
            super(v);
            viewName = name;
            viewStoreName = storeName;
            viewNumItem = NumItems;

        }
    }

    public GroceryListListAdapter(GroceryListList l) {
        list = l;
    }

    @Override
    public GroceryListListAdapter.GroceryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater lI = LayoutInflater.from(parent.getContext());
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_list_of_lists_item_layout,parent,false);

        TextView listName  = (TextView) v.findViewById(R.id.grocerListListItemName);
        TextView storeName = (TextView) v.findViewById(R.id.groceryListListItemStoreName);
        TextView numItems = (TextView) v.findViewById(R.id.groceryListListItemNumberOfItems);
        GroceryListViewHolder vh = new GroceryListViewHolder(v,listName,storeName,numItems);
    }


    @Override
    public void onBindViewHolder(GroceryListViewHolder VH,int position){
        VH.viewName.setText(list.getNameAt(position));
        VH.viewStoreName.setText(list.getStoreNameAt(position));
        VH.viewNumItem.setText(list.getListCountAt(position));

    }




}
