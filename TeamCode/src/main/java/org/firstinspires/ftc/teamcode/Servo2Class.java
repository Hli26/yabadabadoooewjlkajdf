package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@TeleOp(name = "roboticsintro")
@Configurable


public class Servo2Class extends LinearOpMode {

    private Robot robot;
    private Servo servo2;

    private boolean isMoving = false;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap);
        servo2 = hardwareMap.get(Servo.class, "servo2");

        waitForStart();

        while(opModeIsActive()){

            if(gamepad1.xWasPressed()){

                servo2.setPosition(0.2);


            }
            if(gamepad1.yWasPressed()){

                servo2.setPosition(0.2);


            }



        }

    }


}
