package com.example.raunak.justmetrothings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity {

    private TextView button_reg;
    private EditText email_reg;
    private  EditText pass_reg;
    private TextView sign_in_go;

    private String email_text_reg;

    private String pass_text_reg;

    private ProgressDialog progressdialog;

    private FirebaseAuth firebase_auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressdialog = new ProgressDialog(this);
        button_reg = (TextView) findViewById(R.id.button_reg);

        email_reg = (EditText)findViewById(R.id.email_reg);

        pass_reg = (EditText)findViewById(R.id.pass_reg);
        //sign_in_go =(TextView)findViewById(R.id.sign_in_go);

        firebase_auth = FirebaseAuth.getInstance();
        if (firebase_auth.getCurrentUser()!=null){
            Intent i = new Intent(RegisterActivity.this, ProfileActivity.class);
            startActivity(i);
        }

        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_text_reg = email_reg.getText().toString().trim();

                pass_text_reg = pass_reg.getText().toString().trim();

                if(TextUtils.isEmpty(email_text_reg)){
                    Toast.makeText(RegisterActivity.this, "Email cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass_text_reg)){
                    Toast.makeText(RegisterActivity.this, "Password can't be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.getTrimmedLength(pass_text_reg)<5){
                    Toast.makeText(RegisterActivity.this, "Password Length too short", Toast.LENGTH_SHORT).show();
                }

                progressdialog.setMessage("Registering User....");
                progressdialog.show();

                firebase_auth.createUserWithEmailAndPassword(email_text_reg,pass_text_reg)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressdialog.hide();
                                if(task.isSuccessful()){

                                    Intent i = new Intent(RegisterActivity.this, DatabaseActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        /*sign_in_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });*/
    }
}

