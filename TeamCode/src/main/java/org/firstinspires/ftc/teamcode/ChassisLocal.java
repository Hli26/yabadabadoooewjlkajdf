package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Servo;

import androidx.annotation.NonNull;
import com.pedropathing.paths.PathChain;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
@Configurable
public class ChassisLocal{

    private Follower follower;

    private DcMotorEx leftFront, leftRear, rightFront, rightRear;


    //we don't put this in the DriveConstants class because it changes (i.e. it isn't a static final)
    private double lastTurretAngleDeg = 0;

    public ChassisLocal(HardwareMap hardwareMap) {

        follower = Constants.createFollower(hardwareMap);

        follower.update();


        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void startTeleop() {
        follower.startTeleopDrive();
    }

    public Follower getFollower() {
        return follower;
    }

    public void update(){
        follower.update();
    }

    private double lastHeading = 0;
    private long lastTime = 0;
    private static double headingVelocityRad = 0;

    // Filtering and Prediction Constants
    public static double HEADING_VELO_GAIN = 0.8; // 0.0 to 1.0 (Higher = more smoothing)
    public static double AIM_LOOKAHEAD = 0.12;   // Seconds of prediction

    public Pose getPose() {
        return follower.getPose();
    }

    public void setPose(Pose pose) {
        follower.setPose(pose);
    }
    public Vector getVelocity() {return follower.getVelocity();}

    public void followPath(PathChain path, boolean holdEnd) {
        follower.followPath(path, holdEnd);
    }

    public void setMaxPower(double power) {
        follower.setMaxPower(power);
    }

    public boolean isBusy() {
        return follower.isBusy();
    }


    public void drive(double y, double x, double r) {

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);

        leftFront.setPower((y + x + r) / denominator);
        leftRear.setPower((y - x + r) / denominator );
        rightFront.setPower((y - x - r) / denominator);
        rightRear.setPower((y + x - r) / denominator);
    }

}