package com.example.macbookpro.gpacalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    Button btn;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);
        et7=findViewById(R.id.et7);
        et8=findViewById(R.id.et8);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn:

            float num1 = Float.parseFloat(et1.getText().toString());
            float num2 = Float.parseFloat(et2.getText().toString());
            float num3 = Float.parseFloat(et3.getText().toString());
            float num4 = Float.parseFloat(et4.getText().toString());
            float num5 = Float.parseFloat(et5.getText().toString());
            float num6 = Float.parseFloat(et6.getText().toString());
            float num7 = Float.parseFloat(et7.getText().toString());
            float num8 = Float.parseFloat(et8.getText().toString());

            float res = ((num1 * num2) + (num3 * num4) + (num5 * num6) + (num7 * num8)) /(num1+num3+num5+num7);


                Toast.makeText(getApplicationContext()," معدل " + res ,Toast.LENGTH_LONG).show();

                break;
        }


    }
}
