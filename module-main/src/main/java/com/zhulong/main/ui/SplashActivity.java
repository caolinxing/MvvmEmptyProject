package com.zhulong.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zhulong.library_base.storage.MmkvHelper;
import com.zhulong.main.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              /*  if (MmkvHelper.getInstance().getMmkv().decodeBool("first",true)){
                    //startActivity(new Intent(this, GuideActivity.class));
                }else {
                    MainActivity.start(this);
                }*/
                MainActivity.start(SplashActivity.this);

            }
        },1000);
    }
}