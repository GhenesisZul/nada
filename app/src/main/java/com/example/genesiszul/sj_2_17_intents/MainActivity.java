package com.example.genesiszul.sj_2_17_intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private ImageButton ibAbreActivity;
    private ImageButton ibEnviaDatos;
    private ImageButton ibDevuelveDatos;
    private ImageButton ibAbreMarcado;
    private ImageButton ibLlamar;
    private ImageButton ibAbrirGoogleMaps;
    private ImageButton ibAbrirPaginaWeb;
    private ImageButton ibCompartirImagen;
    private ImageButton ibEnviarMail;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibAbreActivity = (ImageButton) findViewById(R.id.ibAbreActivity);
        ibEnviaDatos = (ImageButton) findViewById(R.id.ibEnviaDatos);
        ibDevuelveDatos = (ImageButton) findViewById(R.id.ibDevueveDatos);
        ibAbreMarcado = (ImageButton) findViewById(R.id.ibAbrirMarcado);
        ibLlamar = (ImageButton) findViewById(R.id.ibLlamar);
        ibAbrirGoogleMaps = (ImageButton) findViewById(R.id.ibAbreGoogleMaps);
        ibAbrirPaginaWeb = (ImageButton) findViewById(R.id.ibAbrePaginaWeb);
        ibCompartirImagen = (ImageButton) findViewById(R.id.ibComparteImagen);
        ibEnviarMail = (ImageButton) findViewById(R.id.ibEnviaEmail);


        ibAbreActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity();
            }
        });
        ibEnviaDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDatos();
            }
        });

        ibDevuelveDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolverDatos();
            }
        });
        ibAbreMarcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMarcadoTelefonico();
            }
        });

        ibLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar();
            }




        });
        ibAbrirGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGoogleMaps();
            }
        });
        ibAbrirPaginaWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPaginaWeb();
            }
        });

        ibCompartirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartirImagen();
            }
        });
        ibEnviarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMail();
            }
        });


    }


    public void abrirActivity() {
        intent = new Intent(MainActivity.this, SegundaActivity.class);
        startActivity(intent);
    }

    public void enviarDatos() {
        intent = new Intent(
                getApplicationContext(),
                SegundaActivity.class
        );
        intent.putExtra("dato1", "Luz");
        intent.putExtra("dato2", "Genesis");
        startActivity(intent);
    }

    public void devolverDatos() {
        intent = new Intent(
                getApplicationContext(),
                SegundaActivity.class
        );
        intent.putExtra("mensaje", "Hola");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String resultado = data.getStringExtra("respuesta").toString();
                Toast.makeText(
                        getApplicationContext(),
                        "Respuesta: " + resultado,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Se cancelo la respuesta.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void abrirMarcadoTelefonico() {
        intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:61146624"));
        startActivity(intent);
    }

    public void llamar() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
       /* if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);
        } else {
            /*intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:61146624"));
            startActivity(intent);*/


            Intent i = new Intent(android.content.Intent.ACTION_CALL,
                    Uri.parse("tel:61146624"));
                    startActivity(i);

        //}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    llamar();
                } else {
                    Log.d("TAG", "Permiso de llamada no concedido");
                }
                break;
            default:
                break;
        }
    }

    public void abrirGoogleMaps() {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:-16.504014,-68.130906"));
        startActivity(intent);
    }

    public void abrirPaginaWeb() {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.gdg.androidbolivia.com"));
        startActivity(intent);
    }

    public void compartirImagen() {
       /* intent = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.parse(
                "android.resource://com.miramicodigo.sj_a_1_17_java_3_intents/" + R.drawable.googlemaps);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("entrar/png");
        startActivity(intent);*/

    }

    public void enviarMail() {
        String[] TO = {"lizarraga.gux@gmail.com"};
        String[] CC = {"androidlapaz@gmail.com"};
        String asunto = "Correo Importante";
        String contenido = "Este es una prueba de correo";

        intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, TO);
        intent.putExtra(Intent.EXTRA_CC, CC);
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT, contenido);

        startActivity(Intent.createChooser(intent, "Envia Correo"));
    }


}
