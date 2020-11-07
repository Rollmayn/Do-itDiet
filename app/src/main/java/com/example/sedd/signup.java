package com.example.sedd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    //Variables
    Button btnFinishCreate;
    EditText nameEnter, emailEnter, passEnter, ageEnter;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Initializes application in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Get values and locations of edittext and button objects
        mAuth = FirebaseAuth.getInstance();
        nameEnter = findViewById(R.id.nameEnter);
        emailEnter = findViewById(R.id.emailEnter);
        passEnter = findViewById(R.id.passEnter);
        ageEnter = findViewById(R.id.ageEnter);

        btnFinishCreate = findViewById(R.id.btnFinishCreate);
        btnFinishCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEnter.getText().toString();
                String password = passEnter.getText().toString();
                if(email.isEmpty()){
                    email.
                }
            }
        });
    }
}