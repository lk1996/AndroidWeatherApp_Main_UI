package com.example.akchen.main_ui.others;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alan on 2016/7/7.
 */
public class CreateDBAndTable extends SQLiteOpenHelper {

    public static final String CREATE_PLAN="create table Plan ("
            +"id integer primary key autoincrement, "
            +"time_start text, "
            +"time_end text, "
            +"plan_name text, "
            +"plan_content integer)";
    public CreateDBAndTable(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(CREATE_PLAN);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("drop table if exists Plan");
        onCreate(db);

    }
}
