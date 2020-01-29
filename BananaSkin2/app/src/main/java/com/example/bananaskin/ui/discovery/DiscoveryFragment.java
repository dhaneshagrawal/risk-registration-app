package com.example.bananaskin.ui.discovery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bananaskin.R;

public class DiscoveryFragment extends Fragment {

    private DiscoveryViewModel discoveryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        discoveryViewModel =
                ViewModelProviders.of(this).get(DiscoveryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_discovery, container, false);
        final TextView textView = root.findViewById(R.id.text_discovery);
        discoveryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}