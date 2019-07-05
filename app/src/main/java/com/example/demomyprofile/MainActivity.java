package com.example.demomyprofile;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import javax.crypto.spec.GCMParameterSpec;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioButtonGender);
        btnSave = findViewById(R.id.buttonSave);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Step 1a: Get the user input from the EditText and store it in a variable
                String strName = etName.getText().toString();
                String strGPA = etGPA.getText().toString();
                float floatGPA = Float.parseFloat(strGPA);
                int intGender = rgGender.getCheckedRadioButtonId();

                // Step 1b: Obtain an instance of the SharedPreferences
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                // Step 1c: Obtain an instance of the SharedPreferences Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                // Step 1d: Add the key-value pair
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", floatGPA);
                prefEdit.putInt("gender", intGender);

                // Step 1e: Call commit() to save changes into SharedPreferences
                prefEdit.commit();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data from the SharedPreference object
        String msg = prefs.getString("name", "No Name");
        Float msg2 = prefs.getFloat("gpa", 0);
        Integer msg3 = prefs.getInt("gender", 0);


        // Step 2c: Update the UI element with the value
        etName.setText(msg);
        etGPA.setText(msg2.toString());
        rgGender.check(msg3);

    }

}

