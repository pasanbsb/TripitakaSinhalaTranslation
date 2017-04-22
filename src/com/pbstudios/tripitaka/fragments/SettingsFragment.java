package com.pbstudios.tripitaka.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pbstudios.tripitaka.R;

/**
 * Created by Pasan on 2/16/2016.
 */
public class SettingsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        return rootView;
    }
}
