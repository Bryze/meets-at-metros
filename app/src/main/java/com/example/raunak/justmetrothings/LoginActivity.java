package com.example.raunak.justmetrothings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email_login;
    private  EditText pass_login;
    private TextView signin;
    private TextView registerhere;
    private  TextView forgotpassword;

    private String email_text_login;
    private String pass_text_login;

    private ProgressDialog progressdialog;
    private FirebaseAuth firebase_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressdialog = new ProgressDialog(this);
        email_login = (EditText)findViewById(R.id.email_login);
        pass_login = (EditText)findViewById(R.id.password_login);
        signin = (TextView) findViewById(R.id.signin);
        registerhere = (TextView)findViewById(R.id.register_here);
        forgotpassword = (TextView)findViewById(R.id.forgotpassword);
        firebase_auth = FirebaseAuth.getInstance();

        if(firebase_auth.getCurrentUser()!=null){
            finish();
            Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(i);
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_text_login = email_login.getText().toString().trim();
                pass_text_login = pass_login.getText().toString().trim();

                if(TextUtils.isEmpty(email_text_login)){
                    Toast.makeText(LoginActivity.this, "E-Mail can't be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pass_text_login)){
                    Toast.makeText(LoginActivity.this, "Password can't be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressdialog.setMessage("Signing In \nPlease Wait");
                progressdialog.show();

                firebase_auth.signInWithEmailAndPassword(email_text_login,pass_text_login)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressdialog.dismiss();
                                if(task.isSuccessful()){
                                    finish();
                                    Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



            }
        });

        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(i);
            }
        });

    }
}

