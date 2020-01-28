package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    SharedPreferences settings;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        popUp("onCreate");

        settings = getSharedPreferences(MainActivity.CYCLEVIEPREFS, Context.MODE_PRIVATE);
        String d0 = settings.getString("d0", "");

        Intent intent = getIntent();
        String d1 = "";
        if(intent != null) {
            d1 = intent.getStringExtra("d1");
        }

        String d2 = "";

        if(savedInstanceState != null) {
            d2 = savedInstanceState.getString("d2");
        }

        tv = (TextView) findViewById(R.id.textView1);
        tv.setText(d0 + '\n' + d1 + '\n' + d2);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        popUp("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        popUp("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        popUp("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        popUp("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        popUp("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popUp("onDestroy");
    }

    public void popUp(String message) {
        Toast.makeText(this, "Activity 2: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        popUp("onRestoreInstanceState");
        String d2 = savedInstanceState.getString("d2");
        popUp(d2);
    }
}
