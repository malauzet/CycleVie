package com.example.cyclevie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    public static final String CYCLEVIEPREFS = "cycle_vie_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnQuitter = findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(btnQuitterOnClickListener);

        Button btnEnvoyer = findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(btnEnvoyerOnClickListener);

        Button btnAct2 = findViewById(R.id.btnAct2);
        btnAct2.setOnClickListener(btnAct2OnClickListener);

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
        SharedPreferences settings = getSharedPreferences("CYCLEVIEPREFS", Context.MODE_PRIVATE);
        setTxTValeur(settings.getString("valeur", ""));
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
        if (isFinishing()) {
            popUp("onPause, l'utilisateur à demandé la fermeture via un finish()");
        } else {
            popUp("onPause, l'utilisateur n'a pas demandé la fermeture via un finish()");
        }
        SharedPreferences settings = getSharedPreferences("CYCLEVIEPREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("valeur", getTxtValeur());
        editor.commit();
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
    //=================================================================
    View.OnClickListener btnQuitterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener btnEnvoyerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popUp("valeur saisie = " + getTxtValeur());
        }
    };

    View.OnClickListener btnAct2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), SecondActivity.class);
            intent.putExtra("save2", "Avec putExtra : " + getTxtValeur());
            intent.putExtra("save3", "Avec Bundle : " + getTxtValeur());
            startActivity(intent);
        }
    };

    public void popUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public String getTxtValeur() {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        return zoneValeur.getText().toString();
    }

    public void setTxTValeur(String valeur) {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        zoneValeur.setText(valeur);
    }
}