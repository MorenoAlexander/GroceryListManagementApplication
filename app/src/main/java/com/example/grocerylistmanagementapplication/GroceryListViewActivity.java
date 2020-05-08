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

import android.view.LayoutInflater;
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
        return false;
    }
}
