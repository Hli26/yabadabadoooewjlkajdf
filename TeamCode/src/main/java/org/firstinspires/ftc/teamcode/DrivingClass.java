package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.bylazar.telemetry.PanelsTelemetry;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.RobotActions;


@TeleOp(name = "TeleOp Driving")
@Configurable

public class DrivingClass extends LinearOpMode {
    Robot robot;
    RobotActions actions;
    private boolean grabberOpen = false;
    boolean sillyControls = false;
    boolean inPosition = false;
    static TelemetryManager telemetryM;


    public static double RpmChange = 20;
    public static double intakespeed = 1;
    public double count = 1.0;


    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();


        // Initialize the specialized TEST TURRET

        actions = new RobotActions(
                robot.chassisLocal,
                robot.grabberClass
        );

        waitForStart();

        while (opModeIsActive()) {

            robot.chassisLocal.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            if (gamepad1.aWasPressed()) {
                grabberOpen = !grabberOpen;
                if (grabberOpen) {
                    robot.grabberClass.open();
                } else {
                    robot.grabberClass.close();
                }
            }
            telemetry.addData("Grabber servo", robot.grabberClass.ServoPosition());
            telemetry.update();

        }
    }
}
