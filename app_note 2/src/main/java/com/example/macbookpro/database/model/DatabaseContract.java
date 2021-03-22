package com.example.macbookpro.database.model;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String DB_NAME="Notes.db";
    public static final int DB_VERSION = 1;
    public static final String TYPE_TEXT=" TEXT ";
    public static final String TYPE_INTEGER=" INTEGER ";
    public static final String COMMA=" , ";

    public static final String CREATE_TABLE="CREATE TABLE "+Tasks.TABLE_NAME + " ( "
            + Tasks.COLUMN_NAME_TITLE + TYPE_TEXT + " NOT NULL " + COMMA + Tasks.COLUMN_NAME_CONTENT + TYPE_TEXT + COMMA +
            Tasks.COLUMN_NAME_CREATE_DATE + TYPE_TEXT + " ) ; ";

    public static final String DROP_TABLE ="DROP_TABLE" + Tasks.TABLE_NAME;




    public class Tasks implements BaseColumns{
        public static final String TABLE_NAME="Notes";
        public static final String COLUMN_NAME_TITLE  = "title";
        public static final String COLUMN_NAME_CONTENT  = "content";
        public static final String COLUMN_NAME_CREATE_DATE = "createDate";

    }

}
