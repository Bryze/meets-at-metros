package com.example.raunak.justmetrothings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MetroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        TextView metro_map =(TextView)findViewById(R.id.metro_map);
        metro_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MetroActivity.this,MetroMapActivity.class);
                startActivity(i);
            }
        });
    }
}
