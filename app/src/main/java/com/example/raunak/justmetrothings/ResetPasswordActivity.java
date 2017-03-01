package com.example.raunak.justmetrothings;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText resetemail;
    private Button sendemail;
    private ProgressDialog progressdialog;
    private  String mail_id;

    FirebaseAuth firebase_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        resetemail = (EditText)findViewById(R.id.resetpass_email);
        sendemail = (Button)findViewById(R.id.sendEmail);
        progressdialog = new ProgressDialog(this);
        firebase_auth = FirebaseAuth.getInstance();
        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail_id = resetemail.getText().toString().trim();
                if(TextUtils.isEmpty(mail_id)){
                    Toast.makeText(ResetPasswordActivity.this, "Enter a valid E-Mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressdialog.setMessage("Please Wait");
                progressdialog.show();

                firebase_auth.sendPasswordResetEmail(mail_id)
                        .addOnCompleteListener(ResetPasswordActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressdialog.dismiss();
                                if(task.isSuccessful()){
                                    Toast.makeText(ResetPasswordActivity.this, "Password Reset link has been sent to your E-Mail Address", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(ResetPasswordActivity.this, "E-Mail Address not valid", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });
    }
}
