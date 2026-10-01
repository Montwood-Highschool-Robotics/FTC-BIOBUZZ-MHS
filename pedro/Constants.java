package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-0.5044885695449949);
        c.yPodOffset.set(-2.021491283506859);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("FLW");
        c.frontRightName.set("FRW");
        c.backLeftName.set("BLW");
        c.backRightName.set("BRW");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.21041854294455356);
                Controller secondaryTranslationalForward = Controller.proportional(0.07774402913099422);
                Controller primaryTranslationalLateral = Controller.proportional(0.319929956060367);
                Controller secondaryTranslationalLateral = Controller.proportional(0.11820557007843628);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.011550781436689025));
                c.brake.set(Controller.proportionalFeedforward(0.009818164221185671));

                c.headingFeedback.set(Controller.proportional(3.076298478430215));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04074099890001925, 0.008964909358685656));

                c.linearBrakeCoefficients.set(Matrix.diag(0.08523740287098185, 0.06782497193623431));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0018377388593995973, 0.0018624435315785057));

                c.maxAchievableForwardVelocity.set(56.35753026244337);
                c.maxAchievableStrafeVelocity.set(45.792087109300475);
                c.naturalForwardDeceleration.set(34.69772886978914);
                c.naturalStrafeDeceleration.set(53.639664301333084);
            }
    );
}