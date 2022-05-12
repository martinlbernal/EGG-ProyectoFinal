package com.example.demo.entidades;

import com.example.demo.entidades.enumeraciones.Tipo;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Vehiculo {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    private String identificador;
    private Boolean alta;
    
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    private Cliente cliente;
    
    public Vehiculo() {
    }

    public Vehiculo(String id, String identificador, Tipo tipo, Cliente cliente, Boolean alta) {
        this.id = id;
        this.identificador = identificador;
        this.tipo = tipo;
        this.cliente = cliente;
        this.alta = alta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", identificador=" + identificador + ", alta=" + alta + ", tipo=" + tipo + ", cliente=" + cliente + '}';
    }

}
