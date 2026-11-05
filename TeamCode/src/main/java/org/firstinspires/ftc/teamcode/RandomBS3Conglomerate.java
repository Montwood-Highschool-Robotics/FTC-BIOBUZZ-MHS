package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@TeleOp
public class RandomBS3Conglomerate extends LinearOpMode {

    private Limelight3A limelight;

    //Motors
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
        DcMotor FRW = hardwareMap.dcMotor.get("FRW");
        DcMotor FLW = hardwareMap.dcMotor.get("FLW");
        DcMotor BRW = hardwareMap.dcMotor.get("BRW");
        DcMotor BLW = hardwareMap.dcMotor.get("BLW");
        DcMotor LeftShoot = hardwareMap.dcMotor.get("LeftShoot");
        DcMotor RightShoot = hardwareMap.dcMotor.get("RightShoot");
        DcMotor Intake = hardwareMap.dcMotor.get("Intake");

        //Reverse Motor Direction
        FLW.setDirection(DcMotorSimple.Direction.REVERSE);
        BLW.setDirection(DcMotorSimple.Direction.REVERSE);


        // Servos
        Servo Angle1 = hardwareMap.servo.get("Angle1");
        Servo Angle2 = hardwareMap.servo.get("Angle2");
        Servo FlowerR = hardwareMap.servo.get("FlowerR");
        Servo FlowerL = hardwareMap.servo.get("FlowerL");
        CRServo Stopper = hardwareMap.crservo.get("Stopper");
        CRServo ThisOne2 = hardwareMap.crservo.get("ThisOne2");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(0);
        limelight.start();

        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(parameters);


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

            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            limelight.updateRobotOrientation(orientation.getYaw(AngleUnit.DEGREES));
            LLResult result = limelight.getLatestResult();
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botpose = result.getBotpose_MT2();
                    // Use botpose data
                    telemetry.addData("Limelight Status:", "Yes");
                    telemetry.addData("Bot pose:", botpose);
                    telemetry.update();

                }
            }

            //Shooter
            if (gamepad2.dpad_up) {
                LeftShoot.setPower(0.5);
                RightShoot.setPower(0.5);

            }else if (gamepad2.dpad_down){
                LeftShoot.setPower(-.5);
                RightShoot.setPower(-0.5);
            }else {

                LeftShoot.setPower(0);
                RightShoot.setPower(0);
            }


            //Intake
            if (gamepad2.triangle){
                Intake.setPower(-1);
            }else if (gamepad2.square){
                Intake.setPower(1);
            }else {
                Intake.setPower(0);
            }


            //Angle
            if (gamepad2.right_bumper){
                Angle1.setPosition(.5);
                Angle2.setPosition(0);
            } else if (gamepad2.left_bumper) {
                Angle1.setPosition(0);
                Angle2.setPosition(0.5);
            }

            //Flower Ramp Mech

            if (gamepad2.right_trigger > 0.1){
                FlowerR.setPosition(1);
                FlowerL.setPosition(0);
            } else if (gamepad2.left_trigger > 0.1) {
                FlowerR.setPosition(0);
                FlowerL.setPosition(1);
            }

            if (gamepad2.circle){
                Stopper.setPower(1);
                ThisOne2.setPower(-1);
            } else if (gamepad2.cross) {
                Stopper.setPower(-1);
                ThisOne2.setPower(1);
            } else{
                Stopper.setPower(0);
                ThisOne2.setPower(0);
            }


        }
    }
}