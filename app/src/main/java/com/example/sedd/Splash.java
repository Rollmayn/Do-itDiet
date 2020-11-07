package com.example.sedd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Splash extends AppCompatActivity {

    //Variables
    private Button btnBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Initializes application in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //makes button interactable, initializes openLogin() function when clicked.
        btnBegin = (Button) findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            //openLogin function recalled in onClick function
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    //function to open Login activity once button clicked
    public void openLogin(){
        Intent startApp = new Intent(Splash.this, signin.class);
        startActivity(startApp);
    }
}