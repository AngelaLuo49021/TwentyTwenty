package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Elections extends AppCompatActivity {
    private Button presidential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections);
        presidential = findViewById(R.id.presidential);
        presidential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOne = new Intent(Elections.this, CandidateInfo.class);
                startActivity(intentOne);
            }
        });
    }
}
//connect