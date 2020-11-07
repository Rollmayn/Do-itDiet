package com.example.sedd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    //Variables
    TextView signupTxt1, signupTxt2;
    Button btnFinishCreate;
    EditText nameEnter, emailEnter, passEnter, ageEnter;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Initializes application in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Get values and locations of edittext and button object
        nameEnter = findViewById(R.id.nameEnter);
        emailEnter = findViewById(R.id.emailEnter);
        passEnter = findViewById(R.id.passEnter);
        ageEnter = findViewById(R.id.ageEnter);

        mAuth = FirebaseAuth.getInstance();
        //Keeps user signed in -WIP
//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), home.class));
//            finish();
//        }

        btnFinishCreate = findViewById(R.id.btnFinishCreate);
        btnFinishCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEnter.getText().toString().trim();
                String password = passEnter.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailEnter.setError("Email is required!");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    passEnter.setError("Password is required!");
                    return;
                } else if (password.length() > 6) {
                    passEnter.setError("Password must be more than 6 characters!");
                    return;
                }

                //registers user in database
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signup.this, "Account created!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), home.class));

                        } else {
                            Toast.makeText(signup.this, "Error occurred. Please try again later..." + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }
}