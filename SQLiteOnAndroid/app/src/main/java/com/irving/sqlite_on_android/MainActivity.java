package com.irving.sqlite_on_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private DataManager dataManager;
    private Button botonGuardar;
    private EditText textNombre;
    private EditText textApellidoP;
    private EditText textApellidoM;
    private RadioButton masculino;
    private RadioButton femenino;
    private CalendarView fecha;
    private ListView listaPersonitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = new DataManager(getApplicationContext());
        botonGuardar  = (Button) findViewById(R.id.botonGuardar);
        textNombre = (EditText) findViewById(R.id.editNombre);
        textApellidoP = (EditText) findViewById(R.id.editApellidoP);
        textApellidoM = (EditText) findViewById(R.id.editApellidoM);
        masculino = (RadioButton) findViewById(R.id.Masculino);
        femenino = (RadioButton) findViewById(R.id.Femenino);
        fecha = (CalendarView) findViewById(R.id.FechaEdad);
        listaPersonitas = (ListView) findViewById(R.id.listaPersonitas);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = textNombre.getText().toString();
                String apellidoP = textApellidoP.getText().toString();
                String apellidoM = textApellidoM.getText().toString();
                String genero = null;
                String curDate = String.valueOf(fecha.getDateTextAppearance());

                if (masculino.isChecked()){
                    genero = "Masculino";
                } else if (femenino.isChecked()) {
                    genero = "Femenino";
                } else {
                    Toast.makeText(getApplicationContext(), "Porfavor seleccione su genero", Toast.LENGTH_LONG).show();
                }

                if (!nombre.isEmpty() || !apellidoM.isEmpty() || !apellidoP.isEmpty() || genero.isEmpty() || curDate.isEmpty()){
                    Personita fulanito = new Personita(nombre,apellidoP,apellidoM,genero,curDate);
                    dataManager.guardarPersonita(fulanito);
                    Toast.makeText(getApplicationContext(), "Personita "+fulanito.toString()+"Guardada", Toast.LENGTH_LONG).show();
                    textNombre.setText("");
                    textApellidoP.setText("");
                    textApellidoM.setText("");
                    mostrarPersonitas();
                }else{
                    Toast.makeText(getApplicationContext(), "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
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