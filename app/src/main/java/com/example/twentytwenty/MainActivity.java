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

public class MainActivity extends AppCompatActivity {
private String address, URL, endURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //URL for the get request
        URL = "https://www.googleapis.com/civicinfo/v2/voterinfo?address=";
        endURL = "&electionId=2000&key=AIzaSyAja2lrNOgpD8EXwRAcm33Wn_6Fm38GWdE";

        //sets button function
        Button startApp = findViewById(R.id.submit_location);
        startApp.setOnClickListener(unused -> goToElections());
    }

    private void goToElections() {

        //create an intent that goes to the activity with all the elections listed
        Intent intent = new Intent(this, ElectionsList.class);

        //gets the address
        EditText location = findViewById(R.id.location_input);
        address = location.getText().toString();

        //GET request to get all the information
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL + address + endURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response recieved");

                        //add the received JSON object as a string to the intent as an extra
                        // in the ElectionList activity, do something like
//                        Intent intent = getIntent();
//                        jsonFile = intent.getStringExtra("jsonFile");
                        intent.putExtra("jsonFile", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(objectRequest);

        startActivity(intent);
    }
}
