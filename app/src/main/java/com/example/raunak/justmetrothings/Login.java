package com.example.raunak.justmetrothings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    EditText etName,etEmail,etAge;

    //private DatabaseReference mDatabase;
    private Button bSave;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName= (EditText) findViewById(R.id.etName);
        etAge= (EditText) findViewById(R.id.etAge);
        etEmail=(EditText) findViewById(R.id.etEmail);

        bLogout=(Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);



      //  mDatabase= FirebaseDatabase.getInstance().getReference();
        bSave= (Button) findViewById(R.id.bSave);

        bSave.setOnClickListener(this);

        etEmail.setText(getIntent().getExtras().getString("Email"));

    }

/*

    private void saveUserInfo(){

        String name= etName.getText().toString().trim();
        String age = etAge.getText().toString().trim();

        UserInfo userinformation = new UserInfo(name,age);

        //  To store Info in Firebase database we can use the unique id of the logged in user

        FirebaseUser user = firebaseAuth.getCurrentUser();

        // we will create a node inside Firebase database and inside that node we store that particular user's info

        mDatabase.child(user.getUid()).setValue(userinformation);

        Toast.makeText(this, "Informaton Saved!", Toast.LENGTH_SHORT).show();



        // At last we will call this method(saveUserInfo) when button(bSave) is called
    }
*/

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bLogout:
                startActivity(new Intent(this,Login1.class));

                break;


            case R.id.bSave:

     //           saveUserInfo();
                break;

        }
    }
}
