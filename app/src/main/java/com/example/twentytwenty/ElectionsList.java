package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.concurrent.BlockingQueue;

public class ElectionsList extends AppCompatActivity {
    private String address;
    private String URL = "https://www.googleapis.com/civicinfo/v2/voterinfo?address=";
    private String endURL = "&electionId=2000&key=<YOUR KEY HERE>";
    private JSONObject result;
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
                (Response.Listener<JSONObject>) response -> {
                    result = response;
                    setUpUi(response);
                    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(objectRequest);

        //button to voter info
        Button voterInfo = findViewById(R.id.voterInfo);
        voterInfo.setOnClickListener(unused -> voterInfoClicked());

    }

    private void voterInfoClicked() {
        JSONArray pollingLocations;
        try {
            //Retreives the polling location for that address
            System.out.println("reaches checkpoint 0");
            pollingLocations = result.getJSONArray("pollingLocations");
            System.out.println("reaches checkpoint 1");
            Intent intentVoterInfo = new Intent(ElectionsList.this, VoterInfo.class);
            JSONObject address = (JSONObject) pollingLocations.getJSONObject(0).get("address");
            System.out.println("reaches checkpoint 2");
            String locationName = address.get("locationName").toString();
            String line1 = address.get("line1").toString();
            String city = address.get("city").toString();
            String state = address.get("state").toString();

            //get election date
            JSONObject election = (JSONObject) result.get("election");
            String date = election.get("electionDay").toString();

            //put intent extras
            intentVoterInfo.putExtra("date", date);
            intentVoterInfo.putExtra("locationName", locationName);
            intentVoterInfo.putExtra("line1", line1);
            intentVoterInfo.putExtra("city", city);
            intentVoterInfo.putExtra("state", state);
            startActivity(intentVoterInfo);

        } catch (Exception e) {
            Intent noVoterInfo = new Intent(ElectionsList.this, NoVoterInfo.class);
            startActivity(noVoterInfo);

        }

    }

    private void setUpUi(JSONObject result) {
        LinearLayout parent = (LinearLayout) findViewById(R.id.electionsList);
        JSONArray contests;
        try {
            contests = result.getJSONArray("contests");
        } catch (Exception e) {
            contests = new JSONArray();
        }

        for (int i = 0; i < contests.length(); i++) {
            try {
                Object office = contests.getJSONObject(i).get("office");
                //System.out.println("office: " + i + " " + office);
                View chunk = getLayoutInflater().inflate(R.layout.chunk, parent, false);
                Button elections = chunk.findViewById(R.id.electionsChunk);
                elections.setVisibility(View.VISIBLE);
                elections.setText(office.toString());
                //String candidates = contests.getJSONObject(i).get("candidates").toString();
                String contestInfo = contests.getJSONObject(i).toString();
                //when button is clicked, json with this particular office contest info is sent to the candidate page
                elections.setOnClickListener(new View.OnClickListener() {
                    public void onClick(final View v) {
                        Intent intentCandidate = new Intent(ElectionsList.this, CandidateInfo.class);
                        try {
                            intentCandidate.putExtra("contestInfo", contestInfo);
                        } catch (Exception e) {
                            intentCandidate.putExtra("contestInfo", "None");
                        }

                        startActivity(intentCandidate);
                    }
                });
                parent.addView(chunk);

            } catch (Exception e) {
                contests.remove(i);
                continue;
            }
        }

    }
}
