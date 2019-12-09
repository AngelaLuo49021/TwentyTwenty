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
                View candidateChunk = getLayoutInflater().inflate(R.layout.candidates, parentList, false);
                TextView name = candidateChunk.findViewById(R.id.name);
                name.setText(nameOfCandidate.toString());
                TextView party = candidateChunk.findViewById(R.id.party);
                party.setText(partyOfCandidate.toString());
                System.out.println("#" + i + " " + nameOfCandidate + ", Party: " + partyOfCandidate );
                parentList.addView(candidateChunk);
            } catch (Exception e) {
                System.out.println("candidate does not exist");
                candidatesList.remove(i);
                continue;
            }
        }
    }

    /*private void onClick() {
        Intent intent = new Intent(this, VoterInfo.class);
        startActivity(intent);
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
                View candidateChunk = getLayoutInflater().inflate(R.layout.candidates, parentList, false);
                parentList.addView(candidateChunk);
                System.out.println("#" + i + " " + nameOfCandidate + ", Party: " + partyOfCandidate );
            } catch (Exception e) {
                System.out.println("candidate does not exist");
                candidatesList.remove(i);
                continue;
            }
        }
   */
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
/*
What needs to be done:
- similiar to how you created chunks in ElectionsList, create chunks for CandidateInfo
- for each candidate add the chunk to candidatesList linear layout
- in the chunks have information you think would be useful like name, party, and website (we can just do those three for now)
- probably would be best if we had multiple TextViews in the chunk and also a picture (hold off on picture for now)
        - the chunk layout should be a linear layout with the chunk id and then inside this linear layout,
        add the text views.
*/

