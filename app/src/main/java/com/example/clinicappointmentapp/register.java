package com.example.clinicappointmentapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class register extends AppCompatActivity {
    EditText mName,mAge,mContactNumber,mAddress ,mEmail, mPassword;

    Button mRegister;
    private ProgressDialog mRegProgress;
    private FirebaseAuth mAuth;//Used for firebase authentication
    //Database Reference
    private DatabaseReference mUserDetails = FirebaseDatabase.getInstance().getReference();
    private ProgressDialog loadingBar;//Used to show the progress of the registration process
    //RadioGroup & RadioButton
    private RadioGroup mGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        mAuth = FirebaseAuth.getInstance();
        mRegProgress = new ProgressDialog(this);
        //User Details
        mName =( EditText) findViewById(R.id.name);
        mAge = (EditText) findViewById(R.id.age);
        mContactNumber = (EditText) findViewById(R.id.number);
        mAddress = (EditText) findViewById(R.id.adress);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.pass);
        mRegister = (Button) findViewById(R.id.reg_button);
        loadingBar = new ProgressDialog(this);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mName.getText().toString().trim();
                String age = mAge.getText().toString().trim();
                String contactnumber = mContactNumber.getText().toString().trim();
                String address =mAddress.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String gender = "";

                //RadioGroup
                mGender = (RadioGroup) findViewById(R.id.reg_gender_radiogroup);
                int checkedId = mGender.getCheckedRadioButtonId();

                if(checkedId == R.id.reg_male_radiobtn){
                    gender = "Male";
                }
                else if(checkedId == R.id.reg_female_radiobtn){
                    gender = "Female";
                }
                else if(checkedId == R.id.reg_other_radiobtn){
                    gender = "Other";
                }
                else {
                    Toast.makeText(getBaseContext(),"Select Gender",Toast.LENGTH_LONG).show();
                }

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(contactnumber) && !TextUtils.isEmpty(address)){

                    mRegProgress.setTitle("Creating Account");
                    mRegProgress.setMessage("Please Wait! We are Processing");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();


                    createAccount(name,age,gender,contactnumber,address,email,password);

                }
                else{

                    Toast.makeText(register.this,"Please fill all field",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void createAccount(final String name, final String age,final String gender,  final String contactnumber, final String address, final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = currentUser.getUid();

                            mUserDetails.child("User_Type").child(uid).child("Type").setValue("Patient");

                            HashMap<String,String> userDetails = new HashMap<>();
                            userDetails.put("Name",name);
                            userDetails.put("Age",age);
                            userDetails.put("Gender",gender);
                            userDetails.put("Contact_N0",contactnumber);
                            userDetails.put("Address",address);
                            userDetails.put("Email",email);
                            userDetails.put("Password",password);

                            mUserDetails.child("Patient_Details").child(uid).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mRegProgress.dismiss();
                                    Toast.makeText(register.this,"Account Successfully Created",Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                        else {

                            mRegProgress.hide();
                            Toast.makeText(register.this,"Creating Account Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }



}
