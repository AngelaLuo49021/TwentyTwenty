package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class VoterInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_info);

        Intent intent = getIntent();
        String locationName = intent.getStringExtra("locationName");
        String line1 = intent.getStringExtra("line1");
        String city = intent.getStringExtra("city");
        String state = intent.getStringExtra("state");

        TextView location = findViewById(R.id.locationName);
        location.setText(locationName);

        TextView line = findViewById(R.id.line1);
        line.setText(line1);

        TextView cityView = findViewById(R.id.city);
        cityView.setText(city);

        TextView stateView = findViewById(R.id.state);
        stateView.setText(state);
    }
}
