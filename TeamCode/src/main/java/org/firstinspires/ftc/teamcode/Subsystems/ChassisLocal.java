package org.firstinspires.ftc.teamcode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;


import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.*;

//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
@Configurable
public class ChassisLocal{

    private Follower follower;

    private DcMotorEx leftFront, leftRear, rightFront, rightRear;


    //we don't put this in the DriveConstants class because it changes (i.e. it isn't a static final)
    private double lastTurretAngleDeg = 0;

    public ChassisLocal(HardwareMap hardwareMap) {



        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);

    }



    private double lastHeading = 0;
    private long lastTime = 0;
    private static double headingVelocityRad = 0;

    // Filtering and Prediction Constants
    public static double HEADING_VELO_GAIN = 0.8; // 0.0 to 1.0 (Higher = more smoothing)
    public static double AIM_LOOKAHEAD = 0.12;   // Seconds of prediction



    public void drive(double y, double x, double r) {

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);

        leftFront.setPower((y + x + r) / denominator);
        leftRear.setPower((y - x + r) / denominator );
        rightFront.setPower((y - x - r) / denominator);
        rightRear.setPower((y + x - r) / denominator);
    }

}