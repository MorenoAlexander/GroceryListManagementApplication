package com.example.grocerylistmanagementapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Object;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GroceryListListAdapter extends RecyclerView.Adapter<GroceryListListAdapter.GroceryListViewHolder> {
    public int getContextMenuSelectedItemPosition() {
        return contextMenuSelectedItemPosition;
    }

    public void setContextMenuSelectedItemPosition(int contextMenuSelectedItemPosition) {
        this.contextMenuSelectedItemPosition = contextMenuSelectedItemPosition;
    }

    private int contextMenuSelectedItemPosition;
    private GroceryListList list;


    private final RecyclerView myRecyclerView;
    private final Context mContext;



    private final View.OnClickListener itemOnClickListener =  new GroceryItemOnClickListener();
    public View.OnLongClickListener itemOnLongClickListener;



    public static class GroceryListViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public TextView viewName;
        public TextView viewStoreName;
        public TextView viewNumItem;
        public TextView viewListPrice;

        public GroceryListViewHolder(View v,TextView name,TextView storeName,TextView NumItems, TextView ListPrice) {
            super(v);
            viewName = name;
            viewStoreName = storeName;
            viewNumItem = NumItems;
            viewListPrice = ListPrice;
            v.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

            menu.setHeaderTitle("Select Action");
            menu.add(ContextMenu.NONE,1,ContextMenu.NONE,"Edit");
            menu.add(0,2,1,"Delete");
        }
    }

    public GroceryListListAdapter(Context context, GroceryListList l, RecyclerView RV) {
        list = l;
        myRecyclerView = RV;
        mContext = context;
    }

    @Override
    public GroceryListListAdapter.GroceryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater lI = LayoutInflater.from(parent.getContext());
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_list_of_lists_item_layout,parent,false);
        v.setOnClickListener(itemOnClickListener);
        TextView listName  = (TextView) v.findViewById(R.id.groceryListListItemName);
        TextView storeName = (TextView) v.findViewById(R.id.groceryListListItemStoreName);
        TextView numItems = (TextView) v.findViewById(R.id.groceryListListItemNumberOfItems);
        TextView listPrice = (TextView) v.findViewById(R.id.TotalCost);
        GroceryListViewHolder vh = new GroceryListViewHolder(v,listName,storeName,numItems,listPrice);

        return vh;
    }


    @Override
    public void onBindViewHolder(final GroceryListViewHolder VH,int position){
        VH.viewName.setText(list.getNameAt(position));
        VH.viewStoreName.setText(list.getStoreNameAt(position));
        VH.viewNumItem.setText(list.getListCountAt(position) + " ITEMS"); //TODO create string in strings.xml
        VH.viewListPrice.setText(mContext.getString(R.string.list_list_listTotalCost,list.getListAt(position).getTotalCost()));

        VH.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                setContextMenuSelectedItemPosition(VH.getAdapterPosition());
                return false;
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.length();
    }


    private class GroceryItemOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View view){
            int groceryListPosition = myRecyclerView.getChildLayoutPosition(view);
            Toast.makeText(mContext,"List selected: "+ list.getNameAt(groceryListPosition),Toast.LENGTH_LONG).show();

            Intent editViewList = new Intent(mContext, GroceryListViewActivity.class);
            editViewList.putExtra("list",list.getListAt(groceryListPosition));
            editViewList.putExtra("contextMenuSelectedItemPosition",groceryListPosition);
            MainActivity.startGroceryListActivity(editViewList);
        }
    }


    //@TODO REMOVE IF NEVER CALLED IN FINAL FORM
    public void setItemOnLongClickListener(View.OnLongClickListener itemOnLongClickListener) {
        this.itemOnLongClickListener = itemOnLongClickListener;
    }
}
