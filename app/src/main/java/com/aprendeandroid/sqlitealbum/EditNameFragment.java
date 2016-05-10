package com.aprendeandroid.sqlitealbum;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EditNameFragment extends Fragment{

    //    private EditText mEditText;
    //EditNameDialogListener listener;

    public EditNameFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("ENF","Entra 01");
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View layout = inflater.inflate(R.layout.fragment_edit_name, (ViewGroup) getActivity().findViewById(R.id.root));

        return layout;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
//		try {
//			listener = (EditNameDialogListener) activity;
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString()
//					+ " must implement EditNameDialogListener in Activity");
//		}
	}

//    public static interface EditNameDialogListener {
//    	public void onFinishEditDialog(boolean result, String editedName);
//    	public String getNameToEdit();
//    }
}
