package org.firstinspires.ftc.teamcode.Admin.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoAdjustClass extends LinearOpMode {
    private Servo grabber;

    @Override
    public void runOpMode() throws InterruptedException {
        grabber=hardwareMap.get(Servo.class, "servo");
        grabber.setPosition(0);
        waitForStart();
        while(opModeIsActive())
        {
            if(gamepad1.dpad_left)
            {
                grabber.setPosition(grabber.getPosition()-0.0005);
            }
            if(gamepad1.dpad_right)
            {
                grabber.setPosition(grabber.getPosition()+0.0005);
            }
            telemetry.addData("Grabber Position", grabber.getPosition());
            telemetry.update();
        }
    }
}
