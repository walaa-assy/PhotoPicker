package com.example.administrator.photopicker;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterTextDialogFragment extends DialogFragment {
    public EnterTextDialogFragment() {
    }


    public interface EnterTextDialogListener {
        public void onDialogPositiveClick(String text);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    EnterTextDialogListener mListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (EnterTextDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement EnterTextDialogListener");
        }
    }



    EditText editText;
    String userInput;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.comment_dialog, null);
        editText = (EditText) rootView.findViewById(R.id.inputText);
        userInput = editText.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(rootView)
               // .setTitle("Enter Your Text")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(userInput);
                        //Toast.makeText(getActivity(), userInput , Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //EnterTextDialogFragment.this.getDialog().cancel();
//                        try {
//                            finalize();
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//                        }
                        mListener.onDialogNegativeClick(EnterTextDialogFragment.this);
                    }
                });
        return builder.create();
    }



}
