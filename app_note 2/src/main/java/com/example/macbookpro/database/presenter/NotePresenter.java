package com.example.macbookpro.database.presenter;
import android.app.Activity;
import android.content.Context;

import com.example.macbookpro.database.model.entities.NoteEntity;
import com.example.macbookpro.database.presenter.interfaces.*;
import com.example.macbookpro.database.model.*;
import com.example.macbookpro.database.view.NoteActivity;
import com.example.macbookpro.database.view.interfaces.*;

import java.util.ArrayList;

public class NotePresenter implements NotePresenterContract {

    Context context;
    NoteOperator operator=null;
    NoteView view=null;

    public NotePresenter(Context context, NoteView view) {
        this.context = context;
        this.view = view;
        operator=new NoteOperator(context);
        start();
    }

    private NotePresenter(){

    }

    @Override
    public void start() {
        ArrayList<NoteEntity> data=operator.select();
        if(view!=null)
            view.initialNoteList(data);

    }



    @Override
    public void addNoteResult(int resultCode, NoteEntity newNote) {
        if(resultCode== Activity.RESULT_OK){
            if(view!=null) view.onAddNoteSuccess(newNote);
            }else {
            if(view!=null) view.onAddNoteError();
        }

    }

    @Override
    public void onNoteStateChanged(NoteEntity note) {
        if(operator!=null)
            operator.update(note);

    }
    @Override
    public void navigateToNextActivity() {
        if(view!=null)
            view.NavigateToInsertNote();

    }



    @Override
    public void onDeleteNoteClicked(NoteEntity entity) {
        int affectedRow=operator.delete(entity);
        if(affectedRow>0)
            if(view!=null) view.removeItem(entity);
        else if(view!=null)
            view.showError("Can not Delete");

    }
}
