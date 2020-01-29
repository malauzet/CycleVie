package com.example.cyclevie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity
{
    TextView textv;
    String save, save2, save3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // a) Lines 23-24 Not working at the moment
        SharedPreferences settings = getSharedPreferences(MainActivity.CYCLEVIEPREFS, Context.MODE_PRIVATE);
        save = settings.getString("valeur", "");

        // b) Lines 27-31 + Line 107 MainActivity
        Intent intent = getIntent();
        save2 = "";
        if(intent != null) {
            save2 = intent.getStringExtra("save2");
        }

        // c) Line 35-36 + Line 108 MainActivity
        // Code in question c) didn't work for me so I used what I found with Bundles on the OpenClassroom Forum
        Bundle data = this.getIntent().getExtras();
        save3 = data.getString("save3");

        textv = (TextView) findViewById(R.id.textView1);
        textv.setText(save + "\n" + save2 + "\n" + save3);

        popUp("onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        popUp("onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences settings = getSharedPreferences(MainActivity.CYCLEVIEPREFS, Context.MODE_PRIVATE);
        save = settings.getString("valeur", "");
        popUp("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        popUp("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        popUp("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        popUp("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popUp("onDestroy()");
    }

    public void popUp(String message) {
        Toast.makeText(this, "Activité 2 : " + message, Toast.LENGTH_LONG).show();
    }
}
