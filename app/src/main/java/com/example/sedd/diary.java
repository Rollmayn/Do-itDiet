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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class diary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button calcBMI, addFood;
    EditText enterHeight, enterWeight, food1, food2, food3, food4;
    TextView bmiOutput, dWeight, dHeight, calories;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;

    ImageView menu_icon;

    //Drawer variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;

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
        dWeight = findViewById(R.id.dWeight);
        dHeight = findViewById(R.id.dHeight);

        //variable pointers for calorie addition
        addFood = findViewById(R.id.addFood);
        food1 = findViewById(R.id.food1);
        food2 = findViewById(R.id.food2);
        food3 = findViewById(R.id.food3);
        food4 = findViewById(R.id.food4);
        calories = findViewById(R.id.calories);

        //calls navDrawer function
        navDrawer();

        //display current date and time
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        textViewDate.setText(currentDate);

        //calculate BMI on button press
        calcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getWeight = enterWeight.getText().toString();
                String getHeight = enterHeight.getText().toString();
                String getBmi = bmiOutput.getText().toString();

                float W = Float.parseFloat(getWeight);
                float H = Float.parseFloat(getHeight);
                H = H/100;
                float bmi = W/(H*H);

                if(bmi<18.5){
                    bmiOutput.setText("You should eat some more bananas! " + bmi);
                }else if (bmi>=18.5 && bmi<25){
                    bmiOutput.setText("Snack on a banana! " + bmi);
                }else{
                    bmiOutput.setText("No more bananas for you! " + bmi);
                }
            }
        });

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer temp = Integer.parseInt(food1.getText().toString());
                Integer temp1 = Integer.parseInt(food2.getText().toString());
                Integer temp2 = Integer.parseInt(food3.getText().toString());
                Integer temp3 = Integer.parseInt(food4.getText().toString());
                Integer temp4 = temp + temp1 + temp2 + temp3;
                calories.setText(Integer.toString(temp4));
            }
        });

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
            startActivity(new Intent(diary.this, dailysteps.class));
        }
        else if(item.getItemId() == R.id.nav_profile){
            startActivity(new Intent(diary.this, profile.class));
        }
        return true;
    }
}