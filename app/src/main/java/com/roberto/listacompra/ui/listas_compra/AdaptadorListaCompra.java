package com.roberto.listacompra.ui.listas_compra;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.roberto.listacompra.R;
import com.roberto.listacompra.ViewModel.ListaCompraViewModel;
import com.roberto.listacompra.modelo.Lista_Compra;
import com.roberto.listacompra.modelo.Producto_has_Lista_Compra;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorListaCompra extends RecyclerView.Adapter<AdaptadorListaCompra.Miholder> {
    private ArrayList<Lista_Compra> listas_de_la_compra;
    private Context c;
    private static ListaCompraViewModel vmodel;
    private int resource;


    public AdaptadorListaCompra(List<Lista_Compra> listas_compra, Context c, int r, ListaCompraViewModel viewModel) {
        this.listas_de_la_compra=(ArrayList<Lista_Compra>)listas_compra;
        vmodel=viewModel;
        this.c=c;
        this.resource=r;

    }

    @NonNull
    @Override
    public Miholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista_elemento=((AppCompatActivity)c).getLayoutInflater().inflate(this.resource,parent,false);
        return new Miholder(vista_elemento);

    }

    @Override
    public int getItemCount() {
        if(listas_de_la_compra!=null)
        {
            return listas_de_la_compra.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull Miholder holder, int position) {
        int num_productos=0;
        float importe_total=0;
        holder.lista_compra=listas_de_la_compra.get(position);

//Paso la fecha del formato Date a String
        holder.fecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(holder.lista_compra.getFecha()));
        holder.numero.setText(holder.lista_compra.getId()+"");
        holder.descripcion.setText(holder.lista_compra.getDescripcion());

        //Para conocer el numero de productos de una lista de la compra necesito
        //usar el ViewModel
        ArrayList<Producto_has_Lista_Compra> lista_productos=(ArrayList<Producto_has_Lista_Compra>)vmodel.getDetallelista_compra(holder.lista_compra.getId()).getValue();
        if(lista_productos!=null)
        {

            num_productos=lista_productos.size();
            //Para conocer el importe total?, o bien a traves de una consulta o bien recorriendo este array
            for (Producto_has_Lista_Compra p:
                 lista_productos)
            {
                importe_total+=(p.getPrecio_producto()*p.getUnidades_compradas());
            }
        }
       holder.num_pdtos.setText(num_productos+"");
        holder.importe_total.setText(importe_total+"");




    }

    public void setLista_Compra(ArrayList<Lista_Compra> lista_compras) {
        if(lista_compras!=null) {
            this.listas_de_la_compra = lista_compras;
            notifyDataSetChanged();
        }
    }

    public class Miholder extends RecyclerView.ViewHolder{
        TextView numero;
        TextView descripcion;
        TextView fecha;
        TextView num_pdtos;
        TextView importe_total;
       Lista_Compra lista_compra;

        public Miholder(@NonNull View itemView) {
            super(itemView);
            numero=itemView.findViewById(R.id.numero_lista);
            fecha=itemView.findViewById(R.id.fecha_lista);
            descripcion=itemView.findViewById(R.id.descripcion_lista);
            num_pdtos=itemView.findViewById(R.id.num_pdtos_listcompra_txtview);
            importe_total=itemView.findViewById(R.id.importe_listacompra_txtview);

        }
    }
}