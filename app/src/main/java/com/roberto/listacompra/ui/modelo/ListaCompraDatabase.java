package com.roberto.listacompra.ui.modelo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Producto.class,Tienda.class,
                      Lista_Compra.class,
                      Producto_has_Lista_Compra.class},
            version = 1,
            exportSchema = false)
public abstract class ListaCompraDatabase
         extends RoomDatabase {
    private static ListaCompraDatabase INSTANCIA;


    public abstract TiendaDAO getTiendaDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract Lista_CompraDAO getListaCompraDAO();
    public abstract Producto_has_Lista_CompraDAO getProductolistacompraDAO();

    static synchronized ListaCompraDatabase
       getDatabase(final Context micontexto)
    {
        if(INSTANCIA==null)
        {
            //Instancio el objeto una sola vez
            INSTANCIA=
                    Room.databaseBuilder(micontexto.getApplicationContext(),
                        ListaCompraDatabase.class,"ListaCompra")
                    .build();

        }
        return INSTANCIA;
    }
}
