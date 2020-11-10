package com.example.sedd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class diary extends AppCompatActivity {

    Button calcBMI;
    EditText enterHeight, enterWeight;
    TextView bmiOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //variable pointers
        calcBMI = findViewById(R.id.calcBMI);
        enterHeight = findViewById(R.id.heightEnter);
        enterWeight = findViewById(R.id.weightEnter);
        bmiOutput = findViewById(R.id.bmiOutput);

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
}