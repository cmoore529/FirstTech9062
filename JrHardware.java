package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Chase on 11/30/2016.
 */

public class JrHardware {

    public DcMotor motorLeft = null;
    public DcMotor motorRight = null;
    public DcMotor armMotor = null;
    public DcMotor colorMotor = null;
    public  ColorSensor sensorColor = null;
    public DistanceSensor sensorDistance = null;
    public Servo clawServo = null;
    //double b1Position;

    //Servo rightButton;
    //double b2Position;

    //double stopPosition;


    //public static final double MID_SERVO  = 0.5;
    //public static final double SLIFT      = 0.5;
    //public static final double BLIFT      = 0.5;


    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public JrHardware() {

    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        //Define Motors
        motorLeft = hwMap.dcMotor.get("motorLeft");
        motorRight = hwMap.dcMotor.get("motorRight");
        armMotor = hwMap.dcMotor.get("armMotor");
        colorMotor = hwMap.dcMotor.get("colorMotor");

        //Initialize Motors
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        armMotor.setDirection(DcMotor.Direction.REVERSE);
        colorMotor.setDirection(DcMotor.Direction.REVERSE);

        /*liftMotor1.setDirection(DcMotor.Direction.FORWARD);
        liftMotor2.setDirection(DcMotor.Direction.FORWARD);*/

        //Set the motors to whether they're using Encoders or not.
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        colorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        /*liftMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); */

        //Set all motors to zero power.
        motorLeft.setPower(0);
        motorRight.setPower(0);
        armMotor.setPower(0);
        colorMotor.setPower(0);

        /*liftMotor1.setPower(0);
        liftMotor2.setPower(0); */

        //Define and initialize all servos.
        //leftButton = hwMap.servo.get("servo_1");
        //rightButton = hwMap.servo.get("servo_6");
        //leftButton.setPosition(b1Position);
        //rightButton.setPosition(b2Position);
        clawServo = hwMap.servo.get("clawServo");
        clawServo.setPosition(0);
        /*rightClaw = hwMap.servo.get("rightC");
        leftClaw.setPosition(MID_SERVO);
        rightClaw.setPosition(MID_SERVO); */

        sensorColor = hwMap.colorSensor.get("sensor_color_distance");






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