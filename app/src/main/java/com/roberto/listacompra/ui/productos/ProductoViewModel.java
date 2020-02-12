package com.roberto.listacompra.ui.productos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProductoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el fragmento de productos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}