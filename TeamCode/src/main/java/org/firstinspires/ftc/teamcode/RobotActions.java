package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ChassisLocal;


public class RobotActions {

    private ChassisLocal chassisLocal;

    private Pose shootingTargetOverride = null;
    private boolean lastBumper = false;
    private long launchStartTime = 0;
    private int shotCount = 0;
    private boolean farShootingActive = false;

    private double lastHeadingForAim = 0;
    private long lastAimTime = 0;
    private boolean isTurretFrozen = false;
    private final double FREEZE_THRESHOLD_RAD_PER_SEC = 1.5;
    private final double UNFREEZE_THRESHOLD_RAD_PER_SEC = 0.5;

    public RobotActions(ChassisLocal chassis) {
        this.chassisLocal = chassis;

    }


    // Manual hood Control

    public void setRobotPose(Pose pose) {
        chassisLocal.setPose(pose);
    }

    public void setTargetPose(Pose targetPose) {
        this.shootingTargetOverride = targetPose;
    }

    /**
     * Returns the current shooting target (override if set, otherwise default from
     * constants).
     */


}