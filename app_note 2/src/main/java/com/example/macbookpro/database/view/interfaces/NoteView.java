package com.example.macbookpro.database.view.interfaces;

import com.example.macbookpro.database.model.entities.*;

import java.util.ArrayList;

public interface NoteView {
    void onAddNoteSuccess(NoteEntity newNote);
    void onAddNoteError();
    void showError(String text);
    void NavigateToInsertNote();
    void removeItem(NoteEntity note);
    void initialNoteList(ArrayList<NoteEntity> data);

}
