package org.firstinspires.ftc.teamcode.Admin.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Configurable
public class GrabberClass {
    private Servo grabber;
    private Servo grabberBot2;


    public GrabberClass(HardwareMap hardwareMap)
    {
        grabber = hardwareMap.get(Servo.class, "servo");
        grabberBot2=hardwareMap.get(Servo.class, "servo");
        grabber.scaleRange(0, 0.113);
        grabberBot2.scaleRange(0,0.21);

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
    public void open2()
    {
        grabberBot2.setPosition(open);
    }
    public void close2()
    {
        grabberBot2.setPosition(close);
    }
    public double ServoPosition()
    {
        return grabber.getPosition();
    }
    public double ServoPosition2()
    {
        return grabberBot2.getPosition();
    }
}
