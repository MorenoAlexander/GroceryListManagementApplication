package com.example.grocerylistmanagementapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import groceryObjects.GroceryItem;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder>{
    private GroceryList list;

    public static class GroceryViewHolder extends RecyclerView.ViewHolder {
        /* Define Grocery view objects

         */
        View v;
        TextView textViewName;
        TextView textViewPrice;
        TextView textViewQuantity;
        TextView textViewTotalPrice;
        TextView textViewPricePerOz;



        public GroceryViewHolder(View v,TextView Name, TextView Price, TextView Quantity, TextView TotalPrice, TextView PricePerOz)
        {
            super(v);
            textViewName = Name;
            textViewPrice = Price;
            textViewQuantity = Quantity;
            textViewTotalPrice = TotalPrice;
            textViewPricePerOz = PricePerOz;
        }
    }

    public GroceryListAdapter(GroceryList l){
        list = l;

    }

    @Override
    public GroceryListAdapter.GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater lI = LayoutInflater.from(parent.getContext());
        View v = (View) lI.inflate(R.layout.grocery_list_item_view_layout,parent,false);
        TextView Name = (TextView) v.findViewById(R.id.groceryItemViewName);
        TextView Price = (TextView) v.findViewById(R.id.groceryItemViewPrice);
        TextView Quantity = (TextView) v.findViewById(R.id.groceryItemViewQuantity);
        TextView totalPrice = (TextView) v.findViewById(R.id.grocerItemViewTotalPrice);
        //PricePerOz is unused for now.
        GroceryViewHolder vh = new GroceryViewHolder(v,Name,Price,Quantity,totalPrice,null);
        return vh;
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder VH, int position){
        GroceryItem gi = list.getGroceryItem(position);
        VH.textViewName.setText(gi.getName());
        VH.textViewPrice.setText( "$"+ Double.toString(gi.getCost())+ " each");
        VH.textViewQuantity.setText(Integer.toString(gi.getQuantity()));
        VH.textViewTotalPrice.setText("Total: $" + Double.toString(gi.getTotalCost()));
    }

    @Override
    public int getItemCount() { return list.length(); }
}
