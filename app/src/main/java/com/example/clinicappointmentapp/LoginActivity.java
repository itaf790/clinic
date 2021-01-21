package com.example.clinicappointmentapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {



    private Button mForgot;
    private Button mRegister;

    EditText userName,password;
    Button login;
    TextView register,forgotPassword;
    FirebaseUser currentUser;//used to store current user of account
    FirebaseAuth mAuth;//Used for firebase authentication
    ProgressDialog loadingBar;
    //Firebase Auth

    private Toolbar mToolbar;
    private ProgressDialog mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoginProgress = new ProgressDialog(this);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login_btn);
        register = (TextView) findViewById(R.id.registerLink);
        forgotPassword = (TextView) findViewById(R.id.ForgetPassword);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        currentUser = mAuth.getCurrentUser();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = userName.getText().toString().trim();
                String pwd = password.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pwd)){

                    mLoginProgress.setTitle("Loggin In");
                    mLoginProgress.setMessage("Please wait! While your Account is Logging In");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();

                    login(email,pwd);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Please Enter Email & Password",Toast.LENGTH_LONG).show();
                }

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword_Intent = new Intent(LoginActivity.this,Forgot_PasswordActivity.class);
                startActivity(forgotPassword_Intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration_Intent = new Intent(LoginActivity.this, register.class);
                startActivity(registration_Intent);
            }
        });
    }

    private void login(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            mLoginProgress.dismiss();
                            Intent main_Intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(main_Intent);
                           Toast.makeText(LoginActivity.this,"Successfully Logged IN",Toast.LENGTH_LONG).show();
                        }
                        else {

                            mLoginProgress.dismiss();
                            Toast.makeText(LoginActivity.this,"Entered Email & Password is wrong",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
