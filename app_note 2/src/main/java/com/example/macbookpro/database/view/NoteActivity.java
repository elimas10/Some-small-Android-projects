package com.example.macbookpro.database.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.macbookpro.database.R;
import com.example.macbookpro.database.model.entities.NoteEntity;
import com.example.macbookpro.database.view.interfaces.NoteView;
import com.example.macbookpro.database.view.adapters.*;
import com.example.macbookpro.database.view.interfaces.*;
import com.example.macbookpro.database.presenter.interfaces.*;
import com.example.macbookpro.database.presenter.*;
import com.example.macbookpro.database.*;
import java.util.ArrayList;
import com.example.macbookpro.database.view.adapters.NoteRecyclerAdapter;
import com.example.macbookpro.database.model.*;
import com.example.macbookpro.database.presenter.*;


public class NoteActivity extends AppCompatActivity implements NoteView,View.OnClickListener,OnNoteStateOperations {

    FloatingActionButton fab;
    RecyclerView recycler;
    NoteRecyclerAdapter adapter;

    NotePresenterContract presenter=null;
    NoteOperator operator=null;
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(this);
        recycler=findViewById(R.id.recycler);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);






    }
    @Override
    public void NavigateToInsertNote() {
        Intent i=new Intent(this,InsertNoteActivity.class);
        startActivityForResult(i,Constants.REQUEST_CODE_INSERT_NOTE);


    }

    @Override
    public void onAddNoteSuccess(NoteEntity newNote) {
        if(adapter!=null)
            adapter.addNote(newNote);

            Toast.makeText(this, "Note Inserted", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAddNoteError() {
        Toast.makeText(this,"Can Not Add new note",Toast.LENGTH_LONG).show();

    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }



    @Override
    public void removeItem(NoteEntity note) {
        adapter.removeNote(note);

    }

    @Override
    public void initialNoteList(ArrayList<NoteEntity> data) {
        adapter=new NoteRecyclerAdapter(data,this);
        adapter.setOperations(this);
        recycler.setAdapter(adapter);

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case Constants.REQUEST_CODE_INSERT_NOTE:
                NoteEntity newNote=null;
                if(data!=null){
                    Bundle bundle=data.getExtras();
                    if(bundle.size()>=3){
                        String noteTitle=bundle.getString(Constants.EXTRAS_NOTE_TITLE);
                        String date=bundle.getString(Constants.EXTRAS_NOTE_DATE);
                        newNote=new NoteEntity();
                        newNote.setTitle(noteTitle);
                        newNote.setDate(date);
                    }
                }

                if(presenter!=null)
                    presenter.addNoteResult(resultCode,newNote);
                break;

                default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                if(presenter!=null)
                    presenter.navigateToNextActivity();
                break;
        }

    }


    @Override
    public void onNoteStateChanged(NoteEntity entity) {
        if(presenter!=null)
            presenter.onNoteStateChanged(entity);

    }

    @Override
    public void onNoteDeleteClicked(NoteEntity entity) {
        if(presenter!=null)
            presenter.onDeleteNoteClicked(entity);



    }

    @Override
    public void onNoteEditClicked(NoteEntity entity) {


    }
}
