package com.roberto.listacompra.ui.listas_compra;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaCompraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListaCompraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el fragmento de las Listas de la Compra");
    }

    public LiveData<String> getText() {
        return mText;
    }
}