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
        //Button presidential;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections);
        presidential = (Button)findViewById(R.id.button);
        presidential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Elections.this, Elections.class);
                startActivity(intent);
            }
        });
    }
}
//connect