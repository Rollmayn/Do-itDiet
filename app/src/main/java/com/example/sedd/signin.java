package com.example.sedd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {

    //Variables
    EditText emailEnter, passwordEnter;
    Button btnLogin, btnCreateAccount;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //Initializes application in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        emailEnter = findViewById(R.id.emailEnter);
        passwordEnter = findViewById(R.id.passwordEnter);
        mAuth = FirebaseAuth.getInstance();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEnter.getText().toString().trim();
                String password = passwordEnter.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailEnter.setError("Email is required!");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    passwordEnter.setError("Password is required!");
                    return;
                } else if (password.length() > 6) {
                    passwordEnter.setError("Password must be more than 6 characters!");
                    return;
                }

                //Authenticate users
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signin.this, "Logged in!!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), homepage.class));
                        } else {
                            Toast.makeText(signin.this, "Error occurred. Please try again." + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //Initialize account creation activity
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAccount();
            }
        });
    }

    public void startAccount() {
        Intent open = new Intent(this, signup.class);
        startActivity(open);
    }
}