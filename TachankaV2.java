package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Chase Moore on 1/28/2018.
 */

@Autonomous
public class TachankaV2 extends LinearOpMode {

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor armMotor;
    DcMotor colorMotor;
    Servo clawServo;
    Servo colorServo;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    /**
     * Constructor
     */ {

    }

    private ElapsedTime runtime = new ElapsedTime();

    static final double FORWARD_SPEED = 1;
    static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        armMotor = hardwareMap.dcMotor.get("armMotor");
        colorMotor = hardwareMap.dcMotor.get("colorMotor");

        clawServo = hardwareMap.servo.get("clawServo");
        colorServo = hardwareMap.servo.get("colorServo");

        clawServo.setPosition(1);
        colorServo.setPosition(0);

        motorRight.setPower(0);
        motorLeft.setPower(0);
        armMotor.setPower(0);
        colorMotor.setPower(0);

        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        float hsvValues[] = {0F, 0F, 0F};

        final float values[] = hsvValues;

        final double SCALE_FACTOR = 255;

        int relativeLayoutID = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutID);

        boolean bPrevState = false;
        boolean bCurrState = false;

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();
       /* while(opModeIsActive()) {
            clawServo.setPosition(1);
            bCurrState = (sensorColor.red() > sensorColor.blue() && (sensorColor.red() <500));
            if((bCurrState==true) && (bCurrState!=bPrevState)) {
                motorLeft.setPower(.1);
                motorRight.setPower(-.1);
                runtime.reset();
                while (opModeIsActive() && runtime.seconds() < 3) {
                    telemetry.addData("Path", "Leg 2: % 2.5f S Elapsed", runtime.seconds());
                    telemetry.update();
                }
            }else{
                motorLeft.setPower(-.1);
                motorRight.setPower(.1);
                clawServo.setPosition(0);
                while(opModeIsActive() && (runtime.seconds() < 3));
                telemetry.addData("Path", "Leg 3 %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }

            motorRight.setPower(0);
            motorLeft.setPower(0);
            clawServo.setPosition(0);
            colorMotor.setPower(0);
        }
            */


        // Step 1: Grab the glyph
        motorLeft.setPower(0);
        motorRight.setPower(0);
        armMotor.setPower(0);
        colorMotor.setPower(0);
        clawServo.setPosition(0 );
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3)) {
            telemetry.addData("Path", "leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
            idle();
        }

        // Step 2: Lift arm that holds glyph
        motorLeft.setPower(0);
        motorRight.setPower(0);
        armMotor.setPower(.5);
        colorMotor.setPower(0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            telemetry.addData("Path", "leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
            idle();
        }

        // step 3: Drive towards cryptobox for 3 seconds
        motorLeft.setPower(.2);
        motorRight.setPower(.2 );
        colorMotor.setPower(0);
        armMotor.setPower(0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3)) {
            telemetry.addData("Path", "leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
            idle();
        }

        // step 4: Release the glyph and stop
        motorLeft.setPower(0);
        motorRight.setPower(0);
        colorMotor.setPower(0);
        armMotor.setPower(0);
        clawServo.setPosition(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            telemetry.addData("Path", "leg 4: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
            idle();
        }


    }
}
