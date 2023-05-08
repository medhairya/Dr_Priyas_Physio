package com.dhairya.drpriyasphysio;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.MyDatabaseHelper;

public class addpatient extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MyDatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient);

        Spinner spinner = findViewById(R.id.addpatientspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.addpatientwheel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        dbHelper = new MyDatabaseHelper(this);
        dbHelper.getWritableDatabase(); // Open the database

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> addPatient());
    }

    public void addPatient() {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open the database

        EditText nameEditText = findViewById(R.id.nameinput);
        String name = nameEditText.getText().toString();

        EditText ageEditText = findViewById(R.id.ageinput);
        int age = Integer.parseInt(ageEditText.getText().toString());

        EditText genderEditText = findViewById(R.id.genderinput);
        String gender = genderEditText.getText().toString();

        EditText phoneEditText = findViewById(R.id.phoneinput);
        String phone = phoneEditText.getText().toString();

        EditText addressEditText = findViewById(R.id.addressinput);
        String address = addressEditText.getText().toString();

        EditText diagnosisEditText = findViewById(R.id.diagnosisinput);
        String diagnosis = diagnosisEditText.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("gender", gender);
        values.put("phone_no", phone);
        values.put("address", address);
        values.put("diagnosis", diagnosis);

        db.insert("patients", null, values); // Insert the new patient into the database
        db.close(); // Close the database

        // Clear the input fields
        nameEditText.setText("");
        ageEditText.setText("");
        genderEditText.setText("");
        phoneEditText.setText("");
        addressEditText.setText("");
        diagnosisEditText.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();

        if (choice.equals("Home")) {
            Intent intent = new Intent(addpatient.this, MainActivity.class);
            startActivity(intent);
        }
        if (choice.equals("Payment")) {
            Intent intent = new Intent(addpatient.this, payment.class);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close(); // Close the database
    }
}
