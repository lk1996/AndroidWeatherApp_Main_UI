package com.example.akchen.main_ui.Activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.akchen.main_ui.R;
import com.example.akchen.main_ui.others.utils.Plan;
import com.example.akchen.main_ui.others.utils.WeatherDB;


import java.util.List;

/**
 * Created by Jake on 16/7/11.
 */

public class PlanScheduleActivity extends AppCompatActivity {

    CalendarView cv;
    String time;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planschedule);
        getSupportActionBar().hide();
        //显示当天的计划
        WeatherDB wd = WeatherDB.getInstance(PlanScheduleActivity.this);
        final Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
        t.setToNow(); // 取得系统时间。
<<<<<<< HEAD
        int year = t.year;
        int month = t.month;
        int date = t.monthDay;
        String startime = String.valueOf(year) + "年" + String.valueOf(month) + "月" + String.valueOf(date) + "日";
        int numofplans = wd.queryFromTime(startime, 0).size();
        final String plans0[] = new String[numofplans];
        for (int i = 0; i < numofplans; i++) {
            plans0[i] = wd.queryFromTime(startime, 0).get(i).getPlanName();
        }
        time = startime;
        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(PlanScheduleActivity.this, R.layout.planschedulerow, plans0);
        ListView listView = (ListView) findViewById(R.id.planScheduleListView);
        listView.setAdapter(adapter0);
=======
        String year =String.valueOf(t.year) ;
        String month="01";
        String date="01";
        if(t.month<9){
            month="0"+String.valueOf(t.month+1);
        }else {
            month=String.valueOf(t.month+1);
        }

        if(t.monthDay<9){
            date="0"+ String.valueOf(t.monthDay);
        }else{
            date=String.valueOf(t.monthDay);
        }

        String startime=year+"-"+month+"-"+date;
        time = startime;
        int numofplans=wd.queryFromTime(startime,1).size();
        Log.i("time",time);
        Log.i("num",String.valueOf(numofplans));
            if(numofplans>0) {
            final String plans0[] = new String[numofplans];
            for (int i = 0; i < numofplans; i++) {
                plans0[i] = wd.queryFromTime(startime, 1).get(i).getPlanName();
            }
            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(PlanScheduleActivity.this, R.layout.planschedulerow, plans0);
            ListView listView = (ListView) findViewById(R.id.planScheduleListView);
            listView.setAdapter(adapter0);
        }
>>>>>>> pr/10

        cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                WeatherDB w = WeatherDB.getInstance(PlanScheduleActivity.this);
<<<<<<< HEAD
                String startime = String.valueOf(year) + "年" + String.valueOf(month + 1) + "月" + String.valueOf(dayOfMonth) + "日";
                time = startime;
                //根据开始时间获取这天的计划数
                int numofplans = w.queryFromTime(startime, 0).size();
                String plans1[] = new String[numofplans];
                for (int i = 0; i < numofplans; i++) {
                    plans1[i] = w.queryFromTime(startime, 0).get(i).getPlanName();
=======
                String y =String.valueOf(year) ;
                String m="01";
                String d="01";
                if(month<9){
                    m="0"+String.valueOf(month+1);
                }else {
                    m=String.valueOf(month+1);
                }

                if(dayOfMonth<9){
                    d="0"+ String.valueOf(dayOfMonth);
                }else{
                    d=String.valueOf(dayOfMonth);
                }

                String startime=y+"-"+m+"-"+d;
                time=startime;
                //根据开始时间获取这天的计划数
                int numofplans=w.queryFromTime(startime,1).size();
                String plans1[]=new String[numofplans];
                for (int i=0;i<numofplans;i++){
                    plans1[i]=w.queryFromTime(startime,1).get(i).getPlanName();
>>>>>>> pr/10
                }
                //适配器
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlanScheduleActivity.this, R.layout.planschedulerow, plans1);
                ListView listView = (ListView) findViewById(R.id.planScheduleListView);
                listView.setAdapter(adapter);
                //测试
<<<<<<< HEAD
                Log.i("tag", Integer.toString(year));
                Log.i("tag", Integer.toString(month));
                Log.i("tag", Integer.toString(dayOfMonth));
            }
        });

        ListView listView1 = (ListView) findViewById(R.id.planScheduleListView);
=======

            }
        });

        ListView listView= (ListView) findViewById(R.id.planScheduleListView);
>>>>>>> pr/10

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WeatherDB weatherDB = WeatherDB.getInstance(PlanScheduleActivity.this);

<<<<<<< HEAD
                Plan plan = weatherDB.queryFromTime(time, 0).get(i);
                Bundle data = new Bundle();
                data.putSerializable("plan", plan);
                data.putInt("LEVEL_START", 1);
                Intent intent = new Intent(PlanScheduleActivity.this, EditPlanActivity.class);
                intent.putExtras(data);
                startActivity(intent);

                /*****************************
                 Plan p=new Plan();
                 p.setTimeEnd("2016年7月14日");
                 p.setTimeStart("2016年7月10日");
                 p.setPlanContent("kskjashdkjasdkjasndas");
                 p.setPlanName("test1");

                 Plan po=new Plan();
                 po.setTimeEnd("2016年7月14日");
                 po.setTimeStart("2016年7月10日");
                 po.setPlanContent("kskjashdkjasdkjasndas");
                 po.setPlanName("test1");
                 wd.savePlan(p);
                 wd.savePlan(po);

                 WeatherDB w = WeatherDB.getInstance(PlanScheduleActivity.this);
                 List<String> temp= w.queryPlan(1);


                 Log.i("id",temp.get(0));
                 Log.i("userid",temp.get(1));
                 Log.i("startime",temp.get(4));

                 List<Plan> list=w.queryFromTime("2016年7月10日",0);
                 Log.i("name",list.get(0).getPlanName());
                 Log.i("startTime",list.get(0).getTimeStart());
                 */
=======
                Plan plan=weatherDB.queryFromTime(time,1).get(i);
                Bundle data=new Bundle();
                data.putSerializable("plan",plan);
                data.putInt("CURRENT_LEAVE",1);
                Intent intent =new Intent(PlanScheduleActivity.this,EditPlanActivity.class);
                intent.putExtras(data);
                startActivity(intent);
>>>>>>> pr/10
            }
        });
    }


}
