package com.shovon.project10;

/**
 * Created by Shovon on 3/6/15.
 */
public class Vector {


    int[] vector = new int[3];
    double norm;
    int[] p1;
    int[] p2;


/*This class will be used to make the points into vectors. The constructor creates the new vector and also calculates its norm
* There are two constructers, one creates a vector between points, the other creates a vector from int[3]*/

    public Vector(int[] p1, int[] p2) {
        this.p1 = p1;
        this.p2 = p2;
        for (int i = 0; i < 3; i++) {
            this.vector[i] = p2[i] - p1[i];
        }
        norm = findNorm(vector);
    }

    public Vector(int [] v){
        vector = v;
        norm = findNorm(vector);
    }

    public static double findNorm(int[] v) {
        return  Math.sqrt(Math.pow(v[0], 2) + Math.pow(v[1], 2) + Math.pow(v[2], 2));
    }

    /*dotProduct method calculates the dot product of two vectors using a[0]b[0]+a[1]b[1]...*/
    public int dotProduct(Vector vec) {
        int [] v = vec.getVector();
        int dotProduct = 0;
        for (int i = 0; i < 3; i++) {
            dotProduct += vector[i] * v[i];
        }
        return dotProduct;
    }

    /*sets up vectors as follows:
    * |i     j   k   |
    * |a[0] a[1] a[2]|
    * |b[0] b[1] b[2]|
    * and solves for i j k which gives cross product
    * To compare to the picture vector = a and v = b*/
    public Vector crossProduct(Vector vec) {
        int [] v = vec.getVector();
        int[] crossProduct = new int[3];
        crossProduct[0] = (vector[1] * v[2]) - (v[1] * vector[2]);
        crossProduct[1] = (-1) * ((vector[0] * v[2]) - (v[0] * vector[2]));
        crossProduct[2] = (vector[0] * v[1]) - (v[0] * vector[1]);
        return new Vector(crossProduct);
    }

    /*The scalar triple product method simply calls on the dotProduct method with the crossProduct method as the parmeter.
    * Given vectors vA, vB, vC, The method method finds cBxvC and calls for dotProduct of that and vA*/
    public int STP(Vector vAB, Vector vAC) {
        int STP = dotProduct(vAB.crossProduct(vAC));
        return STP;
    }


    public double getNorm() {
        return norm;
    }

    public int[] getP1() {
        return p1;
    }

    public int[] getVector() {
        return vector;
    }
}
