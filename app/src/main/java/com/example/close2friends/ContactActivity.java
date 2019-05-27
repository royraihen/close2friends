package com.example.close2friends;

import android.Manifest;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    final int CAMERA_PERMISSION_REQUEST=1;
    final int CALL_PERMISSION_REQUEST=2;

    ImageButton pic_btn,date_btn,call_btn,sms_btn,back_btn,profile;
    TextView phone, name, nick, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final String phone_num, full_name, nick_name, email_add;
        profile=findViewById(R.id.profile_pic);
        pic_btn = findViewById(R.id.take_pic);
        date_btn = findViewById(R.id.take_date);
        call_btn = findViewById(R.id.take_call);
        sms_btn = findViewById(R.id.send_sms);
        phone = findViewById(R.id.contact_phoneTV);
        name = findViewById(R.id.contact_nameTV);
        nick = findViewById(R.id.nick_nameTV);
        email = findViewById(R.id.emailTV);
        back_btn = findViewById(R.id.backIB);

        Intent get_info=getIntent(); //Previous attempt of getting the Bundle
        if (get_info!=null) {
            Bundle old_info = getIntent().getExtras();


            phone_num = old_info.getString("phoneNum");
            full_name = old_info.getString("fullName");
            nick_name = old_info.getString("nickName");
            email_add = old_info.getString("email");

            phone.setText(phone_num);
            name.setText(full_name);
            nick.setText(nick_name);
            email.setText(email_add);
        }
        else {
            phone.setText("12345678");
            name.setText("hello");
            nick.setText("bad info");
            email.setText("bad@info.cin");
            phone_num = "12345678";
        }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertTwoButtons();
            }
        });
        back_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                event.getAction();
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    back_btn.setImageResource(R.drawable.backpress);
                }
                else{
                    back_btn.setImageResource(R.drawable.backnopress);
                    finish();
                }
                return false;
            }
        });

           /*pic_btn.setOnClickListener(new View.OnClickListener() {
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
        });*/

        date_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    date_btn.setImageResource(R.drawable.datepressed);
                    Toast.makeText(ContactActivity.this,"Schedule Events with your Friends!", Toast.LENGTH_SHORT).show();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    date_btn.setImageResource(R.drawable.datenpressed);
                    Intent intent=new Intent(ContactActivity.this,DateActivity.class);
                    startActivity(intent);

                }
                return  false;
            }

        });

        call_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    call_btn.setImageResource(R.drawable.phonepressed);
                    Toast.makeText(ContactActivity.this,"Call your Friend!", Toast.LENGTH_SHORT).show();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    call_btn.setImageResource(R.drawable.phonenpress);
                    if(Build.VERSION.SDK_INT>=23){
                        int hasCameraPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                        if (hasCameraPermission==PackageManager.PERMISSION_GRANTED){
                            phoneCall();
                        }
                        else {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_REQUEST);
                        }
                    }
                    else{
                        phoneCall();
                    }
                }
                return  false;
            }

        });

           sms_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                event.getAction();
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    sms_btn.setImageResource(R.drawable.smspress);
                }
                else{
                    sms_btn.setImageResource(R.drawable.smsnopress);
                    Intent intent=new Intent(ContactActivity.this,SendSMSActivity.class);
                    intent.putExtra("phoneNum",phone_num);
                    startActivity(intent);
                }
                return false;
            }
        });

    }
    private void startCamera(){
        Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    private void phoneCall(){
        String num=phone.getText().toString();
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+num));
        startActivity(intent);
    }

    public void alertTwoButtons() {
        new AlertDialog.Builder(ContactActivity.this)
                .setTitle(R.string.camera_gallery)
                .setPositiveButton(R.string.camera,
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {

                                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(takePicture, 0);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton(R.string.gallery, new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto,1);
                        dialog.cancel();
                    }
                }).show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    /*Uri selectedImage = imageReturnedIntent.getData();
                    profile.setImageURI(selectedImage);*/
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    profile.setImageBitmap(photo);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    profile.setImageURI(selectedImage);
                }
                break;
        }
    }

}


