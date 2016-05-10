package com.aprendeandroid.sqlitealbum;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mortizer on 10/5/16.
 */
public class NewNameFragment extends Fragment{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("NMFrag","Entra onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View viewFrag = inflater.inflate(R.layout.fragment_new_name,(ViewGroup)getActivity().findViewById(R.id.root));

        return viewFrag;
    }
}
