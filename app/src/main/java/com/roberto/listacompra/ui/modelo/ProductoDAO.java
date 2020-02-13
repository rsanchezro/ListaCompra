package com.roberto.listacompra.ui.modelo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long nuevo_producto(Producto p);

    @Insert
    public void nuevos_productos(List<Producto> productos);

    //CONSULTAS
    @Query("Select * From Producto")
    public LiveData<List<Producto>> obtener_productos();

}
