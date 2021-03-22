package com.example.macbookpro.plusminus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.macbookpro.plusminus.R.*;
import static com.example.macbookpro.plusminus.R.id.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etfirst;
    EditText etsecond;
    RadioButton sum;
    RadioButton subtraction;
    RadioButton multiplication;
    RadioButton division;
    Button button;
    RadioGroup group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        etfirst = findViewById(R.id.etfirst);
        etsecond = findViewById(R.id.etsecond);
        sum = findViewById(R.id.sum);
        subtraction = findViewById(R.id.subtraction);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);
        sum.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(sum.isChecked()){
            switch (v.getId()){
                case id.button:
                    int a=Integer.parseInt(etfirst.getText().toString());
                    int b=Integer.parseInt(etsecond.getText().toString());
                    int Sum=a+b;
                    Toast.makeText(getApplicationContext()," حاصل : " +  Sum,Toast.LENGTH_LONG).show();
            }
        }

        if(subtraction.isChecked()){
            switch (v.getId()){
                case id.button:
                    int a=Integer.parseInt(etfirst.getText().toString());
                    int b=Integer.parseInt(etsecond.getText().toString());
                    int Sub=a-b;
                    Toast.makeText(getApplicationContext(), " حاصل : " + Sub,Toast.LENGTH_LONG).show();
            }
        }


        if(multiplication.isChecked()){
            switch (v.getId()){
                case id.button:
                    int a=Integer.parseInt(etfirst.getText().toString());
                    int b=Integer.parseInt(etsecond.getText().toString());
                    int Mul=a*b;
                    Toast.makeText(getApplicationContext()," حاصل : " + Mul,Toast.LENGTH_LONG).show();
            }
        }

        if(division.isChecked()){
            switch (v.getId()){
                case id.button:
                    float a=Float.parseFloat(etfirst.getText().toString());
                    float b=Float.parseFloat(etsecond.getText().toString());
                    float Div=a/b;
                    Toast.makeText(getApplicationContext()," حاصل : " +  Div,Toast.LENGTH_LONG).show();
            }
        }
    }
}













