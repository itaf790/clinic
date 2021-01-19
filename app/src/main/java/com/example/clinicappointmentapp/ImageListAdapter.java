package com.example.clinicappointmentapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import doctors.DoctorList;

public class ImageListAdapter extends ArrayAdapter<DoctorList> {
    private static final String TAG = "ImageListAdapter";
    private Context mContext;
    private int mResource;
    public ImageListAdapter(Context context, int resource, ArrayList<DoctorList>  list) {
        super(context, resource, list);
        mContext = context;
        mResource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the image information


        String TempName= Objects.requireNonNull(getItem(position)).getName();
        String TempSpecialization = Objects.requireNonNull(getItem(position)).getSpecialization();
        String TempExperiance = Objects.requireNonNull(getItem(position)).getExperiance();
        String TempEducation = Objects.requireNonNull(getItem(position)).getEducation();
        String TempEmail= Objects.requireNonNull(getItem(position)).getEmail();
        String TempAge= Objects.requireNonNull(getItem(position)).getAge();
        String TempContact = Objects.requireNonNull(getItem(position)).getContact();
        String TempAddress= Objects.requireNonNull(getItem(position)).getAdress();
        String TempShift = Objects.requireNonNull(getItem(position)).getShift();
        String image = Objects.requireNonNull(getItem(position)).getImage();

        //Create the employee object with the information
       DoctorList ImageInfo = new DoctorList(TempName,TempSpecialization,TempExperiance,TempEducation, TempEmail,TempAge,TempContact,TempAddress,TempShift,image);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(this.mResource, parent,false);
        TextView imgname = convertView.findViewById(R.id.name);
        TextView imgSpecialization= convertView.findViewById(R.id.Specialization);
        TextView imgExperiance= convertView.findViewById(R.id.experiancee);
        TextView imgeducation = convertView.findViewById(R.id.educationn);
        TextView  imgemail= convertView.findViewById(R.id.email);
        TextView  imgeage= convertView.findViewById(R.id.age);
        TextView imgContact= convertView.findViewById(R.id.Contact);
        TextView imgadress = convertView.findViewById(R.id.adress);
        TextView imgShift= convertView.findViewById(R.id.Shift);
        ImageView imgView= convertView.findViewById(R.id.image_View);

        imgname.setText(TempName);
        imgSpecialization.setText(TempSpecialization);
        imgExperiance.setText(TempExperiance);
        imgeducation.setText(TempEducation);
       imgemail.setText(TempEmail);
        imgeage.setText(TempAge);
        imgContact.setText(TempContact);
       imgadress.setText(TempAddress);
        imgShift.setText(TempShift);

        //Loading image from Glide library.

        Glide.with(convertView.getContext()).load(image).dontAnimate().into(imgView);
        return convertView;
    }
}