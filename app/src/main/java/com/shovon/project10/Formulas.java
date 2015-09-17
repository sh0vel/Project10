package com.shovon.project10;

import java.util.Arrays;

/**
 * Created by Shovon on 3/6/15.
 */
public class Formulas {

    public boolean collinearity(Vector vAB, Vector vAC) {
        int[] zeroVec = {0, 0, 0};
        System.err.println("norms vAB, vBC, vAC: " + Arrays.toString(vAB.crossProduct(vAC).getVector()));
        return (Arrays.equals(vAB.crossProduct(vAC).getVector(), zeroVec));
    }

    public boolean parallel(Vector dirc, Vector normPlane){
       if ( dirc.dotProduct(normPlane) == 0){
           return true;
       }
        return false;
    }

    public String planeEquation(Vector vAB, Vector vAC) {
        int[] perpenVec = normalVectorOfPlane(vAB, vAC).getVector();
        int[] initialPoint = vAB.getP1();
        int intValue = (perpenVec[0] * (initialPoint[0] * (-1))) + (perpenVec[1] * (initialPoint[1] * (-1))) +
                (perpenVec[2] * (initialPoint[2] * (-1)));

        return perpenVec[0] + "x + " + perpenVec[1] + "y + " + perpenVec[2] + "z + " + intValue + " = 0";

    }

    /*Uses the scalar triple product to determine if point D1 and D2 is on the ABC plane
    * The first parameter = A, second = B, and third = C
    * The STP method is called on A, which returns the dot product of A and (BxC)
    * The vectors are all on the same plane if STP returns 0. */
    public boolean isDonABC(Vector vAD, Vector vAB, Vector vAC) {
        System.err.println("isDonABC, STP = " + vAD.STP(vAB, vAC));
        return vAD.STP(vAB, vAC) == 0;
    }

    public boolean isD1D2OnSameSide(Vector vAD1, Vector vAD2, Vector vAB, Vector vAC) {
        System.err.println("isD1D2OnSameSide " + (vAD1.STP(vAB, vAC) * vAD2.STP(vAB, vAC)));
        return (vAD1.STP(vAB, vAC) * vAD2.STP(vAB, vAC)) > 0;
    }

    /*This method finds the direction vector of the line that passes the points D1
    * and D2. It will be used to find the angle where lD1D2 intersects the plane*/
    public Vector directionOfD1D2Line(int[] pD1, int[] pD2) {
        System.err.println("direct vec of line: " + Arrays.toString(new Vector(pD1, pD2).getVector()));
        return new Vector(pD1, pD2);
    }

    public Vector normalVectorOfPlane(Vector vAB, Vector vAC) {
        System.err.println("normal vec of plane: " + Arrays.toString(vAB.crossProduct(vAC).getVector()));
        return vAB.crossProduct(vAC);
    }

    /*This method applies the angle formula to find the angle between the plane and the
    * lD1D2. angle = arccos((dot product of n&d))/(||n||*||d||) */
    public double angleBetweenPlaneAndD1D2Line(Vector nvPlane, Vector dvD1D2) {
        double dotProduct = Math.abs(nvPlane.dotProduct(dvD1D2));
        System.err.println("DotProduct: " + dotProduct);
        double productOfNorms = nvPlane.getNorm() * dvD1D2.getNorm();
        System.err.println("product of norms: " + productOfNorms);
        System.err.println("After division: " + (dotProduct / productOfNorms));
        return Math.asin((dotProduct / productOfNorms)) * 180 / Math.PI;


    }
}



