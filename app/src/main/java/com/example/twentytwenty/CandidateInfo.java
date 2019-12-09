package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONObject;

public class CandidateInfo extends AppCompatActivity {
    JSONObject contestJson;
    JSONArray candidatesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_info);
        LinearLayout parentList = (LinearLayout) findViewById(R.id.candidatesList);
        //receive JSON with the contest info
        String contestInfoAsString = getIntent().getStringExtra("contestInfo");
        try {
            contestJson = new JSONObject(contestInfoAsString);
        } catch (Exception e) {
            contestJson = new JSONObject();
        }
        //create a list of all the candidates
        try {
            candidatesList = contestJson.getJSONArray("candidates");
        } catch (Exception e) {
            candidatesList = new JSONArray();
        }
        //Example of all the candidates being displayed with parties but in print statements.
        for (int i = 0; i < candidatesList.length(); i++) {
            try {
                Object nameOfCandidate = candidatesList.getJSONObject(i).get("name");
                Object partyOfCandidate = candidatesList.getJSONObject(i).get("party");
                Object websiteOfCandidate = candidatesList.getJSONObject(i).get("candidateUrl");
                View candidateChunk = getLayoutInflater().inflate(R.layout.candidates, parentList, false);
                TextView name = candidateChunk.findViewById(R.id.name);
                name.setText(nameOfCandidate.toString());
                TextView party = candidateChunk.findViewById(R.id.party);
                party.setText(partyOfCandidate.toString());
                TextView webSite = candidateChunk.findViewById(R.id.webSite);
                webSite.setText(websiteOfCandidate.toString());
                System.out.println("#" + i + " " + nameOfCandidate + ", Party: " + partyOfCandidate );
                parentList.addView(candidateChunk);
            } catch (Exception e) {
                System.out.println("candidate does not exist");
                candidatesList.remove(i);
                continue;
            }
        }
    }
}
