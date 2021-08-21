package com.example.grocerylistmanagementapplication

import android.app.Application
import android.content.Context
import android.util.Log
import java.io.*

class App : Application() {
    private fun CheckForInternalFile(): Boolean {
        val directory = getDir(USER_FILE_DIR, MODE_PRIVATE)
        if (!directory.exists()) {
            directory.mkdir()
        }
        return true
    }

    override fun onCreate() {
        super.onCreate()
        //Check for the file or create it if it doesn't exist
        if (CheckForInternalFile()) {
        } else {

            //or what
        }
    }

    companion object {
        @JvmStatic
        fun setAppContext(appContext: Context?) {
            Companion.appContext = appContext
        }

        private var appContext: Context? = null
        private const val USER_FILE_DIR = "user_grocery_data"
        private const val user_groceryList_filename = "user_grocery_list_filename.ser"
        private var directory: File? = null
        private val userListFile: File? = null
        private val userFos: FileOutputStream? = null


        @JvmStatic
        fun writeToInternalFile(gll: GroceryListList?): Boolean {
            try {
                val fileOutputStream = appContext!!.openFileOutput(user_groceryList_filename, MODE_PRIVATE)
                val oos = ObjectOutputStream(fileOutputStream)
                oos.writeObject(gll)
                oos.flush()
                oos.close()
                fileOutputStream.close()
                return true
            } catch (e: IOException) {
                Log.d("USER FILE", "could not write object to file")
            }
            return false
        }

        @JvmStatic
        fun readFromInternalFile(): GroceryListList? {
            try {
                val fis = appContext!!.openFileInput(user_groceryList_filename)
                val buffer: InputStream = BufferedInputStream(fis)
                val ois = ObjectInputStream(buffer)
                try {
                    val gll = ois.readObject() as GroceryListList
                    ois.close()
                    buffer.close()
                    fis.close()
                    return gll
                } catch (e: ClassNotFoundException) {
                    Log.d("USER FILE read", "Class not found exception??")
                    //return null;
                }
            } catch (e: IOException) {
                Log.d("USER FILE read", "could not read from file")
                //return null;
            }
            return null
        }
    }
}