 package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Jun on 11/30/2016.
 */

public class SteelHardware {

    public DcMotor leftDriveMotor = null;
    public DcMotor rightDriveMotor = null;
    public DcMotor throwMotor1 = null;
    public DcMotor throwMotor2 = null;
    public DcMotor conveyor = null;
    public DcMotor winch = null;
    public DcMotor liftMotor1 = null;
    public DcMotor liftMotor2 = null;
    public ColorSensor sensorRGB = null;
    public DeviceInterfaceModule cdim = null;

    //public DcMotor collector = null;

    //public Servo leftButton = null;
    //public Servo rightButton = null;
    public Servo leftClaw = null;
    public Servo rightClaw = null;


    Servo leftButton;
    double b1Position;

    Servo rightButton;
    double b2Position;

    Servo winchStop;
    double stopPosition;


    public static final double MID_SERVO  = 0.5;
    public static final double SLIFT      = 0.5;
    public static final double BLIFT      = 0.5;


    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public SteelHardware() {

    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        //Define Motors
        leftDriveMotor = hwMap.dcMotor.get("leftDrive");
        rightDriveMotor = hwMap.dcMotor.get("rightDrive");
        throwMotor1 = hwMap.dcMotor.get("throwLeft");
        throwMotor2 = hwMap.dcMotor.get("throwRight");
        conveyor = hwMap.dcMotor.get("conveyor");
        winch = hwMap.dcMotor.get("winch");
        /*liftMotor1 = hwMap.dcMotor.get("shootLift");
        liftMotor2 = hwMap.dcMotor.get("ballLift"); */

        //Initialize Motors
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        throwMotor1.setDirection(DcMotor.Direction.REVERSE);
        throwMotor2.setDirection(DcMotor.Direction.FORWARD);
        conveyor.setDirection(DcMotor.Direction.REVERSE);
        winch.setDirection(DcMotor.Direction.FORWARD);

        /*liftMotor1.setDirection(DcMotor.Direction.FORWARD);
        liftMotor2.setDirection(DcMotor.Direction.FORWARD);*/

        //Set the motors to whether they're using Encoders or not.
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        throwMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        throwMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        conveyor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        winch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        /*liftMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); */

        //Set all motors to zero power.
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        throwMotor1.setPower(0);
        throwMotor2.setPower(0);
        conveyor.setPower(0);
        winch.setPower(0);

        /*liftMotor1.setPower(0);
        liftMotor2.setPower(0); */

        //Define and initialize all servos.
        leftButton = hwMap.servo.get("leftB");
        rightButton = hwMap.servo.get("rightB");
        leftButton.setPosition(b1Position);
        rightButton.setPosition(b2Position);
        winchStop = hwMap.servo.get("stopper");
        winchStop.setPosition(stopPosition);
        /*leftClaw = hwMap.servo.get("leftC");
        rightClaw = hwMap.servo.get("rightC");
        leftClaw.setPosition(MID_SERVO);
        rightClaw.setPosition(MID_SERVO); */






    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */

    public void waitForTick(long periodMs) {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
