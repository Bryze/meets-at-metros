package com.example.raunak.justmetrothings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        TextView profile =(TextView)findViewById(R.id.profile);
        TextView metro  = (TextView)findViewById(R.id.metro);
        TextView social_media = (TextView)findViewById(R.id.social_media);

        metro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MetroActivity.class);
                startActivity(i);
            }
        });


        //   Intent i=new Intent(MainActivity.this,ProfileActivity.class);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        social_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SocialMediaActivity.class);
                startActivity(i);
            }
        });
    }
}
