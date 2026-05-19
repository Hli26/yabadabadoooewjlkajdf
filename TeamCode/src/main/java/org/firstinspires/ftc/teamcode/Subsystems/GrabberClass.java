package org.firstinspires.ftc.teamcode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Configurable
public class GrabberClass {
    private Servo grabber;

    public GrabberClass(HardwareMap hardwareMap)
    {
        grabber = hardwareMap.get(Servo.class, "servo");

    }
    private double open = 1.0;
    private double close = 0.0;

    public void open()
    {
        grabber.setPosition(open);
    }
    public void close()
    {
        grabber.setPosition(close);
    }
    public double ServoPosition()
    {
        return grabber.getPosition();
    }
}
