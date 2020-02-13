package com.roberto.listacompra.modelo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TiendaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long nueva_tienda(Tienda t);

    //CONSULTAS
    @Query("Select * from Tienda")
    public LiveData<List<Tienda>> obtener_tiendas();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertar_tiendas(List<Tienda> list);
}
