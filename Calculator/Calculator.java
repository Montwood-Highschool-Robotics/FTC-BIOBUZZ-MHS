package org.firstinspires.ftc.teamcode.Calculator;

import java.lang.Math;



public class Calculator {

    //Basics
    public double addition(double a , double b) {
        return(a + b);
    }
    public double subtraction(double a , double b) {
        return(a - b);
    }
    public double multiplication(double a , double b) {
        return(a * b);
    }
    public double division(double a , double b) {
        return(a / b);
    }

    //Physics
    public double trajectoryOfASphereProjectile(double hi , double goalPosition , double angle , double V0) {
        double g = 9.8;
        double y = (hi + (goalPosition * Math.tan(angle))) - (((g * (Math.pow(goalPosition , 2))) / ((2 * Math.pow(V0 , 2)) * (Math.pow(Math.cos(angle) , 2)))));
        return(y);
    }

    public double initialVelocity(double tps , double encoderTicksPerRev , double ballMass , double ballRadiusCMeters , double wheelRadiusCMeters , double wheelAmount , double wheelTorque , double squeezeCMeters) {
        double rpm = (tps * 60)/encoderTicksPerRev;
        double w = ((2 * Math.PI)/60) * rpm;
        double vt = w * wheelRadiusCMeters;
        double a = (wheelRadiusCMeters + ballRadiusCMeters - squeezeCMeters) / (wheelRadiusCMeters + ballRadiusCMeters);
        double vMulti = Math.pow((2 * wheelAmount * wheelTorque * (Math.cos(a/2))) / ballMass, (double) 1 /2);
        return(vt);
    }



    //To be implemented in toasp
    public double drag(double airDensity , double V , double dragCoefficient , double frontalArea) {
        double Fd = 1/2 * (airDensity * Math.pow(V , 2) * dragCoefficient * frontalArea);
        return(Fd);
    }
    public double spinThin(double mass , double innerRadius , double angularVelocity) {
        double I = (double) 2 /3 * (mass * Math.pow(innerRadius , 2));
        double L = I * angularVelocity;
        double K = (double) 1 /2 * (I * Math.pow(angularVelocity , 2));
        return(K);
    }

}

