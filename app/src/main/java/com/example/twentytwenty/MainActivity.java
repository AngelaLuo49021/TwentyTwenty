package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
//1690 Carthage Court, Naperville, IL, USA
public class MainActivity extends AppCompatActivity {
private String address, URL, endURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startApp = findViewById(R.id.submit_location);
        startApp.setOnClickListener(unused -> goToElections());
    }

    private void goToElections() {

        //create an intent that goes to the activity with all the elections listed
        Intent intent = new Intent(this, ElectionsList.class);

        //gets the address
        EditText location = findViewById(R.id.location_input);
        address = location.getText().toString();
        intent.putExtra("address", address);
        startActivity(intent);
    }
}
