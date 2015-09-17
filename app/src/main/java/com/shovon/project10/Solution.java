package com.shovon.project10;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Shovon on 3/9/15.
 */

public class Solution extends ActionBarActivity implements PointEntry.PointEntryDialog {
    int[] pA, pB, pC, pD1, pD2;
    double angle;
    Vector vAB, vBC, vAC, vAD1, vAD2, vD1D2;
    boolean collinear, d1OnABC, d2OnABC, d1d2OnSameSide, parallel;
    String planeEquation;
    Formulas f = new Formulas();
    Bundle args;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayfragholder);
        PointEntry pointEntryDialog = new PointEntry();
        pointEntryDialog.show(getFragmentManager(), "pointentry");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_newPoints) {
            PointEntry pointEntryDialog = new PointEntry();
            pointEntryDialog.show(getFragmentManager(), "pointentry");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(int[] pA, int[] pB, int[] pC, int[] pD1, int[] pD2) {
            this.pA = pA.clone();
            this.pB = pB.clone();
            this.pC = pC.clone();
            this.pD1 = pD1.clone();
            this.pD2 = pD2.clone();
        solve();

    }


    void solve() {
        vAB = new Vector(pA, pB);
        vBC = new Vector(pB, pC);
        vAC = new Vector(pA, pC);
        vAD1 = new Vector(pA, pD1);
        vAD2 = new Vector(pA, pD2);
        vD1D2 = new Vector(pD1,pD2);

        System.err.println("Vector ab: " + java.util.Arrays.toString(vAB.getVector()));
        System.err.println("Vector bc: " + java.util.Arrays.toString(vBC.getVector()));
        System.err.println("Vector ac: " + java.util.Arrays.toString(vAC.getVector()));
        System.err.println("Vector ad1: " + java.util.Arrays.toString(vAD1.getVector()));
        System.err.println("Vector ad2: " + java.util.Arrays.toString(vAD2.getVector()));
        collinear = f.collinearity(vAB, vAC);
        if (!collinear) {
            planeEquation = f.planeEquation(vAB, vAC);
        }
        d1OnABC = f.isDonABC(vAD1, vAB, vAC);
        d2OnABC = f.isDonABC(vAD2, vAB, vAC);
        d1d2OnSameSide = f.isD1D2OnSameSide(vAD1, vAD2, vAB, vAC);
        parallel = f.parallel( f.directionOfD1D2Line(pD1,pD2), f.normalVectorOfPlane(vAB,vAC));
        angle = f.angleBetweenPlaneAndD1D2Line(f.normalVectorOfPlane(vAB,vAC), f.directionOfD1D2Line(pD1,pD2));
        System.err.println("ANGLE: " + angle);
        sendToDisplay(fm);
    }

    void sendToDisplay(FragmentManager fm) {
        args = new Bundle();
        args.putIntArray("pA", pA);
        args.putIntArray("pB", pB);
        args.putIntArray("pC", pC);
        args.putIntArray("pD1", pD1);
        args.putIntArray("pD2", pD2);
        args.putString("plane", planeEquation);
        args.putBoolean("collinear", collinear);
        args.putBoolean("d1", d1OnABC);
        args.putBoolean("d2", d2OnABC);
        args.putBoolean("sameside", d1d2OnSameSide);
        args.putDouble("angle", angle);
        args.putBoolean("parallel", parallel);
        ft = fm.beginTransaction();
        ft.replace(R.id.solutiondisplay, DisplaySolution.newInstance(args));
        ft.commit();
    }


}
