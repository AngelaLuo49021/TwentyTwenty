package com.example.twentytwenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets button function
        Button startApp = findViewById(R.id.submit_location);
        startApp.setOnClickListener(unused -> goToElections());
    }

    private void goToElections() {
        Intent intent = new Intent(this, ElectionsList.class);
        startActivity(intent);

    }
}
