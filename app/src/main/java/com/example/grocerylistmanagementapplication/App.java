package com.example.grocerylistmanagementapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class App extends Application {
    public static void setAppContext(Context appContext) {
        App.appContext = appContext;
    }

    private static Context  appContext;
    private static App AppInstance;
    private static final String USER_FILE_DIR = "user_grocery_data";
    private static final String user_groceryList_filename = "user_grocery_list_filename.ser";

    private static File directory;
    private static File userListFile;
    private static FileOutputStream userFos;


    //TODO remove in production
    public static void complain(){
        Log.d("complaint","goddamnit!");
    }








    private boolean CheckForInternalFile()
    {
        directory = getDir(USER_FILE_DIR, MODE_PRIVATE);
        if (!directory.exists() )
        {
            directory.mkdir();
        }

        /*
        try{
            //userFos = openFileOutput(user_groceryList_filename,MODE_PRIVATE);

            //userFos.

            //userFos.close();
            return true;
        }
        //catch(FileNotFoundException e )
        {
            Log.d("USER FILE","FileNotFoundException ");
            return false;
        }
        //catch (IOException e)
        {
            return false;
        }

         */
        return true;
    }


    public static boolean writeToInternalFile(GroceryListList gll){

        try
        {
            FileOutputStream fileOutputStream = appContext.openFileOutput(user_groceryList_filename,MODE_PRIVATE);


            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(gll);
            oos.flush();
            oos.close();
            fileOutputStream.close();

            return true;
        }
        catch (IOException e)
        {
            Log.d("USER FILE", "could not write object to file");
        }
        return false;
    }

    public static GroceryListList readFromInternalFile(){
        try
        {
            FileInputStream fis = appContext.openFileInput(user_groceryList_filename);
            InputStream buffer = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(buffer);
            try{
                GroceryListList gll = (GroceryListList) ois.readObject();
                ois.close();
                buffer.close();
                fis.close();
                return gll;
            }
            catch(ClassNotFoundException e)
            {
                Log.d("USER FILE read", "Class not found exception??");
                //return null;
            }

        }
        catch (IOException e)
        {
            Log.d("USER FILE read", "could not read from file");
            //return null;
        }

        return null;
    }




    @Override
    public void onCreate(){
        super.onCreate();
        //Check for the file or create it if it doesn't exist
        if( CheckForInternalFile() ){

        }
        else{

            //or what
        }
    }






}
