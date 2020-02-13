package com.roberto.listacompra.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Lista_Compra {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date fecha;
    private String descripcion;

    public Lista_Compra(Date fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
