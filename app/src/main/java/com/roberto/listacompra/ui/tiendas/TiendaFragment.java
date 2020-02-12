package com.roberto.listacompra.ui.tiendas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.roberto.listacompra.R;

public class TiendaFragment extends Fragment {

    private TiendaViewModel tiendaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tiendaViewModel =
                ViewModelProviders.of(this).get(TiendaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tiendas, container, false);
        final TextView textView = root.findViewById(R.id.text_tienda);
        tiendaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}