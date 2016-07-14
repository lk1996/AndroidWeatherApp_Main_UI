package com.example.akchen.main_ui.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akchen.main_ui.R;
import com.example.akchen.main_ui.others.utils.Plan;
import com.example.akchen.main_ui.others.utils.WeatherDB;
import com.example.akchen.main_ui.others.widget.DateTimeSelectorDialogBuilder;

/**
 * Created by Jake on 16/7/11.
 */

public class EditPlanActivity extends Activity implements DateTimeSelectorDialogBuilder.OnSaveListener, View.OnClickListener {
    private DateTimeSelectorDialogBuilder dialogBuilder;
    private TextView daTextView;
    private TextView endTime;
    private int LEAVE_START=1;
    private int LEAVE_END=2;
    private int  current=0;
    private EditText editText;
    private PopupMenu popupMenu;
    private Menu menu;
    private Button back;
    private String timeStart;
    private String timeEnd;
    private WeatherDB weatherDB;
    private TextView title;
    private int CURRENT_LEAVE=0;
    private String newPlanName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editplan);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        daTextView = (TextView) findViewById(R.id.tv_date);
        endTime=(TextView)findViewById(R.id.endTime);

/**********        Test   
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        Plan plan=(Plan)bundle.getSerializable("plan");
        daTextView.setText(plan.getPlanName());
        endTime.setText(String.valueOf(bundle.getInt("LEVEL_START")));

*****************/


        daTextView.setOnClickListener(this);
        daTextView.setEnabled(false);
        back=(Button)findViewById(R.id.back);
        endTime.setOnClickListener(this);
        endTime.setEnabled(false);
        editText=(EditText)findViewById(R.id.edit_text);
        title=(TextView)findViewById(R.id.Title);
        editText.setMovementMethod(ScrollingMovementMethod.getInstance());
        editText.setSelection(editText.getText().length(),editText.getText().length());
        weatherDB=WeatherDB.getInstance(this);
        final Intent intent = getIntent();
        final Plan intentPlan = (Plan)intent.getSerializableExtra("plan");
        CURRENT_LEAVE =intent.getIntExtra("CURRENT_LEAVE",2);
        if(CURRENT_LEAVE==LEAVE_START)
        {
            editText.setText(intentPlan.getPlanContent());
            title.setText(intentPlan.getPlanName());
        }
        else if(CURRENT_LEAVE==LEAVE_END)
        {
            title.setText("New Plan");
        }

//        ActionBar actionBar=getActionBar();
//        actionBar.hide();
        popupMenu=new PopupMenu(this,findViewById(R.id.Menu));
        menu=popupMenu.getMenu();
        getMenuInflater().inflate(R.menu.popup_menu,menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.motify:

                        editText.setEnabled(true);
                        daTextView.setEnabled(true);
                        endTime.setEnabled(true);
                        break;
                    case R.id.save:
                        Toast.makeText(EditPlanActivity.this, "保存",
                                Toast.LENGTH_LONG).show();
                        Plan plan=new Plan();

                        plan.setTimeStart(timeStart);
                        plan.setTimeEnd(timeEnd);
                        plan.setId(intentPlan.getId());
                        plan.setUserId(intentPlan.getUserId());
                        plan.setPlanName(title.getText().toString());
                        plan.setPlanContent(editText.getText().toString());
                        if(CURRENT_LEAVE==LEAVE_START)
                        {
                            weatherDB.update(plan);
                            Intent intent1 = new Intent(EditPlanActivity.this,MainUIActivity.class);
                            startActivity(intent1);
                        }
                        else if(CURRENT_LEAVE==LEAVE_END)
                        {
                            showDialog();
                            if(!"".equals(newPlanName)&&newPlanName!=null)
                            {
                                plan.setPlanName(newPlanName);
                                weatherDB.savePlan(plan);
                                Intent intent1 = new Intent(EditPlanActivity.this,MainUIActivity.class);
                                startActivity(intent1);
                            }
                        }
                        break;
                    case R.id.delete:
                        Toast.makeText(EditPlanActivity.this,"删除",Toast.LENGTH_SHORT).show();
                        if(CURRENT_LEAVE==LEAVE_START)
                        {
                            weatherDB.delete("Plan",intentPlan.getId());
                        }
                        else if(CURRENT_LEAVE==LEAVE_END)
                        {
                            Intent intent1=new Intent(EditPlanActivity.this,MainUIActivity.class);
                            startActivity(intent1);
                        }
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    public void popupmenu(View v)
    {
        popupMenu.show();
    }
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.endTime:
                if(dialogBuilder==null)
                {
                    current=LEAVE_END;
                    dialogBuilder = DateTimeSelectorDialogBuilder.getInstance(this);
                    dialogBuilder.setOnSaveListener(this);

                    dialogBuilder.show();
                    break;
                }

            case R.id.tv_date:
                if(dialogBuilder==null)
                {
                    current=LEAVE_START;
                    dialogBuilder = DateTimeSelectorDialogBuilder.getInstance(this);
                    dialogBuilder.setOnSaveListener(this);
                    dialogBuilder.show();
                    break;
                }


        }
    }

    @Override
    public void onSaveSelectedDate(String selectedDate) {


        if(current==LEAVE_END)
        {
            endTime.setText(selectedDate);
            timeEnd=selectedDate;
            dialogBuilder=null;
        }
        else  if(current==LEAVE_START)
        {
            daTextView.setText(selectedDate);
            timeStart=selectedDate;
            dialogBuilder=null;
        }

    }
    public void showDialog()
    {
        final Context context=this;
        final EditText userName=new EditText(this);
        new AlertDialog.Builder(context)
                .setTitle("请输入新的计划名：")
                .setView(userName)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"UserName:"+userName.getText().toString(),Toast.LENGTH_SHORT).show();
                        newPlanName=userName.getText().toString();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
}

