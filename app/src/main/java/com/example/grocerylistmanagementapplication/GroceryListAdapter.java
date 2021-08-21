package com.example.grocerylistmanagementapplication;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import groceryObjects.GroceryItem;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder>{
    public int getContextMenuSelectedItemPosition(){
        return contextMenuSelectedItemPosition;

    }




    public void setContextMenuSelectedItemPosition(int contextMenuSelectedItemPosition) {
        this.contextMenuSelectedItemPosition = contextMenuSelectedItemPosition;
    }
    private final GroceryList list;
    private int contextMenuSelectedItemPosition;

    public static class GroceryViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
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
            v.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
            menu.setHeaderTitle("Select Action");
            menu.add(ContextMenu.NONE, 1, ContextMenu.NONE,"Edit");
            menu.add(0, 2, 1,"Delete");
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
    public void onBindViewHolder(final GroceryViewHolder VH, int position){
        GroceryItem gi = list.getGroceryItem(position);
        VH.textViewName.setText(gi.getName());
        VH.textViewPrice.setText(String.format("$%s each", gi.getCost()));
        VH.textViewQuantity.setText(Integer.toString(gi.getQuantity()));
        VH.textViewTotalPrice.setText("Total: $" + gi.getTotalCost());

        VH.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v){
                setContextMenuSelectedItemPosition(VH.getAdapterPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() { return list.length(); }
}
