package com.roberto.listacompra.modelo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Lista_CompraDAO
{
    @Insert
    public long nueva_lista(Lista_Compra l);

    //CONSULTAS
    @Query("Select * from Lista_Compra")
    public LiveData<List<Lista_Compra>> obtener_listas_compra();

}
