package com.shovon.project10;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Shovon on 3/5/15.
 */
public class PointEntry extends DialogFragment {
    PointEntryDialog mListener;
    int[] pA = new int[3];
    int[] pB = new int[3];
    int[] pC = new int[3];
    int[] pD1 = new int[3];
    int[] pD2 = new int[3];


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (PointEntryDialog) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement NoticeDialogListener");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.pointentry, null);
        builder.setView(view);
        final EditText etA = (EditText) view.findViewById(R.id.etPA);
        final EditText etB = (EditText) view.findViewById(R.id.etPB);
        final EditText etC = (EditText) view.findViewById(R.id.etPC);
        final EditText etD1 = (EditText) view.findViewById(R.id.etPD1);
        final EditText etD2 = (EditText) view.findViewById(R.id.etPD2);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String[] a = etA.getText().toString().split(",");
                String[] b = etB.getText().toString().split(",");
                String[] c = etC.getText().toString().split(",");
                String[] d1 = etD1.getText().toString().split(",");
                String[] d2 = etD2.getText().toString().split(",");


                for (int i = 0; i < 3; i++) {
                    pA[i] = Integer.parseInt(a[i]);
                    pB[i] = Integer.parseInt(b[i]);
                    pC[i] = Integer.parseInt(c[i]);
                    pD1[i] = Integer.parseInt(d1[i]);
                    pD2[i] = Integer.parseInt(d2[i]);
                }

                mListener.onDialogPositiveClick(pA, pB, pC, pD1, pD2);

            }
        });
        return builder.create();
    }

    public interface PointEntryDialog {
        public void onDialogPositiveClick(int[] pA, int[] pB, int[] pC, int[] pD1, int[] pD2);
    }
}

