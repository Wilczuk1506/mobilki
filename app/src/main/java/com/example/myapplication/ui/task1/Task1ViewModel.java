package com.example.myapplication.ui.task1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task1ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Task1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Task1");
    }

    public LiveData<String> getText() {
        return mText;
    }
}