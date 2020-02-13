package com.roberto.listacompra.ui.listas_compra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roberto.listacompra.R;
import com.roberto.listacompra.ViewModel.ListaCompraViewModel;
import com.roberto.listacompra.modelo.Lista_Compra;

import java.util.ArrayList;
import java.util.List;

public class ListaCompraFragment extends Fragment {

    private ListaCompraViewModel listacompraViewModel;
    private AdaptadorListaCompra miadaptador;
    private RecyclerView mirecycler;
    public ListaCompraFragment()
    {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppCompatActivity ac=(AppCompatActivity)getActivity();
        ViewModelProvider v=new ViewModelProvider(getActivity());


        listacompraViewModel = v.get(ListaCompraViewModel.class);
        this.miadaptador=new AdaptadorListaCompra(listacompraViewModel.getListas_compra().getValue(),getActivity(),R.layout.elementolistacompra,listacompraViewModel);
        View root = inflater.inflate(R.layout.fragment_listacompra, container, false);
        mirecycler=root.findViewById(R.id.recicler_listacompra);
        mirecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mirecycler.setAdapter(miadaptador);


        listacompraViewModel.getListas_compra().observe(getActivity(), new Observer<List<Lista_Compra>>() {
            @Override
            public void onChanged(List<Lista_Compra> lista_compras) {

                miadaptador.setLista_Compra((ArrayList<Lista_Compra>)lista_compras);
            }
        });
        return root;
    }
}