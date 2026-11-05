package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous
public class SoIHavethAParkeAutoeth extends LinearOpMode {


    private DcMotor FRW;
    private DcMotor FLW;
    private DcMotor BRW;
    private DcMotor BLW;


    //Mechs
    private DcMotor LeftShoot;
    private DcMotor RightShoot;
    private DcMotor Intake;

    private Servo Angle1;
    private Servo Angle2;

    private Servo FlowerR;

    private Servo FlowerL;
    private CRServo Stopper;
    private CRServo ThisOne2;


    @Override
    public void runOpMode() throws InterruptedException {


        //Motors

        FRW = hardwareMap.dcMotor.get("FRW");
        FLW = hardwareMap.dcMotor.get("FLW");
        BRW = hardwareMap.dcMotor.get("BRW");
        BLW = hardwareMap.dcMotor.get("BLW");
        LeftShoot = hardwareMap.dcMotor.get("LeftShoot");
        RightShoot = hardwareMap.dcMotor.get("RightShoot");
        Intake = hardwareMap.dcMotor.get("Intake");

        //Reverse Motor Direction
        FLW.setDirection(DcMotorSimple.Direction.REVERSE);
        BLW.setDirection(DcMotorSimple.Direction.REVERSE);

        // Servos
        Angle1 = hardwareMap.servo.get("Angle1");
        Angle2 = hardwareMap.servo.get("Angle2");
        FlowerR = hardwareMap.servo.get("FlowerR");
        FlowerL = hardwareMap.servo.get("FlowerL");
        Stopper = hardwareMap.crservo.get("Stopper");
        ThisOne2 = hardwareMap.crservo.get("ThisOne2");



        waitForStart();
        while (opModeIsActive()) {

        Forward(1,130);
        terminateOpModeNow();

        }



    }

    public void Forward(double power, long time) throws InterruptedException {
        FRW.setPower(power);
        BRW.setPower(power);
        FLW.setPower(power);
        BLW.setPower(power);
        sleep(time);
    }
    public void Backward(double power, long time) throws InterruptedException {
        FRW.setPower(-power);
        BRW.setPower(-power);
        FLW.setPower(-power);
        BLW.setPower(-power);
        sleep(time);
    }
    public void Right(double power, long time) throws InterruptedException {
        FRW.setPower(-power);
        BRW.setPower(power);
        FLW.setPower(power);
        BLW.setPower(-power);
        sleep(time);
    }
    public void TurnLeft(double power, long time) throws InterruptedException {
        FRW.setPower(power);
        BRW.setPower(power);
        FLW.setPower(-power);
        BLW.setPower(-power);
        sleep(time);
    }
    public void TurnRight(double power, long time) throws InterruptedException {
        FRW.setPower(-power);
        BRW.setPower(-power);
        FLW.setPower(power);
        BLW.setPower(power);
        sleep(time);
    }
    public void Stop() throws InterruptedException {
        FRW.setPower(0);
        BRW.setPower(0);
        FLW.setPower(0);
        BLW.setPower(0);
    }
}


