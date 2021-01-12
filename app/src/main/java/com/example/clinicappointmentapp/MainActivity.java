package com.example.clinicappointmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    private String Type = "", status = "";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Fragment_SectionPagerAdapter mFragment_SectionPagerAdapter;

    //Firebase Auth
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private DatabaseReference mUserDatabase = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          //Toolbar
       // mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
       // setSupportActionBar(mToolbar);
      //getSupportActionBar().setTitle("clinic appointment");

        //NavigationView
        mNavigationView = (NavigationView) findViewById(R.id.main_nav_view);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.admin:

                Intent admin_Intent = new Intent(MainActivity.this, admin.class);
                startActivity(admin_Intent);

                break;

            case R.id.nav_showAppointment:

                Intent showAppointment_Intent = new Intent(MainActivity.this, patient_ShowAppointmentActivity.class);
                startActivity(showAppointment_Intent);
                break;

            case R.id.doctors:

                Intent doctorsintent = new Intent(MainActivity.this, doctors.class);
                startActivity(doctorsintent);
                break;

            case R.id.nav_aboutapp:
                Intent about_Intent = new Intent(MainActivity.this, about.class);
                startActivity(about_Intent);
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                onStart();

                Toast.makeText(getBaseContext(), "Successfully Logged Out", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_feedback:

                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                break;

            case R.id.notification:

                startActivity(new Intent(MainActivity.this, notifications.class));
                break;


            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }}
