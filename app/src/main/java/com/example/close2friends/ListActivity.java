package com.example.close2friends;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    final int CAMERA_PERMISSION_REQUEST=1;

    Button new_contact;
    ImageButton pic_btn,date_btn,call_btn;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pic_btn=findViewById(R.id.take_pic);
        date_btn=findViewById(R.id.take_date);
        call_btn=findViewById(R.id.take_call);
        new_contact=findViewById(R.id.contact1);




        new_contact.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new_contact.setBackgroundResource(R.drawable.contactpress);


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    new_contact.setBackgroundResource(R.drawable.clrprt);
                    Intent intent=new Intent(ListActivity.this,AddNewActivity.class);
                    startActivity(intent);
                }
                return  false;
            }

        });

        pic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pic_btn.setImageResource(R.drawable.camerapress);
                if(Build.VERSION.SDK_INT>=23){
                    int hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);
                    if (hasCameraPermission==PackageManager.PERMISSION_GRANTED){
                        pic_btn.setImageResource(R.drawable.cameranpress);
                        startCamera();
                    }
                    else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_REQUEST);
                    }
                }
                else{
                    pic_btn.setImageResource(R.drawable.cameranpress);
                    startCamera();
                }

            }
        });

        date_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    date_btn.setImageResource(R.drawable.datepressed);
                    Toast.makeText(ListActivity.this,"Schedule Events with your Friends!", Toast.LENGTH_SHORT).show();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    date_btn.setImageResource(R.drawable.datenpressed);
                }
                return  true;
            }

        });

        call_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    call_btn.setImageResource(R.drawable.phonepressed);
                    Toast.makeText(ListActivity.this,"Call your Friends!", Toast.LENGTH_SHORT).show();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    call_btn.setImageResource(R.drawable.phonenpress);
                }
                return  true;
            }

        });
    }
    private void startCamera(){
        Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
}


/*
        exists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListActivity.this,AddNewActivity.class);

            }
        });



        pic_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    pic_btn.setImageResource(R.drawable.camerapress);
                    Toast.makeText(ListActivity.this,"Take Picture of your Friends!", Toast.LENGTH_SHORT).show();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    pic_btn.setImageResource(R.drawable.cameranpress);
                    if(Build.VERSION.SDK_INT>=23){
                        int hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);
                        if (hasCameraPermission==PackageManager.PERMISSION_GRANTED){
                            startCamera();
                        }
                        else {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_REQUEST);
                        }
                    }
                    else {startCamera();}


                }
                return  true;
            }

        });







    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CAMERA_PERMISSION_REQUEST){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                startCamera();
            }
            else {
                Toast.makeText(this,"NO PERMISSIONS",Toast.LENGTH_LONG).show();
            }
        }*/