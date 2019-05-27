package com.example.close2friends;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    TextView date_view;
    EditText title,description;
    ImageButton ok,not_ok;
    Button date_btn;
    final int CALENDAR_PERMISSION_REQUEST=3;
    int chooseYear = -1, chooseMonth = -1, chooseDayOfMonth = -1, chooseHour = -1, chooseMinute = -1, chooseDayOfWeek = -1;
    Calendar beginTimeDate = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        ok=findViewById(R.id.okIB);
        not_ok=findViewById(R.id.notokIB);
        date_btn=findViewById(R.id.datepicker);
        date_view=findViewById(R.id.dateTV);
        title=findViewById(R.id.titleET);
        description=findViewById(R.id.infoET);


        ok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    ok.setImageResource(R.drawable.okpress);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ok.setImageResource(R.drawable.oknopress);
                    Toast.makeText(DateActivity.this,"Saved!", Toast.LENGTH_SHORT).show();
                    if(Build.VERSION.SDK_INT>=23){
                        int hasCameraPermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR);
                        if (hasCameraPermission== PackageManager.PERMISSION_GRANTED){
                            saveDate();
                            finish();
                        }
                        else {
                            requestPermissions(new String[]{Manifest.permission.WRITE_CALENDAR},CALENDAR_PERMISSION_REQUEST);
                        }
                    }
                    else{
                        saveDate();
                        finish();
                    }
                }
                return  true;
            }

        });

        not_ok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    not_ok.setImageResource(R.drawable.notpress);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    not_ok.setImageResource(R.drawable.notnopress);
                    Toast.makeText(DateActivity.this,"Discarded!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return  true;
            }

        });

        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(DateActivity.this);
            }
        });

    }
    private void showPopup(Activity context){
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext()
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.activity_onlydate, null, false);
        final PopupWindow popupWindow = new PopupWindow(
                layout,1000,400);

        popupWindow.setContentView(layout);
        popupWindow.setHeight(1000);
        popupWindow.setOutsideTouchable(false);
        // Clear the default translucent background
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        CalendarView cv = (CalendarView) layout.findViewById(R.id.calendarView);
        cv.setBackgroundColor(Color.WHITE);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
                chooseYear = year;
                chooseMonth = month;
                chooseDayOfMonth = dayOfMonth;
                date_view.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                //Log.d("date selected", "date selected " + year + " " + month + " " + dayOfMonth);

            }
        });
        popupWindow.showAtLocation(layout, Gravity.TOP,5,170);

    }
    private void saveDate(){
        beginTimeDate.set(chooseYear, (chooseMonth - 1), chooseDayOfMonth, chooseHour, chooseMinute);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title.getText().toString())
                .putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
        startActivity(intent);

    }
}
