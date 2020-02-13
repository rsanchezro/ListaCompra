package com.roberto.listacompra.ui.modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"Id_producto","Id_lista_compra"},
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
}
