package patient;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clinicappointmentapp.R;

import java.util.Calendar;

public class Patient_DoctorProfileActivity extends AppCompatActivity {

    private TextView mName, mEducation, mSpecialization, mExperience, mContactNo, mShift;
    private String shift;

    private Button mBookAppointmentBtn;
    private Toolbar mToolbar;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__doctor_profile);

        //Toolbar
       // mToolbar = (Toolbar) findViewById(R.id.patient_doctorProfile_toolbar);
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setTitle("Doctor Profile");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mName = (TextView) findViewById(R.id.patient_doctorProfile_name);
        mSpecialization = (TextView) findViewById(R.id.patient_doctorProfile_specialization);
        mEducation = (TextView) findViewById(R.id.patient_doctorProfile_education);
        mExperience = (TextView) findViewById(R.id.patient_doctorProfile_experiance);
        mContactNo = (TextView) findViewById(R.id.patient_doctorProfile_contact);
        mShift = (TextView) findViewById(R.id.patient_doctorProfile_shift);

        mBookAppointmentBtn = (Button) findViewById(R.id.book_appointment_button);
        mBookAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Patient_DoctorProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String userId = getIntent().getStringExtra("UserId").toString();

                        String date = dayOfMonth +"-"+ (month+1) +"-"+ year;
//                        Toast.makeText(Patient_DoctorProfileActivity.this, date , Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Patient_DoctorProfileActivity.this, Patient_BookAppointmentActivity.class);
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
    }

    @Override
    protected void onStart() {
        super.onStart();

        String name = getIntent().getStringExtra("Name");
        String education = getIntent().getStringExtra("Education");
        String specialization = getIntent().getStringExtra("Specialization");
        String experience = getIntent().getStringExtra("Experiance");
        String contact = getIntent().getStringExtra("Contact");
        shift = getIntent().getStringExtra("Shift");

        mName.setText(name);
        mEducation.setText(education);
        mSpecialization.setText(specialization);
        mExperience.setText(experience);
        mContactNo.setText(contact);
        mShift.setText(shift);
    }
}
