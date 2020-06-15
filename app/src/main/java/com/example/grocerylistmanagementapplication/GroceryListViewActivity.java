package com.example.grocerylistmanagementapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import groceryObjects.GroceryItem;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class GroceryListViewActivity extends AppCompatActivity {


    private GroceryList currentList;
    private int currentListPosition;
    private AlertDialog groceryItemDialog;

    private RecyclerView groceryListView;
    private RecyclerView.Adapter groceryListAdapter;
    private RecyclerView.LayoutManager topLevelLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentList =  (GroceryList) getIntent().getExtras().getSerializable("list");
        currentListPosition = getIntent().getExtras().getInt("position");



        groceryListView  = (RecyclerView) findViewById(R.id.GroceryListView);

        groceryListView.setHasFixedSize(true);

        topLevelLayoutManager = new LinearLayoutManager(this);
        groceryListView.setLayoutManager(topLevelLayoutManager);
        groceryListAdapter = new GroceryListAdapter(currentList);

        groceryListView.setAdapter(groceryListAdapter);

        

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add a new grocery item", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(GroceryListViewActivity.this);
                LayoutInflater lf = GroceryListViewActivity.this.getLayoutInflater();
                builder.setView(lf.inflate(R.layout.dialog_layout_add_grocery_item,null))
                        .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                String Text =  ((EditText) groceryItemDialog.findViewById(R.id.groceryInputName)).getText().toString();
                                double price = Double.parseDouble(((EditText) groceryItemDialog.findViewById(R.id.groceryInputPrice)).getText().toString() );
                                int quantity = Integer.parseInt(((EditText) groceryItemDialog.findViewById(R.id.groceryInputQuantity)).getText().toString() );

                                currentList.AddItem(GroceryItem.type.MEAT,Text,price,1.0,quantity);
                                groceryListAdapter.notifyDataSetChanged();

                                MainActivity.getInstance().notifyGroceryListListDataSetChanged();

                            }
                        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                groceryItemDialog = builder.create();
                groceryItemDialog.show();
            }
        });
    }


    @Override
    public boolean  onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_grocerylist_view_activity,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //@TODO implement proper options AND settings page perhaps
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }










    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Returning to main activity",Toast.LENGTH_LONG).show();

        Intent result =  new Intent();
        result.putExtra("position",currentListPosition);
        result.putExtra("changedList",currentList);
        setResult(Activity.RESULT_OK,result);
        super.onBackPressed();
    }



    //@TODO REMOVE
    @Override
    public void finish()
    {
        Intent result =  new Intent();
        result.putExtra("position",currentListPosition);
        result.putExtra("changedList",currentList);
        setResult(Activity.RESULT_OK,result);
        super.finish();
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        try{
            final int position = ((GroceryListAdapter) groceryListView.getAdapter()).getContextMenuSelectedItemPosition();

            switch(item.getItemId()){
                case 1:
                    AlertDialog.Builder editDialogue = new AlertDialog.Builder(GroceryListViewActivity.this);
                    LayoutInflater lf = GroceryListViewActivity.this.getLayoutInflater();
                    View v = lf.inflate(R.layout.dialog_layout_add_grocery_item,null);
                    final EditText groceryItemNameView = v.findViewById(R.id.groceryInputName);
                    final EditText groceryInputPriceView = v.findViewById(R.id.groceryInputPrice);
                    final EditText groceryInputQuantityView = v.findViewById(R.id.groceryInputQuantity);
                    groceryItemNameView.setText(currentList.getGroceryItem(position).getName());
                    groceryInputPriceView.setText(Double.toString(currentList.getGroceryItem(position).getCost()));
                    groceryInputQuantityView.setText(Integer.toString(currentList.getGroceryItem(position).getQuantity()));

                    editDialogue.setView(v).setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            currentList.getGroceryItem(position).setName(groceryItemNameView.getText().toString());
                            currentList.getGroceryItem(position).setCost(Double.parseDouble(groceryInputPriceView.getText().toString()));
                            currentList.getGroceryItem(position).setQuantity(Integer.parseInt(groceryInputQuantityView.getText().toString()));
                            groceryListAdapter.notifyDataSetChanged();

                        }
                    })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    editDialogue.create().show();
                    break;
                case 2:
                    AlertDialog.Builder check = new AlertDialog.Builder(GroceryListViewActivity.this).setMessage("Are you sure you want to deleter this item?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    currentList.removeGrocery(position);
                                    groceryListAdapter.notifyDataSetChanged();
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    check.create().show();

                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: "+ item.getItemId());
            }
            return super.onContextItemSelected(item);
        }
        catch(Exception e){
            Log.d("ContextMenu", e.getLocalizedMessage());
            return super.onContextItemSelected(item);
        }
    }
}
