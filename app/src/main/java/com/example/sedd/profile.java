package com.example.sedd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.util.Calendar;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables
    TextView textViewDate;
    ImageView menu_icon;

    //Drawer variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Initializes application in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //hooks
        TextView textViewDate = findViewById(R.id.textViewDate);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

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
        navigationView.setNavigationItemSelectedListener(profile.this);
        navigationView.setCheckedItem(R.id.nav_profile);
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
            startActivity(new Intent(profile.this, homepage.class));
        }
        else if(item.getItemId() == R.id.nav_diary){
            startActivity(new Intent(profile.this, diary.class));
        }
        else if(item.getItemId() == R.id.nav_tracker){
            startActivity(new Intent(profile.this, dailysteps.class));
        }
        else if(item.getItemId() == R.id.nav_profile){
            startActivity(new Intent(profile.this, profile.class));
        }
        return true;
    }

}