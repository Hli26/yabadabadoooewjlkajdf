package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.bylazar.telemetry.PanelsTelemetry;


@TeleOp(name = "TeleOp Driving")
@Configurable

public class DrivingClass extends LinearOpMode {
    Robot robot;
    RobotActions actions;
    boolean sillyControls = false;
    boolean inPosition = false;
    static TelemetryManager telemetryM;


    public static double RpmChange = 20;
    public static double intakespeed = 1;


    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();


        // Initialize the specialized TEST TURRET

        actions = new RobotActions(
                robot.chassisLocal
        );

        waitForStart();
        robot.chassisLocal.startTeleop();

        while (opModeIsActive()) {

            robot.chassisLocal.update();
            robot.chassisLocal.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        }


    }
}
