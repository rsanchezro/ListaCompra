package com.roberto.listacompra.ui.modelo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Producto_has_Lista_CompraDAO {

    @Insert
    public long insertarproducto_en_lista
               (Producto_has_Lista_Compra pl);

    @Update
    public int actualizarproducto_en_lista
              (Producto_has_Lista_Compra pl);


    //CONSULTA
    @Query("Select * from Producto_has_Lista_Compra where" +
            " id_lista_compra= :id_l_compra")
    public LiveData<List<Producto_has_Lista_Compra>>
             obtenerdatos_listacompra(int id_l_compra);
}
