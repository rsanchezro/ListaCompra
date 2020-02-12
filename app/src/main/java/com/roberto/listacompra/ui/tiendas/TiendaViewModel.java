package com.roberto.listacompra.ui.tiendas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TiendaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TiendaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el Fragmento de tiendas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}