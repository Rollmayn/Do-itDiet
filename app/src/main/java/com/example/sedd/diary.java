package com.example.sedd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class diary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button calcBMI, storeData;
    EditText enterHeight, enterWeight;
    TextView bmiOutput, dWeight, dHeight;
    SharedPreferences sharedPreferences;

    ImageView menu_icon;

    //Drawer variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    static final String mypreference = "mypref";
    static final String sWeight = "weightKey";
    static final String sHeight = "heightKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //Initializes application in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //variable pointers
        TextView textViewDate = findViewById(R.id.textViewDate);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        calcBMI = findViewById(R.id.calcBMI);
        enterHeight = (EditText) findViewById(R.id.heightEnter);
        enterWeight = (EditText) findViewById(R.id.weightEnter);
        bmiOutput = findViewById(R.id.bmiOutput);
        storeData = findViewById(R.id.storeData);
        dWeight = findViewById(R.id.dWeight);
        dHeight = findViewById(R.id.dHeight);

        //calls navDrawer function
        navDrawer();

        //display current date and time
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        textViewDate.setText(currentDate);


        sharedPreferences = getSharedPreferences("NAME", MODE_PRIVATE);

        //calculate BMI on button press
        calcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getWeight = enterWeight.getText().toString();
                String getHeight = enterHeight.getText().toString();
                String getBmi = bmiOutput.getText().toString();

                float W = Float.parseFloat(getWeight);
                float H = Float.parseFloat(getHeight);

                float newH = H/100;
                float bmi = W/(newH*newH);

                if(bmi<18.5){
                    bmiOutput.setText("You're BMI is: " + bmi);
                }else if (bmi>=18.5 && bmi<25){
                    bmiOutput.setText("You're BMI is: " + bmi);
                }else{
                    bmiOutput.setText("You're BMI is: " + bmi);
                }
            }
        });

    }

    public void save(View view){
        String weight = enterWeight.getText().toString();
        String height = enterHeight.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(sWeight, weight);
        editor.putString(sHeight, height);
        editor.commit();
    }

    public void retrieve(View view){
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(sWeight)){
            dWeight.setText(sharedPreferences.getString(sWeight, "hi"));
        }
        if(sharedPreferences.contains(sHeight)){
            dWeight.setText(sharedPreferences.getString(sHeight, "45"));
        }

    }

    //Nav drawer functions
    private void navDrawer() {
        //menu drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_diary);
        menu_icon = findViewById(R.id.menu_icon);

        menu_icon.setOnClickListener(new View.OnClickListener() {
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
            startActivity(new Intent(diary.this, homepage.class));
        }
        else if(item.getItemId() == R.id.nav_diary){
            startActivity(new Intent(diary.this, diary.class));
        }
        else if(item.getItemId() == R.id.nav_tracker){
            startActivity(new Intent(diary.this, stepcounter.class));
        }
        else if(item.getItemId() == R.id.nav_profile){
            startActivity(new Intent(diary.this, profile.class));
        }
        return true;
    }
}