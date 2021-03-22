package com.example.macbookpro.database.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.macbookpro.database.presenter.interfaces.*;
import com.example.macbookpro.database.model.*;
import com.example.macbookpro.database.view.InsertNoteActivity;
import com.example.macbookpro.database.view.interfaces.*;
import com.example.macbookpro.database.model.entities.*;

import java.util.Date;

public class InsertNotePresenter implements InsertNotePresenterContract {
    Context context=null;
    NoteOperator operator=null;
    InsertNoteViewContract view=null;

    public InsertNotePresenter(Context context, NoteOperator operator, InsertNoteViewContract view) {
        this.context = context;
        operator=new NoteOperator(context);
        this.view = view;
    }

    private InsertNotePresenter(){

    }

    @Override
    public void onConfirmClicked(String title, Date selectedDate) {
        if(title.trim().isEmpty()){
            if(view!=null)
                view.onInputFieldValidationFailed();
            return;
        }

        Date now=new Date();
        if(selectedDate==null || selectedDate.before(now)){
            if(view!=null)
                view.onInputFieldValidationFailed();
            return;
        }

        NoteEntity entity=new NoteEntity();
        entity.setTitle(title);
        entity.setDate(selectedDate);
        NoteEntity result=operator.insert(entity);

        if(result!=null){
            if(view!=null) view.navigateBack(Activity.RESULT_OK,result);

        }else {
            if(view!=null) view.navigateBack(Activity.RESULT_CANCELED,null);
        }

    }

    @Override
    public void onCloseClicked() {
        if(view!=null){
            view.navigateBack(Activity.RESULT_CANCELED,null);
        }


    }

    @Override
    public void onSelectDateClicked() {
        if(view!=null)
            view.showDatePicker();

    }
}
