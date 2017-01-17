package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bCalcular = (Button) findViewById(R.id.bCalcular);

        final EditText etPeso = (EditText) findViewById(R.id.etPeso);
        final EditText etAltura = (EditText) findViewById(R.id.etAltura);
        final TextView tvParametrosIncorrectos = (TextView) findViewById(R.id.tvParametrosIncorrectos);

        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String peso = etPeso.getText().toString();
                String altura = etAltura.getText().toString();
                float valorPeso;
                float valorAltura;

                if (peso.length() > 0 && altura.length() > 0) {

                    try {
                        valorPeso = Float.parseFloat(peso);
                        valorAltura = Float.parseFloat(altura) / 100;

                        if (valorPeso > 0 && valorPeso < 200 && valorAltura > 0 && valorAltura < 2.50) {

                            Intent intent = new Intent((Activity) MainActivity.this, Resultados.class);
                            intent.putExtra("PESO", peso);
                            intent.putExtra("ALTURA", altura);
                            startActivity(intent);
                        }

                    } catch (NumberFormatException nfe) {
                        tvParametrosIncorrectos.setVisibility(View.VISIBLE);
                    }
                }
            } //Fin onClick


        });

        etAltura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) v).setText("");
                tvParametrosIncorrectos.setVisibility(View.INVISIBLE);
            }
        });

        etPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) v).setText("");
                tvParametrosIncorrectos.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
