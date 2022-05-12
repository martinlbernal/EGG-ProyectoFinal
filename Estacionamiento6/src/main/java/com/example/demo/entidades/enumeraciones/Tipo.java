package com.example.demo.entidades.enumeraciones;

public enum Tipo {
    AUTO("Auto", 1),
    MOTO("Moto", 1/2),
    BICICLETA("Bicicleta", 1/15),
    CAMIONETA("Camioneta", 2);

    private String nombre;
    
//    Relación respecto al auto.
    private Integer relacion;

    private Tipo(String nombre, Integer precio) {
        this.nombre = nombre;
        this.relacion = precio;
    }
    
    public String getNombre() {
        return nombre;
    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }

    public Integer getPrecio() {
        return relacion;
    }

//    public void setPrecio(Integer precio) {
//        this.precio = precio;
//    }
}
