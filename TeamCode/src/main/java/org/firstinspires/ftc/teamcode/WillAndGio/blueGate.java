package org.firstinspires.ftc.teamcode.WillAndGio;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Admin.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Admin.Subsystems.RobotActions;

@Autonomous(name = "BlueGate", group = "auto", preselectTeleOp ="BLUEEEEE b")
public class blueGate extends OpMode {
    /**
     * Fixed field-relative turret angle (degrees) for this auton after run starts.
     * Tune on the field (telemetry used to show dynamic angle; match that here).
     */
    private static final double AUTON_FIXED_TURRET_ANGLE_DEG = 23;
    private static final double AUTON_FIXED_TURRET_ANGLE_DEG_FIRST = 22;
    private static final double AUTON_FIXED_TURRET_ANGLE_DEG_FINAL = -13;
    private static final double AUTON_RPM_OFFSET = 10;
    private static final double AUTON_RPM_OFFSET_FIRST_SHOT = 60;

    /**
     * Open blocker (`robot.gate`) when this close to the shot pose (inches).
     */
    private static final double GATE_OPEN_PROXIMITY_IN = 5;
    private static final double FIRST_SHOT_PROXIMITY = 5;
    private static final double LAST_SHOT_PROXIMITY = 15;


    Pose sillyTarget;
    private int rotatorStartPosition = 0;
    double txDeg = 0.0;
    double tyDeg = 0.0;
    Robot robot;
    private RobotActions actions;
    private static final double BALL_DETECT_TIMEOUT_SEC = 3;

    private boolean shoot1Started = false;
    private boolean shoot2Started = false;
    private boolean shoot3Started = false;
    private boolean shoot4Started = false;
    private boolean shoot5Started = false;
    private boolean goAwayFromGateStarted = false;
    private boolean goTowardsGateStarted = false;
    private boolean opengateStarted = false;
    private boolean parkingStarted = false;
    private boolean collectionStarted = false;
    private boolean beginGateCollectionStarted = false;
    private boolean gateCollectionStarted = false;
    private boolean turn = false;
    private boolean beginGateCollectionAgainStarted = false;
    private boolean gateCollectionAgainStarted = false;
    private boolean shotFeeding = false;
    /**
     * True after we have reached the gate pose; gate dwell timer runs only from here.
     */
    private boolean gateWallDwellStarted = false;
    private Follower follower;

    private Servo hood, blocker;
    private int limeHeight = 33;
    private int offset = 28;
    private int tagHeight = 75;
    private static final double NORMAL_DRIVE_POWER = 1;
    private static final double INTAKE_DRIVE_POWER = 1;

    private static final double DISTANCE_THRESHOLD = 180.0;
    private static final double CLOSE_HOOD_POSITION = .2541;
    private static final double FAR_HOOD_POSITION = 0.36;

    private int y = tagHeight - limeHeight;

    int motor180Range = 910;
    int limelightUpAngle = 25;
    private int vMultiplier = 9;
    private Limelight3A limelight;

    private double lastValidTx = 0.0;
    private double lastValidTy = 0.0;
    private double lastValidDistance = 0.0;
    private boolean hasValidLimelightData = false;

    private DcMotor leftFront, leftRear, rightFront, rightRear;

    private Timer pathTimer, opModeTimer, gateTimer, shootTimer, openTimer;

    public enum PathState {
        start,
        actuallyshoot1,
        gotocollect,
        collection,
        goAwayFromGate,
        goTowardsGate,
        opengate,
        shoot,
        collectAgain,
        collectAgainEnd,
        shootAgain,
        collectAgainAgain,
        collectAgainAgainEnd,
        shootAgainAgain,
        collectAgainAgainAgain,
        collectAgainAgainAgainEnd,
        shootAgainAgainAgain,
        parklol,
        done,
        VERYYYY_THIRD_INTAKE,
        THIRD_SHOT_PREP,
        PAUSE3,
        Turny,
        SHOT_3,
        JACK_OFF,
        beginGateCollection,
        GateCollection,
        beginGateCollectionAgain,
        GateCollectionAgain,
        GateCollectionAgainAgain,
        shootAgainAgainAgainAgain,
        shoot3ToGate
    }

    PathState pathState;

    private final Pose startPose = new Pose(38, 130, Math.toRadians(-90));
    private final Pose shootPose1 = new Pose(38, 56, Math.toRadians(-90));
    private final Pose collect1thing = new Pose(70, 56, Math.toRadians(0));
    private final Pose goToCollect1ControlPoint = new Pose(52.265, 60.091);
    private final Pose shootPose2 = new Pose(63, 77, Math.toRadians(155));
    private final Pose gateCollect1 = new Pose(13.5, 63, Math.toRadians(148));
    private final Pose option1 = new Pose(131, 61, Math.toRadians(45));
    private final Pose option2 = new Pose(137, 55, Math.toRadians(90));

    private final Pose shootBall3 = new Pose(63, 77, Math.toRadians(155));
    private final Pose inBetween2 = new Pose(100, 62, Math.toRadians(202.5));
    private final Pose gateCollect2 = new Pose(128.5, 62, Math.toRadians(205));
    private final Pose shootBall4 = new Pose(84, 75, Math.toRadians(230));
    private final Pose gateCollect3 = new Pose(128.5, 62, Math.toRadians(205));
    private final Pose shootBall5 = new Pose(95, 87, Math.toRadians(230));
    private final Pose collect3end = new Pose(20, 88, Math.toRadians(180));
    private final Pose collect3ControlPoint = new Pose(102.98, 84.857);
    private final Pose shootBall6 = new Pose(55, 120, Math.toRadians(147));
    private final Pose park = new Pose(103, 84, Math.toRadians(226));

    private PathChain shoot1, Turn, goToCollect1, shoot3ToGate, collect1, shoot2, GateCollect3, shoot6, InBetween1, InBetween2, GateCollect1, GateCollect2, shoot3, awayfromGate, goToCollect3, collect3, shoot4, goToGate, openGate, goToCollect4, collect4, shoot5, parking;

    public void buildPaths() {
        shoot1 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose1))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose1.getHeading())
                .build();

        collect1 = follower.pathBuilder()
                .addPath(new BezierLine(shootPose1, collect1thing))
                .setLinearHeadingInterpolation(shootPose1.getHeading(), collect1thing.getHeading())
                .build();

        shoot2 = follower.pathBuilder()
                .addPath(new BezierLine(collect1thing, shootPose2))
                .setLinearHeadingInterpolation(collect1thing.getHeading(), shootPose2.getHeading())
                .build();
        GateCollect1 = follower.pathBuilder()
                .addPath(new BezierLine(shootPose2, gateCollect1))
                .setLinearHeadingInterpolation(shootPose2.getHeading(), gateCollect1.getHeading())
                .build();
        Turn = follower.pathBuilder()
                .addPath(new BezierLine(gateCollect1, option1))
                .setLinearHeadingInterpolation(gateCollect1.getHeading(), option1.getHeading())
                .build();


        shoot3 = follower.pathBuilder()
                .addPath(new BezierLine(gateCollect1, shootBall3))
                .setLinearHeadingInterpolation(gateCollect1.getHeading(), shootBall3.getHeading())
                .build();

        collect3 = follower.pathBuilder()
                .addPath(new BezierLine(shootBall3, collect3end))
                .setLinearHeadingInterpolation(shootBall3.getHeading(), collect3end.getHeading())
                .build();

        shoot6 = follower.pathBuilder()
                .addPath(new BezierLine(collect3end, shootBall6))
                .setLinearHeadingInterpolation(collect3end.getHeading(), shootBall6.getHeading())
                .build();
    }

    public void statePathUpdate() {
        switch (pathState) {
            case start:
                    follower.followPath(shoot1);
                    if(!follower.isBusy())
                    {
                        setPathState(PathState.actuallyshoot1);
                    }
                // Do not use setPathState here: it clears shoot1Started and breaks the first-shot logic.
                break;
            case actuallyshoot1:
                follower.followPath(collect1);
                if(!follower.isBusy())
                {
                    setPathState(PathState.done);
                }
                break;
            case done:
                break;
        }
    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();
        shoot1Started = false;
        shoot2Started = false;
        shoot3Started = false;
        shoot4Started = false;
        shoot5Started = false;
        goAwayFromGateStarted = false;
        goTowardsGateStarted = false;
        opengateStarted = false;
        parkingStarted = false;
        collectionStarted = false;
        beginGateCollectionStarted = false;
        turn = false;
        gateCollectionStarted = false;
        beginGateCollectionAgainStarted = false;
        gateCollectionAgainStarted = false;
        shotFeeding = false;
        gateWallDwellStarted = false;
    }

    @Override
    public void init() {
        pathState = PathState.start;

        pathTimer = new Timer();
        opModeTimer = new Timer();
        gateTimer = new Timer();
        shootTimer = new Timer();
        openTimer = new Timer();

        robot = new Robot(hardwareMap);
        // Important: create TestTurret AFTER Robot so launcher motors end in TestTurret's
        // RUN_WITHOUT_ENCODER + manual power-loop configuration (same order as AmazingBotBlue).
        actions = new RobotActions(
                robot.chassisLocal,
                robot.grabberClass
        );
        buildPaths();

        telemetry.addLine("Good to go BLUE");
        telemetry.update();
    }

    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);
    }

    //
    @Override
    public void loop() {
        statePathUpdate();


        telemetry.update();
    }
}
