package com.example.raunak.justmetrothings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseActivity extends AppCompatActivity {

    Toolbar toolbar;
    private EditText name;
    private EditText gender;
    private EditText relation;
    private EditText location;
    private Button send_details;
    private FirebaseAuth mfirebaseauth;
    //private FirebaseDatabase mfirebasedatabase;
    private DatabaseReference mdatabasereference;
    private FirebaseUser user;
    private String uid;
    private TextView key;
    private String username;
    private String usergender;
    private String userrelation;
    private String userlocation;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.name);
        gender = (EditText)findViewById(R.id.gender);
        relation = (EditText)findViewById(R.id.relationship);
        location = (EditText)findViewById(R.id.location);
        location.setEnabled(false);
        send_details = (Button)findViewById(R.id.data_send_button);
        key = (TextView)findViewById(R.id.key);
        mfirebaseauth = FirebaseAuth.getInstance();
        //mfirebasedatabase = FirebaseDatabase.getInstance();
        mdatabasereference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            uid = user.getUid();
        }
        key.setText(uid);


        send_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveuserinfo();
            }
        });
    }

    public  void saveuserinfo(){
        username = name.getText().toString();
        usergender = gender.getText().toString();
        userrelation = relation.getText().toString();
        userlocation = location.getText().toString();

        UserInformation ui = new UserInformation(username,usergender,userrelation,userlocation);
        mdatabasereference.child(uid).setValue(ui);
        finish();
        Intent i = new Intent(DatabaseActivity.this, ProfileActivity.class);
        startActivity(i);

    }
}
