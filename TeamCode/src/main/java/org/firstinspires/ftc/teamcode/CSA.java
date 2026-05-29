package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Admin.Subsystems.Robot;

@TeleOp(name = "CSA")
@Configurable

public class CSA extends LinearOpMode
{
    //private Robot robot;
    private Servo servo1;
    private Servo servo2;

    private int caseNum = 0;



    @Override
    public void runOpMode() throws InterruptedException
    {
        //robot = new Robot(hardwareMap);
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");


        waitForStart();
        while(opModeIsActive()){

            if(gamepad1.x){

                switch(caseNum){

                    case 0:

                        servo2.setPosition(0.5);
                        caseNum ++;
                        break;

                    case 1:

                        servo2.setPosition(0.9);
                        caseNum ++;
                        break;

                    case 2:
                        servo2.setPosition(0.0);
                        caseNum ++;
                        break;


                    case 3:
                        servo2.setPosition(1.0);
                        caseNum ++;
                        break;

                    case 4:
                        servo1.setPosition(1);
                        caseNum ++;
                        break;

                    case 5:
                        servo1.setPosition(0.5);
                        caseNum ++;
                        break;
                    case 6:
                        servo1.setPosition(1.0);
                        caseNum ++;
                        break;
                }

            }


        }


        /* Creative stuff under here

        while(opModeIsActive())
        {
            robot.grabberClass.open();
            servo2.setPosition(0.5);

            if(gamepad1.x)
            {
                servo2.setPosition(0.0);
            }

            if(gamepad1.aWasPressed())
            {
                servo1.setPosition(1.0);
            }
        }

         */

    }
}


