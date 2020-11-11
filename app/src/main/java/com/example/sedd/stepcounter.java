package com.example.sedd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.util.Calendar;

public class stepcounter extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

    //variables
    ImageView menuIcon;
    SensorManager sensorManager;
    TextView stepCounter;
    boolean running = false;

    //Drawer variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepcounter);
        //Initializes application in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //hooks
        TextView textViewDate = findViewById(R.id.textViewDate);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_tracker);
        stepCounter = findViewById(R.id.stepCounter);

        //step counter
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //calls navDrawer function
        navDrawer();


        //display current date and time
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        textViewDate.setText(currentDate);
    }

    //Nav drawer functions
    private void navDrawer() {
        //menu drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(stepcounter.this);
        navigationView.setCheckedItem(R.id.nav_tracker);
        menuIcon = findViewById(R.id.menu_icon);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home){
            startActivity(new Intent(stepcounter.this, homepage.class));
        }
        else if(item.getItemId() == R.id.nav_diary){
            startActivity(new Intent(stepcounter.this, diary.class));
        }
        else if(item.getItemId() == R.id.nav_tracker){
            startActivity(new Intent(stepcounter.this, stepcounter.class));
        }
        else if(item.getItemId() == R.id.nav_profile){
            startActivity(new Intent(stepcounter.this, profile.class));
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, sensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //stops the step counter - wont work anymore
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            stepCounter.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}