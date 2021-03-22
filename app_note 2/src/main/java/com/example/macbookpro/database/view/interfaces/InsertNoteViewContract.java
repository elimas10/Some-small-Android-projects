package com.example.macbookpro.database.view.interfaces;

import com.example.macbookpro.database.model.entities.*;

public interface InsertNoteViewContract {
    void showDatePicker();
    void navigateBack(int resultCode,NoteEntity note);
    void onInputFieldValidationFailed();
}
