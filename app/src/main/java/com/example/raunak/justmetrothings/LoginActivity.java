package com.example.raunak.justmetrothings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.raunak.justmetrothings.R.id.editText;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    private View statusBar;
    private EditText email_login;
    private  EditText pass_login;
    private TextView signin;
    private TextView registerhere;
    private  TextView forgotpassword;
    private Button viewPassword;

    private String email_text_login;
    private String pass_text_login;

    private ProgressDialog progressdialog;
    private FirebaseAuth firebase_auth;
/*
    //Transparent Status Bar
    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().setDisplayShowTitleEnabled(false);

        toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Attemot at Changing Fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Ubuntu-Regular.ttf");
        signin.setTypeface(custom_font);
        registerhere.setTypeface(custom_font);
        forgotpassword.setTypeface(custom_font);
        viewPassword.setTypeface(custom_font);
        email_login.setTypeface(custom_font);
        pass_login.setTypeface(custom_font);
        */

        progressdialog = new ProgressDialog(this);
        viewPassword = (Button) findViewById(R.id.viewPassword);
        email_login = (EditText) findViewById(R.id.email_login);
        pass_login = (EditText) findViewById(R.id.password_login);
        signin = (TextView) findViewById(R.id.signin);
        registerhere = (TextView) findViewById(R.id.register_here);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        firebase_auth = FirebaseAuth.getInstance();

        if (firebase_auth.getCurrentUser() != null) {
            finish();
            Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(i);
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_text_login = email_login.getText().toString().trim();
                pass_text_login = pass_login.getText().toString().trim();

                if (TextUtils.isEmpty(email_text_login)) {
                    Toast.makeText(LoginActivity.this, "E-Mail can't be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass_text_login)) {
                    Toast.makeText(LoginActivity.this, "Password can't be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressdialog.setMessage("Signing In \nPlease Wait");
                progressdialog.show();

                firebase_auth.signInWithEmailAndPassword(email_text_login, pass_text_login)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressdialog.dismiss();
                                if (task.isSuccessful()) {
                                    finish();
                                    Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                                    startActivity(i);
                                } else {
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
                Intent i = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(i);
            }
        });

        //View Password
        viewPassword.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pass_login.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        pass_login.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });
    }
}

