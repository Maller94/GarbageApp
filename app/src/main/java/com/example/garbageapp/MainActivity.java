package com.example.garbageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //Shopping V1

    // GUI variables
    private Button whereB, addbtn, clearbtn;
    private EditText items;

    // Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsDB = ItemsDB.get(MainActivity.this);

        items = findViewById(R.id.inputField);

        // Locate specific item
        whereB = findViewById(R.id.where);
        whereB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Empty input field
                String getInputText = items.getText().toString().toLowerCase();
                String foundItem = "";
                for (Map.Entry<String, String> item : itemsDB.getItemsDB().entrySet()) {
                    if (getInputText.equals(item.getKey())) {
                        foundItem = item.getValue();
                    }
                }

                if (foundItem.length() == 0) {
                    items.setText(getInputText + ": cannot find location");
                } else {
                    items.setText(getInputText + " -> " + foundItem);
                }

            }

        });

        // Navigates to add_item activity
        addbtn = findViewById(R.id.main_add_new_item);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddItem.class);
                startActivity(intent);
            }
        });


        // Clears the edit text field
        clearbtn = findViewById(R.id.clear);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.getText().clear();
            }
        });
    }
}
