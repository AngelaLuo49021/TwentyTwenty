package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class CandidateInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_info);
        Button voterInfo = (Button) findViewById(R.id.voter_information);
        voterInfo.setOnClickListener(unused -> onClick());
    }

    private void displayCandidates() {
        ScrollView infoPage = (ScrollView) findViewById(R.id.candidateInfo);
        View candidateChunk = getLayoutInflater().inflate(R.layout.candidates, infoPage, false);
        infoPage.addView(candidateChunk);
    }
    private void onClick() {
        Intent intent = new Intent(this, VoterInfo.class);
        startActivity(intent);

    }

}


// steps for displayCandidates
// get intent that has all the candidate info
// ......
// get the number of candidates
//........
// for (every candidates) {
// inflate the chunk:
// ScrollView infoPage = (ScrollView) findViewById(R.id.candidateInfo);
// View candidateChunk = getLayoutInflater().inflate(R.layout.candidates, infoPage, false);
// fill chunk with the data for that specific candidate u got from the intent
// .........
// add the chunk back to the activity
// infoPage.addView(candidateChunk);
// }
