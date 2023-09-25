package com.irving.basepersonita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DataManager dataManager;
    private Button botonGuardar;
    private EditText textNombre;
    private ListView listaPersonitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = new DataManager(getApplicationContext());
        botonGuardar  = (Button) findViewById(R.id.botonGuardar);
        textNombre = (EditText) findViewById(R.id.editNombre);
        listaPersonitas = (ListView) findViewById(R.id.listaPersonitas);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = textNombre.getText().toString();
                if (!nombre.isEmpty()){
                    Personita fulanito = new Personita(nombre);
                    dataManager.guardarPersonita(fulanito);
                    Toast.makeText(getApplicationContext(), "Personita "+fulanito.toString()+"Guardada", Toast.LENGTH_LONG).show();
                    textNombre.setText("");
                    mostrarPersonitas();
                }else{
                    Toast.makeText(getApplicationContext(), "Debes ingresar Nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    protected void mostrarPersonitas(){
        try {
            ArrayList<Personita> personitas = dataManager.leerPersonitas();
            ArrayAdapter<Personita> adaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,personitas);
            listaPersonitas.setAdapter(adaptador);
            listaPersonitas.setVerticalScrollBarEnabled(true);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        dataManager.cerrar();
        super.onPause();
    }

    @Override
    protected void onResume(){
        dataManager.abrir();
        super.onResume();
    }
}