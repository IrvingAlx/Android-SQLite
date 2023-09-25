package com.irving.basepersonita;

public class Personita {
    private String nombre;

    public Personita(){
    }

    public Personita(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String toString(){
        return nombre;
    }
}
