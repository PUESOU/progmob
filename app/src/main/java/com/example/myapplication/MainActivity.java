package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static String CYCLEVIEPREFS = "cycle_vie_prefs";
    TextView tv;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        String login = res.getString(R.string.login);

        tv = (TextView) findViewById(R.id.tvi);

        tv.setText(login);

        popUp("onCreate");

        settings = getSharedPreferences(CYCLEVIEPREFS, Context.MODE_PRIVATE);

        //

        Button btnQuitter = (Button) findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //

        Button btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp("valeur saisie = " + getTxtValeur());
            }
        });

        //

        Button btnAct2 = (Button) findViewById(R.id.btnAct2);
        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Main2Activity.class);

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("d0", "Avec SharedPreferences: " + getTxtValeur());
                editor.commit();

                intent.putExtra("d1", "Avec intent.putExtra: " + getTxtValeur());

                startActivity(intent);
            }
        });
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

        setTxtValeur(settings.getString("valeur", ""));
    }

    @Override
    protected void onResume() {
        super.onResume();
        popUp("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(isFinishing()) {
            popUp("onPause, l'utilisateur a demandé la fermeture via un finish()");
        } else {
            popUp("onPause, l'utilisateur n'a pas demandé la fermeture via un finish()");
        }

        //

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("valeur", getTxtValeur());
        editor.commit();
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public String getTxtValeur() {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        return zoneValeur.getText().toString();
    }

    public void setTxtValeur(String valeur) {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        zoneValeur.setText(valeur);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        popUp("onSaveInstanceState");
        outState.putString("d2", "savedInstanceState: " + getTxtValeur());
    }
}
