package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ElectionsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections_list);

        Button presidential = (Button) findViewById(R.id.button);
        presidential.setOnClickListener(unused -> onClick());
        Button state = (Button) findViewById(R.id.button2);
        state.setOnClickListener(unused -> onClick());
        Button school = (Button) findViewById(R.id.button3);
        school.setOnClickListener(unused -> onClick());
    }
//
    private void onClick() {
        Intent intent = new Intent(this, CandidateInfo.class);
        startActivity(intent);

    }
}
