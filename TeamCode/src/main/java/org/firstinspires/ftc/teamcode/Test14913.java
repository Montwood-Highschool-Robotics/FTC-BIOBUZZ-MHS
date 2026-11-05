package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp
public class Test14913 extends LinearOpMode {

    //Motors
    private DcMotor FRW;
    private DcMotor FLW;
    private DcMotor BRW;
    private DcMotor BLW;

    //Mechs
    private DcMotor NectarCollecter;
    private DcMotor NectarShooter;


    private CRServo Gripper1;
    private CRServo Gripper2;
    private CRServo Propeller;





    @Override
    public void runOpMode() throws InterruptedException {


        //Motors
        DcMotor FRW = hardwareMap.dcMotor.get("FRW");
        DcMotor FLW = hardwareMap.dcMotor.get("FLW");
        DcMotor BRW = hardwareMap.dcMotor.get("BRW");
        DcMotor BLW = hardwareMap.dcMotor.get("BLW");
        DcMotor NectarCollecter = hardwareMap.dcMotor.get("NectarCollecter");
        DcMotor NectarShooter = hardwareMap.dcMotor.get("NectarShooter");

        //Reverse Motor Direction
        BLW.setDirection(DcMotorSimple.Direction.REVERSE);
        FLW.setDirection(DcMotorSimple.Direction.REVERSE);


        // Servos

        CRServo Gripper1 = hardwareMap.crservo.get("Gripper1");
        CRServo Gripper2 = hardwareMap.crservo.get("Gripper2");
        CRServo Propeller = hardwareMap.crservo.get("Propeller");



        waitForStart();
        while (opModeIsActive()) {

            // Drive
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            FLW.setPower(frontLeftPower);
            BLW.setPower(backLeftPower);
            FRW.setPower(frontRightPower);
            BRW.setPower(backRightPower);

            FLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




            if (gamepad1.triangle){
                NectarCollecter.setPower(-1);
            }else if (gamepad1.square){
                NectarCollecter.setPower(1);
            }else {
                NectarCollecter.setPower(0);
            }


            if (gamepad1.right_bumper){
                Gripper1.setPower(1);
                Gripper2.setPower(1);
            } else if (gamepad1.left_bumper) {
                Gripper1.setPower(-1);
                Gripper2.setPower(-1);
            } else{
                Gripper1.setPower(0);
                Gripper2.setPower(0);
            }



            if (gamepad1.right_trigger > 0.1){
                NectarShooter.setPower(-1);
            } else if (gamepad1.left_trigger > 0.1) {
                NectarShooter.setPower(1);
            } else{
                NectarShooter.setPower(0);
            }

            if (gamepad1.circle){
                Propeller.setPower(1);
            } else if (gamepad1.cross) {
                Propeller.setPower(-1);
            } else{
                Propeller.setPower(0);
            }


            if(gamepad1.dpad_up){
                FLW.setPower(1);
                telemetry.addData("Motor", "FLW");
            }else if(gamepad1.dpad_down){
                FRW.setPower(1);
                telemetry.addData("Motor", "FRW");
            }else if(gamepad1.dpad_right){
                BLW.setPower(1);
                telemetry.addData("Motor", "BLW");
            }else if(gamepad1.dpad_left){
                BRW.setPower(1);
                telemetry.addData("Motor", "BRW");
            }
        }
    }
}