package com.example.clinicappointmentapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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


public class admininsert extends AppCompatActivity {
    Button insert;
    private TextView mName, mEmail, mSpecialization, mExperiance, mAge, mContact, mAddress, mEducation,mshift;
    private ProgressDialog mRegProgress;
    private FirebaseAuth mAuth;//Used for firebase authentication
    //Database Reference
    private DatabaseReference mUserDetails = FirebaseDatabase.getInstance().getReference();
    private ProgressDialog loadingBar;//Used to show the progress of the registration process




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admininsert);
        mAuth = FirebaseAuth.getInstance();
        mRegProgress = new ProgressDialog(this);
        //firebas

        //variabels
        insert = (Button) findViewById(R.id.insert);
        mName = (TextView) findViewById(R.id.edit_doctor_name);
        mSpecialization = (TextView) findViewById(R.id.edit_doctor_specialization);
        mExperiance = (TextView) findViewById(R.id.edit_doctor_experiance);
        mEducation = (TextView) findViewById(R.id.edit_doctor_education);
        mEmail = (TextView) findViewById(R.id.edit_doctor_email);
        mAge = (TextView) findViewById(R.id.edit_doctor_age);
        mContact = (TextView) findViewById(R.id.edit_doctor_contact);
        mAddress = (TextView) findViewById(R.id.edit_doctor_address);
        mshift = (TextView) findViewById(R.id.edit_doctor_shift);
        loadingBar = new ProgressDialog(this);

       insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = mName.getText().toString().trim();
                String specification= mAge.getText().toString().trim();
                String eexperiance= mExperiance.getText().toString().trim();
                String  edu =mEducation.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String age = mAge.getText().toString().trim();
                String contact = mContact.getText().toString().trim();
                String adress = mAddress.getText().toString().trim();
                String sepecialization = mSpecialization.getText().toString().trim();
                String shift = mshift.getText().toString().trim();


                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(Name) && !TextUtils.isEmpty(specification) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(eexperiance) && !TextUtils.isEmpty(edu)&& !TextUtils.isEmpty(contact)&& !TextUtils.isEmpty(shift)&& !TextUtils.isEmpty(adress)&& !TextUtils.isEmpty(sepecialization)){

                    mRegProgress.setTitle("add data");
                    mRegProgress.setMessage("Please Wait! We are Processing");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();


                    createAccount(Name,age,adress,eexperiance,email,edu,specification,contact,shift,sepecialization);

                }
                else{

                    Toast.makeText(admininsert.this,"Please fill all field",Toast.LENGTH_LONG).show();

                }

            }
       });
    }

    private void createAccount(final String Name, final String age,final String adress,  final String contact, final String shift, final String email, final String eexperiance,final String specification,final String edu,final String specialization) {

        mAuth.createUserWithEmailAndPassword(email,Name)
                .addOnCompleteListener(admininsert.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = currentUser.getUid();

                            mUserDetails.child("User_Type").child(uid).child("Type").setValue("doctors");

                            HashMap<String,String> userDetails = new HashMap<>();
                            userDetails.put("Name",Name);
                            userDetails.put("Age",age);
                            userDetails.put("education",edu);
                            userDetails.put("Contact_N0",contact);
                            userDetails.put("Address",adress);
                            userDetails.put("Email",email);
                            userDetails.put("spesification",specification);
                            userDetails.put("shift",shift);
                            userDetails.put("experince",eexperiance);
                            userDetails.put("Specialization",specialization);


                            mUserDetails.child("DOCTORS_Details").child(uid).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mRegProgress.dismiss();
                                    Toast.makeText(admininsert.this,"data Successfully added",Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                        else {

                            mRegProgress.hide();
                            Toast.makeText(admininsert.this,"intering data Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });






    }////

}



