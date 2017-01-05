package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
