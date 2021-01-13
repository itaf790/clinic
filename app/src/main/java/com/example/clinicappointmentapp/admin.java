package com.example.clinicappointmentapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class admin extends AppCompatActivity {

    private ProgressDialog loadingBar;
    private EditText AdminLoginInputEmail, AdminLoginInputPassword;

    private FirebaseAuth auth;
    private Button btnAdminSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        loadingBar = new ProgressDialog(this);
        loadingBar.setMessage("Loading.....");
        loadingBar.setCancelable(true);
        loadingBar.setCanceledOnTouchOutside(false);

        auth = FirebaseAuth.getInstance();

        AdminLoginInputEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        AdminLoginInputPassword = (EditText) findViewById(R.id.editpass);
        btnAdminSignIn = (Button) findViewById(R.id.btnsign);

        btnAdminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = AdminLoginInputEmail.getText().toString();
                final String password = AdminLoginInputPassword.getText().toString();
                try {

                    if (password.length()> 0 && email.length()> 0){
                        loadingBar.show();
                        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(admin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(admin.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    Log.v("error", task.getResult().toString());
                                } else{
                                    Toast.makeText(admin.this,"You logged in successfully",Toast.LENGTH_LONG).show();
                                    loadingBar.dismiss();
                                    Intent intent = new Intent(admin.this,admininsert.class);
//                                    Users users = new Users(email,password);
//                                    Prevalent.currentOnLineUsers = users;
                                    startActivity(intent);

                                }

                                loadingBar.dismiss();

                            }
                        });
                    } else{
                        Toast.makeText(admin.this,"Account with this "+ email+" email do not exists",Toast.LENGTH_LONG).show();
                    }



                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}

