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

import java.util.ArrayList;

public class AdaptadorListaCompra extends RecyclerView.Adapter<AdaptadorListaCompra.Miholder> {
    private ArrayList<Llamada> lista_llamadas;
    private Context c;
    private int resource;
    public AdaptadorListaCompra(Context c, int r)
    {
        this.lista_llamadas=new ArrayList<Llamada>();
        this.c=c;
        this.resource=r;
    }

    public AdaptadorListaCompra(ArrayList<Llamada> llamadas, Context c, int r) {
        this.lista_llamadas=llamadas;
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
        if(lista_llamadas!=null)
        {
            return lista_llamadas.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull Miholder holder, int position) {
        holder.llamada=lista_llamadas.get(position);


        holder.fecha.setText(holder.llamada.getFecha());
        holder.numero.setText(holder.llamada.getNumero());


    }

    public void setLista_llamadas(ArrayList<Llamada> lista_llamadas) {
        this.lista_llamadas = lista_llamadas;
        notifyDataSetChanged();
    }

    public class Miholder extends RecyclerView.ViewHolder{
        TextView numero;
        TextView descripcion;

        TextView fecha;
        Llamada llamada;

        public Miholder(@NonNull View itemView) {
            super(itemView);
            numero=itemView.findViewById(R.id.textView_numero);
            fecha=itemView.findViewById(R.id.textView_fechalistacompra);
            descripcion=itemView.findViewById(R.id.textView_descripcionlista);

        }
    }
}