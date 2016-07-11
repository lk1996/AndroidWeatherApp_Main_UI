package com.example.akchen.main_ui.others.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.akchen.main_ui.others.CreateDBAndTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 2016/7/7.
 */
public class WeatherDB {
    public static final String DB_NAME = "Weather_DB";
    public static final int VERSION = 2;
    private static WeatherDB weatherDB;
    private SQLiteDatabase db;

    private WeatherDB(Context context) {
        CreateDBAndTable helper = new CreateDBAndTable(context, DB_NAME, null, VERSION);
        db = helper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    public void savePlan(Plan plan)
    {
        if(plan!=null)
        {
            ContentValues values = new ContentValues();
            values.put("id",plan.getId());
            values.put("plan_name",plan.getPlanName());
            values.put("plan_content",plan.getPlanContent());
            values.put("time_start",plan.getTimeStart());
            values.put("time_end",plan.getTimeEnd());
            db.insert("Plan",null,values);
        }
    }

    public List<Plan> loadPlan()
    {
        List<Plan> list=new ArrayList<Plan>();
        Cursor cursor=db.query("Plan",null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do {
                Plan plan = new Plan();
                plan.setId(cursor.getInt(cursor.getColumnIndex("id")));
                plan.setPlanName(cursor.getString(cursor.getColumnIndex("plan_name")));
                plan.setPlanContent(cursor.getString(cursor.getColumnIndex("plan_content")));
                plan.setTimeStart(cursor.getString(cursor.getColumnIndex("time_start")));
                plan.setTimeEnd(cursor.getString(cursor.getColumnIndex("time_end")));
                list.add(plan);
            }while (cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }

    public List<String> queryPlan(int id)
    {
        List<String> list=new ArrayList<String>();
        Cursor cursor=db.query("Plan",null,"id=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor.moveToFirst())
        {
            list.add(String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
            list.add(cursor.getString(cursor.getColumnIndex("plan_name")));
            list.add(cursor.getString(cursor.getColumnIndex("plan_content")));
            list.add(cursor.getString(cursor.getColumnIndex("time_start")));
            list.add(cursor.getString(cursor.getColumnIndex("time_end")));
        }
        if (cursor!=null)
        {
            cursor.close();
        }
        return list;
    }
    public void delete(int id)
    {
        int result = db.delete("Plan","id=?",new String[]{String.valueOf(id)});
    }
}
