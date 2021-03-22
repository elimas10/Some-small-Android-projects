package com.example.macbookpro.database.view.interfaces;
import com.example.macbookpro.database.model.entities.*;

public interface OnNoteStateOperations {
    void onNoteStateChanged(NoteEntity entity);

    void onNoteDeleteClicked(NoteEntity entity);

    void onNoteEditClicked(NoteEntity entity);
}
