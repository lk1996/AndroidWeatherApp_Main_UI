package com.example.akchen.main_ui.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
import com.example.akchen.main_ui.others.widget.DateTimeSelectorDialogBuilder;

/**
 * Created by Jake on 16/7/11.
 */

public class EditPlan extends Activity implements DateTimeSelectorDialogBuilder.OnSaveListener, View.OnClickListener {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editplan);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        daTextView = (TextView) findViewById(R.id.tv_date);
        endTime=(TextView)findViewById(R.id.endTime);
        daTextView.setOnClickListener(this);
        back=(Button)findViewById(R.id.back);
        endTime.setOnClickListener(this);
        editText=(EditText)findViewById(R.id.edit_text);
        editText.setMovementMethod(ScrollingMovementMethod.getInstance());
        editText.setSelection(editText.getText().length(),editText.getText().length());
        editText.getText().append("msg");
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
                        //editText.setTextIsSelectable(true);
                        //editText.setFocusable(true);
                        editText.setEnabled(true);

                        break;
                    case R.id.save:
                        Toast.makeText(EditPlan.this, "保存",
                                Toast.LENGTH_LONG).show();
                        Plan plan=new Plan();
                        plan.setTimeStart(timeStart);
                        plan.setTimeEnd(timeEnd);


                        break;
                    case R.id.delete:
                        Toast.makeText(EditPlan.this,"删除",Toast.LENGTH_SHORT).show();
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

}
