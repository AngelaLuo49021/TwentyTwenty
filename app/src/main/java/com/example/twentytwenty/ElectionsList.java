package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;


public class ElectionsList extends AppCompatActivity {
    private String jsonFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections_list);

        Intent intent = getIntent();
        jsonFile = intent.getStringExtra("jsonFile");
        System.out.println(jsonFile);
    }
    private void onClick() {
        Intent intent = new Intent(this, CandidateInfo.class);
        startActivity(intent);

    }
    public void setUpUi(JSONObject result) {
        //JsonArray arr = result.get("contests").getAsJsonArray();
    }
}
