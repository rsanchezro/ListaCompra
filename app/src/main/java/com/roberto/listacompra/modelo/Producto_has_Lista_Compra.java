package com.roberto.listacompra.modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"id_producto","id_lista_compra"},
foreignKeys = {@ForeignKey(entity = Producto.class,
                        parentColumns = {"id"},
                        childColumns = {"id_producto"},
                        onDelete =ForeignKey.CASCADE ),
               @ForeignKey(entity = Lista_Compra.class,
                           parentColumns = {"id"},
                           childColumns = {"id_lista_compra"}),
                @ForeignKey(entity = Tienda.class,
                            parentColumns = {"id"},
                            childColumns = {"id_Tienda"})})
public class Producto_has_Lista_Compra {
    private int id_producto;
    private int id_lista_compra;
    private int id_Tienda;
    private float precio_producto;
    private int unidades_compradas;
    private int unidades_listadas;

    public Producto_has_Lista_Compra(int id_producto, int id_lista_compra, int id_Tienda, float precio_producto, int unidades_compradas, int unidades_listadas) {
        id_producto = id_producto;
        id_lista_compra = id_lista_compra;
        id_Tienda = id_Tienda;
        this.precio_producto = precio_producto;
        this.unidades_compradas = unidades_compradas;
        this.unidades_listadas = unidades_listadas;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        id_producto = id_producto;
    }

    public float getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(float precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getUnidades_compradas() {
        return unidades_compradas;
    }

    public void setUnidades_compradas(int unidades_compradas) {
        this.unidades_compradas = unidades_compradas;
    }

    public int getUnidades_listadas() {
        return unidades_listadas;
    }

    public void setUnidades_listadas(int unidades_listadas) {
        this.unidades_listadas = unidades_listadas;
    }

    public int getId_lista_compra() {
        return id_lista_compra;
    }

    public void setId_lista_compra(int id_lista_compra) {
        this.id_lista_compra = id_lista_compra;
    }

    public int getId_Tienda() {
        return id_Tienda;
    }

    public void setId_Tienda(int id_Tienda) {
        this.id_Tienda = id_Tienda;
    }
}
