package com.roberto.listacompra.ui.listas_compra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.roberto.listacompra.R;

public class ListaCompraFragment extends Fragment {

    private ListaCompraViewModelbis listacompraViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listacompraViewModel =
                ViewModelProviders.of(this).get(ListaCompraViewModelbis.class);
        View root = inflater.inflate(R.layout.fragment_listacompra, container, false);

        listacompraViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}