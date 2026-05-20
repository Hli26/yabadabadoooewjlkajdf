package org.firstinspires.ftc.teamcode.Admin.Subsystems;

import android.util.Log;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    public final ChassisLocal chassisLocal;
    public final GrabberClass grabberClass;
    //
    public Robot(HardwareMap hardwareMap) {


        chassisLocal = new ChassisLocal(hardwareMap);
        Log.w("Chassis", "Chassis Loaded");

        grabberClass = new GrabberClass(hardwareMap);
        Log.w("grabber", "grabber loaded");
        //

    }
}