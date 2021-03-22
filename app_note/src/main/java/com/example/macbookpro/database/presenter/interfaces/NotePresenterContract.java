package com.example.macbookpro.database.presenter.interfaces;
import com.example.macbookpro.database.model.entities.NoteEntity;

public interface NotePresenterContract {

    void start();
    void navigateToNextActivity();
    void addNoteResult(int resultCode,NoteEntity newNote);
    void onNoteStateChanged(NoteEntity note);
    void onDeleteNoteClicked(NoteEntity entity);


}
