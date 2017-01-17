package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by juanjosecanotena on 2/1/17.
 */

public class Resultados extends Activity {


    static String peso;
    static String altura;
    static ArrayList<Indice> indices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(getClass().getCanonicalName(), "INICIO DE ONCREATE");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resultados);

        indices = new ArrayList<Indice>();
        indices.add(new Indice(16, getString(R.string.desnutricion), Color.RED,R.drawable.desnutricion));
        indices.add(new Indice(18, getString(R.string.bajoPeso), Color.BLUE,R.drawable.bajo_peso));
        indices.add(new Indice(25, getString(R.string.normal), Color.GREEN,R.drawable.normalidad));
        indices.add(new Indice(31, getString(R.string.sobrepeso), Color.DKGRAY,R.drawable.sobrepeso));
        indices.add(new Indice(99, getString(R.string.obesidad), Color.RED, R.drawable.obesidad));

        float valorPeso;
        float valorAltura;
        int imc;

        TextView tvResultIMC = (TextView) findViewById(R.id.tvResultIMC);
        TextView tvEstadoIMC = (TextView) findViewById(R.id.tvEstadoIMC);
        Button bVerRangos = (Button) findViewById(R.id.bVerRangos);
        Button bHome = (Button) findViewById(R.id.bHome);

        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            peso = extras.getString("PESO");
            altura = extras.getString("ALTURA");

        } else {

            peso = (String) savedInstanceState.get("PESO");
            altura = (String) savedInstanceState.get("ALTURA");
        }

        valorPeso = Float.parseFloat(peso);
        valorAltura = Float.parseFloat(altura) / 100;
        imc = (int) (valorPeso / (valorAltura * valorAltura));

        Log.d(getClass().getCanonicalName(), "ANTES DEL BUCLE");


        for (Indice indice : indices) {
            if (imc < indice.limite) {

                Log.d(getClass().getCanonicalName(), "Límite: " + Integer.toString(indice.limite));

                tvResultIMC.setText(Integer.toString(imc));
                tvEstadoIMC.setText(indice.literal);
                tvEstadoIMC.setTextColor(indice.color);
                break;
            }
        } //Fin for

        bVerRangos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Resultados.this,Rangos.class);
                intent.putExtra("PESO", peso);
                intent.putExtra("ALTURA", altura);
                startActivity(intent);

            }
        });

        bHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Resultados.this,MainActivity.class);
               // intent.putExtra("PESO", peso);
               // intent.putExtra("ALTURA", altura);
                startActivity(intent);

            }
        });

    } //Fin OnCreate


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putCharSequence("PESO", peso);
        savedInstanceState.putCharSequence("ALTURA", altura);

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
