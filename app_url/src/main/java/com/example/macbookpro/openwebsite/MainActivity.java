package com.example.macbookpro.openwebsite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    EditText et_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_url=findViewById(R.id.et_url);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent("android.intent.action.VIEW");
        Uri link=Uri.parse("http://"+ et_url.getText().toString());
        intent.setData(link);
        startActivity(intent);

    }
}
