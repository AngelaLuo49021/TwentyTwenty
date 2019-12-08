package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ElectionsList extends AppCompatActivity {
    private String address;
    private String URL = "https://www.googleapis.com/civicinfo/v2/voterinfo?address=";
    private String endURL = "&electionId=2000&key=AIzaSyAja2lrNOgpD8EXwRAcm33Wn_6Fm38GWdE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections_list);

        //get address as string from intent
        Intent intent = getIntent();
        address = intent.getStringExtra("address");


        //GET request to get all the information
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL + address + endURL,
                null,
                (Response.Listener<JSONObject>) response -> setUpUi(response),
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(objectRequest);


    }

    private void setUpUi(JSONObject result) {
        LinearLayout parent = (LinearLayout) findViewById(R.id.electionsList);
        JSONArray contests;
        try {
            System.out.println("elections obtained");
            contests = result.getJSONArray("contests");
        } catch (Exception e) {
            System.out.println("elections NOT obtained");//
            contests = new JSONArray();
        }

        for (int i = 0; i < contests.length(); i++) {
            try {
                //System.out.println("office obtained");
                Object office = contests.getJSONObject(i).get("office");
                System.out.println("office: " + i + " " + office);
            } catch (Exception e) {
                System.out.println("office not obtained");
                contests.remove(i);
                continue;
            }
        }

    }
//
    private void onClick() {
        Intent intent = new Intent(this, CandidateInfo.class);
        startActivity(intent);

    }
}
