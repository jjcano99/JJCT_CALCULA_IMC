package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.graphics.Color;

import android.os.Bundle;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;


/**
 * Created by juanjosecanotena on 2/1/17.
 */

public class Resultados extends Activity {


    String peso;
    String altura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(getClass().getCanonicalName(),"INICIO DE ONCREATE" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        List<Indice> indices = new ArrayList<Indice>();

        indices.add(new Indice(16, getString(R.string.desnutricion), Color.RED));
        indices.add(new Indice(18, getString(R.string.bajoPeso), Color.YELLOW));
        indices.add(new Indice(25, getString(R.string.normal), Color.GREEN));
        indices.add(new Indice(31, getString(R.string.sobrepeso), Color.YELLOW));
        indices.add(new Indice(99, getString(R.string.obesidad), Color.RED));

        float valorPeso;
        float valorAltura;
        int imc;

        TextView tvResultIMC = (TextView) findViewById(R.id.tvResultIMC);
        TextView tvEstadoIMC = (TextView) findViewById(R.id.tvEstadoIMC);

        if (savedInstanceState == null) {

                Bundle extras = getIntent().getExtras();
                peso = extras.getString("PESO");
                altura = extras.getString("ALTURA");

        } else {

            peso = (String) savedInstanceState.get("PESO");
            altura = (String) savedInstanceState.get("ALTURA");
        }

        valorPeso = Float.parseFloat(peso);
        valorAltura = Float.parseFloat(altura)/100;
        imc = (int) (valorPeso / (valorAltura * valorAltura));

        Log.d(getClass().getCanonicalName(),"ANTES DEL BUCLE" );

        for(Indice indice : indices) {
            if(imc < indice.limite) {

                Log.d(getClass().getCanonicalName(),"Límite: " + Integer.toString(indice.limite));

                tvResultIMC.setText(Integer.toString(imc));
                tvEstadoIMC.setText(indice.literal);
                tvEstadoIMC.setTextColor(indice.color);
                break;
            }
            }
        }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        savedInstanceState.putCharSequence("PESO",peso);
        savedInstanceState.putCharSequence("ALTURA",altura);

    }

    }
