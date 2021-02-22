package com.example.garbageapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity {
    private static ItemsDB itemsDB;

    //Gui elements
    private TextView what_field;
    private TextView where_field;
    private Button addbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        itemsDB = ItemsDB.get(AddItem.this);

        //Text Fields
        what_field = findViewById(R.id.what_text);
        where_field = findViewById(R.id.where_text);
        addbtn = findViewById(R.id.add_item);

        // Add's an item to the database
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatS = what_field.getText().toString().trim();
                String whereS = where_field.getText().toString().trim();
                if ((whatS.length() > 0) && (whereS.length() > 0)) {
                    itemsDB.addItem(whatS.toLowerCase(), whereS.substring(0,1).toUpperCase()+whereS.substring(1).toLowerCase());
                    what_field.setText("");
                    where_field.setText("");
                } else
                    Toast.makeText(AddItem.this, "Item could not be added", Toast.LENGTH_LONG).show();
            }
        });
    }
}
