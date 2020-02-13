package com.roberto.listacompra.ui.modelo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ListaCompraRespositorio
{
    private Lista_CompraDAO lista_compraDAO;
    private ProductoDAO productoDAO;
    private TiendaDAO tiendaDAO;
    private Producto_has_Lista_CompraDAO producto_has_lista_compraDAO;

    private LiveData<List<Tienda>> listatiendas;
    private LiveData<List<Producto>> listaproductos;
    private LiveData<List<Lista_Compra>> listacompras;



    public ListaCompraRespositorio(Application app)
    {
        ListaCompraDatabase ldb=ListaCompraDatabase.getDatabase(app);
        this.lista_compraDAO=ldb.getListaCompraDAO();
        this.producto_has_lista_compraDAO=ldb.getProductolistacompraDAO();

        this.tiendaDAO=ldb.getTiendaDAO();
        this.productoDAO=ldb.getProductoDAO();


        this.listacompras=lista_compraDAO.obtener_listas_compra();
        this.listaproductos=productoDAO.obtener_productos();
        this.listatiendas=tiendaDAO.obtener_tiendas();


    }

    public void insertarListaCompra(Lista_Compra l)
    {
     new AsyncTask<Lista_Compra,Void,Void>()
        {

            @Override
            protected Void doInBackground(Lista_Compra... lista_compras) {
                lista_compraDAO.nueva_lista(lista_compras[0]);
                return null;
            }
        }.execute(l);
    }

    public void insertarDetalleListaCompra(Producto_has_Lista_Compra detalle)
    {

        new AsyncTask<Producto_has_Lista_Compra, Void, Void>() {
            @Override
            protected Void doInBackground(Producto_has_Lista_Compra... detalle) {
                producto_has_lista_compraDAO.
                        insertarproducto_en_lista(detalle[0]);
                return null;
            }
        }.execute(detalle);

    }

    public void actualizarDetalleListaCompra(Producto_has_Lista_Compra detalle)
    {
        new AsyncTask<Producto_has_Lista_Compra, Void, Void>() {
            @Override
            protected Void doInBackground(Producto_has_Lista_Compra... detalle) {
                producto_has_lista_compraDAO.
                       actualizarproducto_en_lista(detalle[0]);
                return null;
            }
        }.execute(detalle);

    }

    public LiveData<List<Lista_Compra>> obtener_listasdelacompra()
    {
        return this.listacompras;

    }

    public LiveData<List<Producto>> obtener_listadeproductos()
    {
        return this.listaproductos;
    }

    public LiveData<List<Tienda>> obtener_listatiendas()
    {
        return this.listatiendas;
    }

    public LiveData<List<Producto_has_Lista_Compra>>
    obtener_detalle_listacompra(int idlistacompra)
    {
        return this.producto_has_lista_compraDAO.obtenerdatos_listacompra(idlistacompra);
    }

}
