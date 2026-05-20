package org.firstinspires.ftc.teamcode.Admin.Subsystems;

import com.pedropathing.geometry.Pose;


public class RobotActions {

    private ChassisLocal chassisLocal;
    private GrabberClass grabberClass;

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

    public RobotActions(ChassisLocal chassis, GrabberClass grabber) {
        this.chassisLocal = chassis;
        this.grabberClass = grabber;

    }


    // Manual hood Control



    /**
     * Returns the current shooting target (override if set, otherwise default from
     * constants).
     */


}