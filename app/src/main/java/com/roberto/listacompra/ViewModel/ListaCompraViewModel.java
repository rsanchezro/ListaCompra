package com.roberto.listacompra.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.roberto.listacompra.modelo.ListaCompraRespositorio;
import com.roberto.listacompra.modelo.Lista_Compra;
import com.roberto.listacompra.modelo.Producto;
import com.roberto.listacompra.modelo.Producto_has_Lista_Compra;
import com.roberto.listacompra.modelo.Tienda;

import java.util.ArrayList;
import java.util.List;

public class ListaCompraViewModel extends AndroidViewModel {
    //Objeto que haga referencia al repositorio
    //Objetos LiveData que hagan referencia a los
    //resultados de las consultas
    private ListaCompraRespositorio respositorio;
    private LiveData<List<Tienda>> tiendas;
    private LiveData<List<Producto>> productos;
    private LiveData<List<Lista_Compra>> listas_compra;





    //Constructor que recibe la Applicacion
    //Instancia el repositorio y los LiveData
    public ListaCompraViewModel(@NonNull Application application) {
        super(application);
        //Instanciamos objeto repositorio
        respositorio=new ListaCompraRespositorio(application);
        this.listas_compra=respositorio.obtener_listasdelacompra();
        this.productos=respositorio.obtener_listadeproductos();
        this.tiendas=respositorio.obtener_listatiendas();

    }


    public LiveData<List<Tienda>> getTiendas() {
        return tiendas;
    }

    public LiveData<List<Producto>> getProductos() {
        return productos;
    }

    public LiveData<List<Lista_Compra>> getListas_compra() {
        return listas_compra;
    }

    public LiveData<List<Producto_has_Lista_Compra>> getDetallelista_compra(int id) {
        return respositorio.obtener_detalle_listacompra(id);

    }

    public void insert_listacompra(Lista_Compra lista)
    {
        respositorio.insertarListaCompra(lista);
    }

    public void insertar_productos(List<Producto> pdtos)
    {
        respositorio.insertar_productos(pdtos);
    }


    public void insertar_tiendas(ArrayList<Tienda> lista_tiendas) {
        respositorio.insertar_tiendas(lista_tiendas);
    }
}
