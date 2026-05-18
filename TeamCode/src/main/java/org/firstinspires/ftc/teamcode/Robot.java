package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ChassisLocal;

public class Robot {

    public final ChassisLocal chassisLocal;
    //
    public Robot(HardwareMap hardwareMap) {


        chassisLocal = new ChassisLocal(hardwareMap);
        Log.w("Chassis", "Chassis Loaded");
        //

    }
}