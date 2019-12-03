package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CandidateInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_info);
        Button voterInfo = (Button) findViewById(R.id.voter_information);
        voterInfo.setOnClickListener(unused -> onClick());
    }
    private void onClick() {
        Intent intent = new Intent(this, VoterInfo.class);
        startActivity(intent);

    }

}
