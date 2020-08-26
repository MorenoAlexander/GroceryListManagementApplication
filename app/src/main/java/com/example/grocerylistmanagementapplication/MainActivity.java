package com.example.grocerylistmanagementapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final int USER_CHANGED_LIST = 777;
    public static final int USER_NOCHANGE_LIST =  -1;
    private static MainActivity currentInstance = null;

    public static final int START_GROCERYLIST_ACTIVITY_OPEN_NEW_LIST = 777;
    private static GroceryListList test = groceryObjectTestHelper.get();
    private static GroceryListList groceryListList = test;
    private static boolean  listChanged;
    private RecyclerView groceryListListView;



    private RecyclerView.Adapter groceryListListAdapter;
    private RecyclerView.LayoutManager topLevelLayoutManager;
    private AlertDialog addGroceryListDialog;

    /**
     * notify the main list that the data set has changed
     * USEAGE : To be called when data has changed
     */
    public void notifyGroceryListListDataSetChanged(){
        groceryListListAdapter.notifyDataSetChanged();
    }

    public static MainActivity getInstance(){
        return currentInstance;
    }

    public static void updateGroceryListList( int position, GroceryList changedList)
    {

        groceryListList.replaceList(position, changedList);
        App.writeToInternalFile(groceryListList);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        App.setAppContext(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentInstance = this;



        //TODO refactor dialog to prevent user from creating lists with empty fields
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add a new list", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater lf = MainActivity.this.getLayoutInflater();
                builder.setView(lf.inflate(R.layout.dialog_layout_add_grocery_list, null))
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String listName = ( (EditText) addGroceryListDialog.findViewById(R.id.groceryListName)).getText().toString();
                                String StoreName = ( (EditText) addGroceryListDialog.findViewById(R.id.groceryListStore)).getText().toString();

                                groceryListList.AddList(listName,StoreName);
                                App.writeToInternalFile(groceryListList);
                                groceryListListAdapter.notifyDataSetChanged();

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                addGroceryListDialog = builder.create();
                addGroceryListDialog.show();
            }
        });

        groceryListList = App.readFromInternalFile();
        if(groceryListList == null)
        {
            groceryListList = new GroceryListList();
        }

        groceryListListView = (RecyclerView) findViewById(R.id.GroceryListOfLists);


        groceryListListView.setHasFixedSize(true);

        topLevelLayoutManager = new LinearLayoutManager(this);
        groceryListListView.setLayoutManager(topLevelLayoutManager);

        groceryListListAdapter = new GroceryListListAdapter(MainActivity.this,groceryListList,groceryListListView);


        groceryListListView.setAdapter(groceryListListAdapter);
        registerForContextMenu(groceryListListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public static void startGroceryListActivity(Intent intent){
        currentInstance.startActivityForResult(intent,START_GROCERYLIST_ACTIVITY_OPEN_NEW_LIST);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data ){
        if( requestCode == START_GROCERYLIST_ACTIVITY_OPEN_NEW_LIST && resultCode == Activity.RESULT_OK)
        {
            GroceryList updatedList  = (GroceryList) data.getExtras().getSerializable("changedList");
            int changedListPosition = data.getExtras().getInt("position");

            groceryListList.replaceList(changedListPosition,updatedList);
            App.writeToInternalFile(groceryListList);
            groceryListListAdapter.notifyDataSetChanged();
        }


    }


    @Override
    public void onStop(){
        super.onStop();

    }


    @Override
    public boolean onContextItemSelected(MenuItem item){

         //position = 0;
        try{
           final int position = ((GroceryListListAdapter)groceryListListView.getAdapter()).getContextMenuSelectedItemPosition();


            switch (item.getItemId()){
                case 1: //Edit
                    AlertDialog.Builder editDialogue = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater lf = MainActivity.this.getLayoutInflater();
                    View v = lf.inflate(R.layout.dialog_layout_add_grocery_list, null);
                    final EditText listNameView = v.findViewById(R.id.groceryListName);
                    final EditText storeNameView = v.findViewById(R.id.groceryListStore);
                    listNameView.setText(groceryListList.getNameAt(position));
                    storeNameView.setText(groceryListList.getStoreNameAt(position));


                    editDialogue.setView(v)
                            .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //String StoreName = ( (EditText) addGroceryListDialog.findViewById(R.id.groceryListStore)).getText().toString();
                                    groceryListList.setListNameAt(position,listNameView.getText().toString());
                                    groceryListList.setStoreNameAt(position,storeNameView.getText().toString());

                                    groceryListListAdapter.notifyDataSetChanged();

                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    addGroceryListDialog = editDialogue.create();
                    addGroceryListDialog.show();
                    break;
                case 2:
                    AlertDialog.Builder check = new AlertDialog.Builder(MainActivity.this).setMessage("Are you sure you want to delete this list?").setPositiveButton(R.string.delete,new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog,int which){
                            groceryListList.removeList(position);
                            groceryListListAdapter.notifyDataSetChanged();

                        }
                    }).setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    check.create().show();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + item.getItemId());
            }
            return super.onContextItemSelected(item);

        }
        catch (Exception e){
            Log.d("ContextMenu", e.getLocalizedMessage());
            return super.onContextItemSelected(item);
        }
    }




}
