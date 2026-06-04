package org.firstinspires.ftc.teamcode.Admin;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Admin.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Admin.Subsystems.RobotActions;


@TeleOp(name = "TeleOp Driving 3")
@Configurable

public class DrivingClass3 extends LinearOpMode {
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

            telemetry.addData("Grabber servo", robot.grabberClass.ServoPosition2());
            telemetry.update();

        }
    }
}
