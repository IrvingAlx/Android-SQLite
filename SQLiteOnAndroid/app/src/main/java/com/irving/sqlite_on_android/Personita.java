package com.irving.sqlite_on_android;

import androidx.annotation.NonNull;

public class Personita {
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String genero;
    private String fecha;

    public Personita(){
    }

    public Personita(String nombre, String apellidoP,String apellidoM, String genero, String fecha){
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.genero = genero;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellidoP() {
        return apellidoP;
    }
    public String getApellidoM() {
        return apellidoM;
    }
    public String getGenero() {
        return genero;
    }
    public String getFecha() {
        return fecha;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellidoP(String apellidoP){
        this.apellidoP = apellidoP;
    }
    public void setApellidoM(String apellidoM){
        this.apellidoM = apellidoM;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    @NonNull
    public String toString(){
        return nombre;
    }
}
