package com.example.raunak.justmetrothings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login1 extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etUsername,etPassword;
    TextView tvRegisterLink;

    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);


        firebaseAuth = FirebaseAuth.getInstance();
        mAuth= FirebaseAuth.getInstance();

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bLogin:

                final ProgressDialog progressDialog= ProgressDialog.show(Login1.this,"Please wait..","Processing..",true);
                (firebaseAuth.signInWithEmailAndPassword(etUsername.getText().toString(),etPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            Toast.makeText(Login1.this,"Login successful",Toast.LENGTH_LONG).show();

                            Intent i=new Intent(Login1.this,Login.class);
                            i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);

                        }
                        else{
                            Log.e("Error",task.getException().toString());
                            Toast.makeText(Login1.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();


                        }

                    }
                });



                break;


            case R.id.tvRegisterLink:

                startActivity(new Intent(this,Register.class));
                break;




        }

    }
}
