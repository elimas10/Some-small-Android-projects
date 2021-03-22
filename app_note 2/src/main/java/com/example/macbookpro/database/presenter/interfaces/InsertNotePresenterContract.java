package com.example.macbookpro.database.presenter.interfaces;

import java.util.Date;

public interface InsertNotePresenterContract {
   void onConfirmClicked(String title,Date selectedDate);
    void onCloseClicked();
    void onSelectDateClicked();

}
