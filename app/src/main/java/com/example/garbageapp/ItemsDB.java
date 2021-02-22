package com.example.garbageapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private final HashMap<String, String> itemsDB = new HashMap<String, String>();
    private static Context sContext;

    public static ItemsDB get(Context context) {
        if (sItemsDB == null) {
            sContext = context;
            sItemsDB = new ItemsDB(context);
        }
        return sItemsDB;
    }

    public ItemsDB(Context context) {
        fillItemsDB("garbage.txt");
    }

    /*
    * Will be used later in the course (probably)
    *
    public String listItems() {
        String r = "";
        for (Map.Entry<String, String> item : itemsDB.entrySet())
            r = r + "\n Sort " + item.getKey() + " in: " + item.getValue();
        return r;
    }*/

    public void addItem(String what, String where) {
        itemsDB.put(what, where);
    }

    public HashMap<String, String> getItemsDB() {
        return itemsDB;
    }

    private void fillItemsDB(String filename) {
        BufferedReader reader = null;
        try {
            // Fetching the text file from assets
            reader = new BufferedReader(
                    new InputStreamReader(sContext.getAssets().open(filename), "UTF-8"));
            // Reading lines from the text file
            String line = reader.readLine();
            while (line != null) {
                String[] gItem = line.split(",");
                gItem[1] = gItem[1].trim();
                itemsDB.put(gItem[0].trim(), gItem[1]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }
}
