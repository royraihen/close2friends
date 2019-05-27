package com.example.close2friends;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SendSMSActivity extends AppCompatActivity {

    ImageButton ok,not_ok;
    EditText sms_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);

        ok=findViewById(R.id.okIB);
        not_ok=findViewById(R.id.notokIB);
        sms_content=findViewById(R.id.smsET);


        ok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    ok.setImageResource(R.drawable.okpress);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ok.setImageResource(R.drawable.oknopress);
                    Toast.makeText(SendSMSActivity.this,"Just Press Send!", Toast.LENGTH_SHORT).show();
                    smsSend();
                }
                return  false;
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
                    Toast.makeText(SendSMSActivity.this,"Discarded!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return  true;
            }

        });



    }
        private void smsSend(){
            String content=sms_content.getText().toString();
            Intent Phoneintent = getIntent();
            String phoneNum = Phoneintent.getStringExtra("phoneNum");

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNum));
            intent.putExtra("sms_body", content);
            startActivity(intent);

    }
}
