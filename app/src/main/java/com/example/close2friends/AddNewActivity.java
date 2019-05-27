package com.example.close2friends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddNewActivity extends AppCompatActivity {

    ImageButton ok,not_ok;
    EditText fullName,nickName,email,phoneNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        ok=findViewById(R.id.okIB);
        not_ok=findViewById(R.id.notokIB);

        fullName=findViewById(R.id.fullnameET);
        nickName=findViewById(R.id.nicknameET);
        email=findViewById(R.id.emailET);
        phoneNum=findViewById(R.id.phoneET);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name ,nick, emaill, phone;
                ok.setImageResource(R.drawable.okpress);
                name=fullName.getText().toString();
                nick =nickName.getText().toString();
                emaill =email.getText().toString();
                phone=phoneNum.getText().toString();
                Intent moverIntent=new Intent(AddNewActivity.this,ContactActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("fullName", name);
                bundle.putString("nickName", nick);
                bundle.putString("email", emaill);
                bundle.putString("phoneNum", phone);
                moverIntent.putExtras(bundle);

                startActivity(moverIntent);

            }
        });

/*        ok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    ok.setImageResource(R.drawable.okpress);
                    Intent moverIntent=new Intent(AddNewActivity.this,ContactActivity.class);
                    moverIntent.putExtra("fullName",name);
                    moverIntent.putExtra("nickName",nick);
                    moverIntent.putExtra("email",emaill);
                    moverIntent.putExtra("phoneNum",phone);
                    startActivity(moverIntent);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ok.setImageResource(R.drawable.oknopress);
                    Toast.makeText(AddNewActivity.this,"Saved!", Toast.LENGTH_SHORT).show();

                }
                return true;
            }

        });*/

        not_ok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                event.getAction();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    not_ok.setImageResource(R.drawable.notpress);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    not_ok.setImageResource(R.drawable.notnopress);
                    Toast.makeText(AddNewActivity.this,"Discarded!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return  true;
            }

        });



    }
}
