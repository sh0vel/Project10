package com.shovon.project10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shovon on 3/5/15.
 */
public class DisplaySolution extends Fragment {


    private static int[] pA, pB, pC, pD1, pD2;
    private TextView tvPA, tvPB, tvPC, tvPD1, tvPD2, tvAns1, tvAns2, tvAns3, tvAns4, tvAns5;
    private boolean collinear, d1OnABC, d2OnABC, d1d2OnSameSide;
    boolean parallel = false;
    private String planeEquation;
    private double angle;


    public static DisplaySolution newInstance(Bundle args) {
        DisplaySolution ds = new DisplaySolution();
        Bundle nargs = new Bundle();
        nargs.putBundle("args", args);
        ds.setArguments(nargs);
        return ds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.displaysolution, container, false);
        initializeView(v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle answers = getArguments().getBundle("args");
        assignVars(answers);
        displayPoints();

        if (collinear) {
            tvAns1.setText("Points A, B, and C are collinear");
            tvAns2.setText("No unique plane");
        } else {
            tvAns1.setText("Points A, B, and C are not collinear");
            tvAns2.setText("Equation of the plane:");
            tvAns3.setText(planeEquation);

            if (!d1OnABC && !d2OnABC) {
                tvAns4.setText("D1 and D2 are not on ABC plane");
            } else if (d1OnABC && d2OnABC) {
                tvAns4.setText("Points D1 and D2 are on the ABC plane");
                parallel = true;
            } else if (!d1OnABC && d2OnABC) {
                tvAns4.setText("Point D2 is on the ABC plane, point D1 is not");
            } else if (d1OnABC && !d2OnABC) {
                tvAns4.setText("Point D1 is on the ABC plane, point D2 is not");
            }


            if (parallel) {
                tvAns5.setText("No intersection, line is parallel to the plane");
            } else {
                tvAns5.setText("Line D1D2 intersects ABC plane at a " + Math.round(angle) + "\u00B0 angle");
            }

        }


    }


    private void initializeView(View v) {
        tvPA = (TextView) v.findViewById(R.id.tvPA);
        tvPB = (TextView) v.findViewById(R.id.tvPB);
        tvPC = (TextView) v.findViewById(R.id.tvPC);
        tvPD1 = (TextView) v.findViewById(R.id.tvPD1);
        tvPD2 = (TextView) v.findViewById(R.id.tvPD2);
        tvAns1 = (TextView) v.findViewById(R.id.tvAns1);
        tvAns2 = (TextView) v.findViewById(R.id.tvAns2);
        tvAns3 = (TextView) v.findViewById(R.id.tvAns3);
        tvAns4 = (TextView) v.findViewById(R.id.tvAns4);
        tvAns5 = (TextView) v.findViewById(R.id.tvAns5);

    }

    private void assignVars(Bundle b) {
        pA = b.getIntArray("pA");
        pB = b.getIntArray("pB");
        pC = b.getIntArray("pC");
        pD1 = b.getIntArray("pD1");
        pD2 = b.getIntArray("pD2");
        planeEquation = b.getString("plane");
        collinear = b.getBoolean("collinear");
        d1OnABC = b.getBoolean("d1");
        d2OnABC = b.getBoolean("d2");
        d1d2OnSameSide = b.getBoolean("sameside");
        angle = b.getDouble("angle");
        parallel = b.getBoolean("parallel");


    }

    private void displayPoints() {
        tvPA.setText(Integer.toString(pA[0]) + "," + Integer.toString(pA[1]) + "," + Integer.toString(pA[2]));
        tvPB.setText(Integer.toString(pB[0]) + "," + Integer.toString(pB[1]) + "," + Integer.toString(pB[2]));
        tvPC.setText(Integer.toString(pC[0]) + "," + Integer.toString(pC[1]) + "," + Integer.toString(pC[2]));
        tvPD1.setText(Integer.toString(pD1[0]) + "," + Integer.toString(pD1[1]) + "," + Integer.toString(pD1[2]));
        tvPD2.setText(Integer.toString(pD2[0]) + "," + Integer.toString(pD2[1]) + "," + Integer.toString(pD2[2]));
    }

}
