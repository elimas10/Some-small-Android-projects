package com.example.macbookpro.database.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.macbookpro.database.*;

import com.example.macbookpro.database.model.entities.NoteEntity;
import com.example.macbookpro.database.view.interfaces.*;

import com.example.macbookpro.database.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.example.macbookpro.database.presenter.interfaces.*;
import com.example.macbookpro.database.presenter.*;
import com.example.macbookpro.database.model.*;

public class InsertNoteActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener,InsertNoteViewContract {

     EditText etNote;
     TextView txtDate;
     Button btnDate;
     Date selectedDate=null;
     InsertNotePresenterContract presenter=null;
     Context context;
     NoteOperator operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
        etNote=findViewById(R.id.etNote);
        txtDate=findViewById(R.id.txtDate);
        btnDate=findViewById(R.id.btnDate);
        btnDate.setOnClickListener(this);
        presenter=new InsertNotePresenter(context,operator,this );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert_note, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.done:
                if(presenter!=null)
                    presenter.onConfirmClicked(etNote.getText().toString(),selectedDate);
                return true;
            case R.id.close:
                if(presenter!=null)
                    presenter.onCloseClicked();
                return true;

                default:
                    return false;
        }
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDate:
                if(presenter!=null)
                    presenter.onSelectDateClicked();
                break;
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month);
        instance.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);

        selectedDate = instance.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = sdf.format(selectedDate);
        txtDate.setText("Selected: " + formattedDate);

    }

    @Override
    public void showDatePicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog pickerDialog = new
                DatePickerDialog(this, this, year, month, dayOfMonth);
        pickerDialog.show();

    }

    @Override
    public void onInputFieldValidationFailed() {
        Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void navigateBack(int resultCode, NoteEntity note) {
        if (resultCode == RESULT_OK) {
            Intent data = new Intent();
         data.putExtra(Constants.EXTRAS_NOTE_TITLE,note.getTitle());
         data.putExtra(Constants.EXTRAS_NOTE_DATE,note.getDateInString());
         setResult(RESULT_OK,data);

        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }



}
