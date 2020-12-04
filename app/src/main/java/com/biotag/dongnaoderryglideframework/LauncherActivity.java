package com.biotag.dongnaoderryglideframework;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(v->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }
}