package com.example.clinicappointmentapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;


public class admininsert extends AppCompatActivity {
    Button insert;
    private String Name,specification,eexperiance,edu, email, age, contact,   adress,    Specialization,   shift;
    private TextView mName, mEmail, mSpecialization, mExperiance, mAge, mContact, mAddress, mEducation,mshift, mPassword;
    private ProgressDialog mRegProgress;
    private FirebaseAuth mAuth;//Used for firebase authentication
    private static final int GalleryPick = 1;
    private Uri imageUri;
    private String productRandomKey,downLoadImageUrl;
    private StorageReference ImagesRef;
    private DatabaseReference mDatabase;
    private ProgressDialog loadingBar;
    private ImageView doctorImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admininsert);

       ImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("doctors");
        mAuth = FirebaseAuth.getInstance();

        mRegProgress = new ProgressDialog(this);


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
        doctorImage = (ImageView) findViewById(R.id.doctor_image);


       doctorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateProductData();

            }
        });


    }

    private void openGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
           doctorImage.setImageURI(imageUri);
        }
    }

    private void validateProductData() {

        Name = mName.getText().toString().trim();
      specification= mAge.getText().toString().trim();
      eexperiance= mExperiance.getText().toString().trim();
       edu =mEducation.getText().toString().trim();
       email = mEmail.getText().toString().trim();
       age = mAge.getText().toString().trim();
      contact = mContact.getText().toString().trim();
      adress = mAddress.getText().toString().trim();
     Specialization = mSpecialization.getText().toString().trim();
       shift = mshift.getText().toString().trim();

        if (imageUri == null) {

            Toast.makeText(this, "doctor image is mandatory", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Name)) {

            Toast.makeText(this, "Please write doctor name", Toast.LENGTH_SHORT).show();


        } else if (TextUtils.isEmpty( specification)) {

            Toast.makeText(this, "Please write doctor specification", Toast.LENGTH_SHORT).show();


        } else if (TextUtils.isEmpty(eexperiance)) {

            Toast.makeText(this, "Please write doctor experiance", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(edu)) {

            Toast.makeText(this, "Please write doctor deucation", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email)) {

            Toast.makeText(this, "Please write doctor email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(age)) {

            Toast.makeText(this, "Please write doctor age", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(contact)) {

            Toast.makeText(this, "Please write contact number ", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(adress)) {

            Toast.makeText(this, "Please write  doctor adress", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Specialization)) {

            Toast.makeText(this, "Please write Specialization", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(shift)) {

            Toast.makeText(this, "Please write doctor shift ", Toast.LENGTH_SHORT).show();
        }
        else{
            storeProductInformation();
        }
    }

    private void storeProductInformation() {

        loadingBar.setTitle("Add  data");
        loadingBar.setMessage(" Please wait ");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        final StorageReference filePath = ImagesRef.child(imageUri.getLastPathSegment() + productRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(admininsert.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(admininsert.this, " Image uploaded Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();

                        }

                        downLoadImageUrl = filePath.getDownloadUrl().toString();
                        return  filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful()){
                            downLoadImageUrl = task.getResult().toString();
                            Toast.makeText(admininsert.this, "got  image Url Successfully", Toast.LENGTH_SHORT).show();

                            saveProductInfoToDatabase();
                        }

                    }
                });
            }
        });


    }

    private void saveProductInfoToDatabase() {

        HashMap<String, Object> productMap  = new HashMap<>();
        productMap.put("pid" , productRandomKey);
        productMap.put("name" ,Name);
        productMap.put("specification" , specification);
        productMap.put("age" , age);
        productMap.put("image" , downLoadImageUrl);
        productMap.put("experiance" , eexperiance);
        productMap.put("email" , email);
        productMap.put(" contact" ,  contact);
        productMap.put("adress" , adress);
        productMap.put("Specialization" , Specialization);
        productMap.put("shift" , shift);
        productMap.put("education" , edu);


        mDatabase.child(Name).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(admininsert.this,  Doctor_ShowAppointmentActivity.class);
                            startActivity(intent);
                            loadingBar.dismiss();
                            Toast.makeText(admininsert.this, "data is added successfully", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(admininsert.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}