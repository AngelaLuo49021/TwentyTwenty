package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CandidateInfo extends AppCompatActivity {
    JSONObject contestJson;
    JSONArray candidatesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_info);

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
                System.out.println("#" + i + " " + nameOfCandidate + ", Party: " + partyOfCandidate );
            } catch (Exception e) {
                System.out.println("candidate does not exist");
                candidatesList.remove(i);
                continue;
            }

        }

    }
}
/*
What needs to be done:
- similiar to how you created chunks in ElectionsList, create chunks for CandidateInfo
- for each candidate add the chunk to candidatesList linear layout
- in the chunks have information you think would be useful like name, party, and website (we can just do those three for now)
- probably would be best if we had multiple TextViews in the chunk and also a picture (hold off on picture for now)
        - the chunk layout should be a linear layout with the chunk id and then inside this linear layout,
        add the text views.

*/

