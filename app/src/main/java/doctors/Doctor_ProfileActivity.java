
package doctors;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.clinicappointmentapp.R;
import java.util.Calendar;
import patient.Patient_BookAppointmentActivity;



public class Doctor_ProfileActivity extends AppCompatActivity {

    private String image;
    private String name, specialization, experiance, education, email, age,  contact, adress, shift;
    private Button mBookAppointmentBtn;
    private Toolbar mToolbar;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_doctor__profile);


            mBookAppointmentBtn = (Button) findViewById(R.id.book_appointment_button);
             mBookAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Doctor_ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String userId = getIntent().getStringExtra("UserId");

                        String date = dayOfMonth +"-"+ (month+1) +"-"+ year;
//                        Toast.makeText(Patient_DoctorProfileActivity.this, date , Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Doctor_ProfileActivity.this, Patient_BookAppointmentActivity.class);
                        intent.putExtra("Date",date);
                        intent.putExtra("DoctorUserId",userId);
                        intent.putExtra("Shift",shift);
                        startActivity(intent);
                    }
                },day,month,year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });

        Intent intent = getIntent();


        TextView mName = (TextView) findViewById(R.id.doctor_name);
        TextView mSpecialization = (TextView) findViewById(R.id.doctor_specialization);
        TextView mExperiance = (TextView) findViewById(R.id.doctor_experience);
        TextView mEducation = (TextView) findViewById(R.id.doctor_education);
        TextView mEmail = (TextView) findViewById(R.id.doctor_email);
        TextView mAge = (TextView) findViewById(R.id.doctor_age);
        //TextView mContact = (TextView) findViewById(R.id.doctor_contact);
        TextView mAddress = (TextView) findViewById(R.id.doctor_address);
        TextView  mshift = (TextView) findViewById(R.id.shift);
        ImageView Image = (ImageView)findViewById(R.id.IMAGE);



        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        contact = getIntent().getStringExtra(" contact");
        education = getIntent().getStringExtra("education");
       // specialization = getIntent().getStringExtra("Specialization");
        experiance = getIntent().getStringExtra("experiance");
        age = getIntent().getStringExtra("age");
        adress = getIntent().getStringExtra("adress");
        shift = getIntent().getStringExtra("shift");
        image=getIntent().getStringExtra("image");

        mName.setText(name);
       // mSpecialization.setText(specialization);
        mExperiance.setText(experiance);
        mEducation.setText(education);
        mEmail.setText(email);
        mAge.setText(age);
       // mContact.setText( contact);
        mAddress.setText(adress);
        mshift.setText(shift);
        Glide.with(this ).load(image).override(300, 300) .into(Image);



    }
}