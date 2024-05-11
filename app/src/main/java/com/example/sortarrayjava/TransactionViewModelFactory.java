package com.example.sortarrayjava;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TransactionViewModelFactory implements ViewModelProvider.Factory {

    private final Resources resources;

    public TransactionViewModelFactory(Resources resources) {
        this.resources = resources;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TransactionViewModel.class)) {
            return (T) new TransactionViewModel(resources);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

