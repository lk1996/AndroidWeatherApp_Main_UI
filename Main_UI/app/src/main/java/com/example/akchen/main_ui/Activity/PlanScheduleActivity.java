package com.example.akchen.main_ui.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.akchen.main_ui.R;

/**
 * Created by Jake on 16/7/11.
 */

public class PlanScheduleActivity extends Activity {

    CalendarView cv;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planschedule);

        String[] plans0={ "大神","大大的","苏打水","生日歌人格" };
        ArrayAdapter<String> adapter0=new ArrayAdapter<String>(PlanScheduleActivity.this,R.layout.planschedulerow,plans0);
        ListView listView= (ListView) findViewById(R.id.planScheduleListView);
        listView.setAdapter(adapter0);

        cv= (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String[] plans1={ "吃饭","睡觉","学习","运动" };
                String[] plans2={ "13231","454","5454","464654","16656","156165","156165","156165","156165","156165"};
                if(year==2016&&month==6&&dayOfMonth==11){
                    ArrayAdapter<String>adapter=new ArrayAdapter<String>(PlanScheduleActivity.this,R.layout.planschedulerow,plans1);
                    ListView listView= (ListView) findViewById(R.id.planScheduleListView);
                    listView.setAdapter(adapter);
                }

                if(year==2016&&month==6&&dayOfMonth==15){
                    ArrayAdapter<String>adapter=new ArrayAdapter<String>(PlanScheduleActivity.this,R.layout.planschedulerow,plans2);
                    ListView listView= (ListView) findViewById(R.id.planScheduleListView);
                    listView.setAdapter(adapter);
                }

                Log.i("tag",Integer.toString(year));
                Log.i("tag",Integer.toString(month));
                Log.i("tag",Integer.toString(dayOfMonth));
            }
        });
        ListView listView1= (ListView) findViewById(R.id.planScheduleListView);

//        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ComponentName comp=new ComponentName(PlanScheduleActivity.this,ContentActivity.class);
//                Intent intent =new Intent();
//                intent.setComponent(comp);
//                startActivity(intent);
//            }
//        });
//
    }


}
