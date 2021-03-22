package com.example.macbookpro.database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.macbookpro.database.model.DatabaseContract;
import com.example.macbookpro.database.model.DatabaseHelper;
import com.example.macbookpro.database.model.entities.NoteEntity;
import java.util.ArrayList;

public class NoteOperator {

    Context context=null;

    public NoteOperator(Context context) {

        this.context = context;
    }

    private NoteOperator() {
    }

    public ArrayList<NoteEntity> select(){
        ArrayList<NoteEntity> data=new ArrayList<>();
        DatabaseHelper helper=new DatabaseHelper(context);
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();

        Cursor cursor=readableDatabase.query(DatabaseContract.Tasks.TABLE_NAME,new String[]{DatabaseContract.Tasks.COLUMN_NAME_CONTENT,
                DatabaseContract.Tasks.COLUMN_NAME_TITLE,DatabaseContract.Tasks.COLUMN_NAME_CREATE_DATE},null,null,null,null,null);

        if(cursor.getCount()<1){
            if(readableDatabase.isOpen())
                readableDatabase.close();
            return data;
        }

        while (cursor.moveToNext()){

            int createDateIndex=cursor.getColumnIndex(DatabaseContract.Tasks.COLUMN_NAME_CREATE_DATE);
            String createDate=cursor.getString(createDateIndex);

            int titleIndex=cursor.getColumnIndex(DatabaseContract.Tasks.COLUMN_NAME_TITLE);
            String title=cursor.getString(titleIndex);

            int contentIndex=cursor.getColumnIndex(DatabaseContract.Tasks.COLUMN_NAME_CONTENT);
            String content=cursor.getString(contentIndex);


            NoteEntity entity=new NoteEntity(createDate,title,content);
            data.add(entity);

        }

        if(readableDatabase.isOpen())
            readableDatabase.close();
        return data;

    }

    public NoteEntity insert(NoteEntity note){
        DatabaseHelper helper=new DatabaseHelper(context);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        ContentValues contentV=new ContentValues();
        contentV.put(DatabaseContract.Tasks.COLUMN_NAME_CONTENT,note.getContent());
        contentV.put(DatabaseContract.Tasks.COLUMN_NAME_CREATE_DATE,note.getDateInString());
        contentV.put(DatabaseContract.Tasks.COLUMN_NAME_TITLE,note.getTitle());

       // long id=writableDatabase.insert(DatabaseContract.Tasks.TABLE_NAME,null,contentV);


        if(writableDatabase.isOpen())
            writableDatabase.close();

        return note;

    }

    public int update(NoteEntity note){

        int affectedRows=1;
        DatabaseHelper helper=new DatabaseHelper(context);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        ContentValues contentV=new ContentValues();
        contentV.put(DatabaseContract.Tasks.COLUMN_NAME_CONTENT,note.getContent());
        contentV.put(DatabaseContract.Tasks.COLUMN_NAME_CREATE_DATE,note.getDateInString());
        contentV.put(DatabaseContract.Tasks.COLUMN_NAME_TITLE,note.getTitle());

        affectedRows=writableDatabase.update(DatabaseContract.Tasks.TABLE_NAME,contentV,
                DatabaseContract.Tasks.COLUMN_NAME_TITLE + " =?",new String[]{String.valueOf(note.getTitle())});

        if(writableDatabase.isOpen())
            writableDatabase.close();


        return affectedRows;

    }

    public int delete(NoteEntity note){

        int affectedRows = 1;
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();


        writableDatabase.delete(DatabaseContract.Tasks.TABLE_NAME,
                DatabaseContract.Tasks.COLUMN_NAME_TITLE + " = ? ", new String[]{String.valueOf(note.getTitle())});

        if (writableDatabase.isOpen()) writableDatabase.close();

        return affectedRows;

    }




}
