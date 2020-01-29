package com.example.bananaskin.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Do not PANIC! Press button to call the emergency helpline");
    }

    public LiveData<String> getText() {
        return mText;
    }
}