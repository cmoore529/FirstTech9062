package org.firstinspires.ftc.teamcode;


// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Jun on 10/28/2016.
 */

@TeleOp
public class SteelTeleOp extends LinearOpMode {
    SteelHardware robot = new SteelHardware();   // Use custom hardware

    static final double FORWARD_SPEED = 0.75;
    static final double TURN_SPEED = 0.5;
    static final double SHOOT_SPEED = -1;

    double clawOffset = 0;
    final double CLAW_SPEED = 0.02;

    final static double ARM_MIN_RANGE   = 0.10;
    final static double ARM_MAX_RANGE   = 0.45;

    double stopPosition;
    double b1Position;
    double b2Position;

    double stopDelta = 0.1;




    @Override
    public void runOpMode() {

        double left;
        double right;
        double max;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        //set the starting position for the servos.

        robot.leftButton.setPosition(0.5);
        robot.rightButton.setPosition(0.5);
        robot.winchStop.setPosition(0.4);

        waitForStart();

        while (opModeIsActive()) {



            ///////////////////
            /////PLAYER 1//////
            ///////////////////

            float leftStick = -gamepad1.left_stick_y; // Left wheel
            leftStick = Range.clip(leftStick, -1, 1);
            leftStick = (float) (scaleInput(leftStick));

            float rightStick = -gamepad1.right_stick_y;
            rightStick = Range.clip(rightStick, -1, 1);
            rightStick = (float) (scaleInput(rightStick));

            robot.rightDriveMotor.setPower(rightStick * 1.0);
            robot.leftDriveMotor.setPower(leftStick * 1.0);

            float leftStick2 = -gamepad2.left_stick_y; // Left wheel
            leftStick2 = Range.clip(leftStick2, -1, 1);
            leftStick2 = (float) (scaleInput(leftStick2));

            float rightStick2 = -gamepad2.right_stick_y;
            rightStick2 = Range.clip(rightStick2, -1, 1);
            rightStick2 = (float) (scaleInput(rightStick2));

            robot.throwMotor1.setPower(leftStick2 * -1.0);
            robot.throwMotor2.setPower(leftStick2 * -1.0);
            robot.conveyor.setPower(rightStick2 * -1.0);




            //use analog sticks to drive.
            if (gamepad1.left_stick_y != 0) {
                telemetry.addData("leftDrive", leftStick);
            }
            if (gamepad1.right_stick_y != 0) {
                telemetry.addData("rightDrive", rightStick);
            }

            // Use gamepad to control left button presser.
            if (gamepad1.dpad_up) {
                robot.leftButton.setPosition(0.5);
            }
            if (gamepad1.dpad_down) {
                robot.leftButton.setPosition(-1.0);
            }


            // Use gamepad to control right button presser.
            if (gamepad1.y) {
                robot.rightButton.setPosition(0.5);
            }
            if (gamepad1.a) {
                robot.rightButton.setPosition(-1.0);
            }


            /*Use Left/Right bumpers to control both conveyors.
            if (gamepad1.left_bumper)
                robot.conveyor1.setPower(robot.LCONVEYOR);
            else
                robot.conveyor1.setPower(0.0);

            if (gamepad1.right_bumper)
                robot.conveyor2.setPower(robot.RCONVEYOR);
            else
                robot.conveyor2.setPower(0.0);


            //Use dpad UP/DOWN to control shooter lift.
            if (gamepad1.dpad_up)
                robot.liftMotor1.setPower(robot.SLIFT);
            if (gamepad1.dpad_down)
                robot.liftMotor1.setPower(-1.0);

            //Use A/Y to control cap ball lift.
            if (gamepad1.y)
                robot.liftMotor2.setPower(robot.BLIFT);
            if (gamepad1.a)
                robot.liftMotor2.setPower(-1.0);
            */

            ///////////////////
            //////PLAYER 2/////
            ///////////////////

            // Use analog sticks to control the shooters

            if (gamepad2.left_stick_y != 0) {
                telemetry.addData("throwLeft", leftStick2);
            } else
                robot.throwMotor1.setPower(0.0);


            if (gamepad2.left_stick_y != 0) {
                telemetry.addData("throwRight", leftStick2);
            } else
                robot.throwMotor2.setPower(0.0);


            if (gamepad2.right_stick_y != 0) {
                telemetry.addData("conveyor", rightStick2);
            } else
                robot.conveyor.setPower(0.0);


            if (gamepad2.dpad_up) {
                robot.winch.setPower(1.0); // Holding up, full speed ahead
            }
            if (gamepad2.dpad_down){
                robot.winch.setPower(-1.0); // Holding down, full reverse
            }
            else
            {
                robot.winch.setPower(0.0); // Stop
            }


            if (gamepad2.a) {
                stopPosition += stopDelta;
            }
            if (gamepad2.y) {
                stopPosition -= stopDelta;
            }

            stopPosition = Range.clip(stopPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);

            robot.winchStop.setPosition(stopPosition);

            telemetry.addData("Text", "*** Robot Data***");
            telemetry.addData("stopper", "stopper:  " + String.format("%.2f", stopPosition));

            /*
            // Use Left/Right Bumpers to open and close claw.
            if (gamepad2.right_bumper)
                clawOffset += CLAW_SPEED;
            else if (gamepad2.left_bumper)
                clawOffset -= CLAW_SPEED;

            //Move both servos to new position. Assume they are mirrored.
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
            robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);

            */
            // telemetry.addData("Distance", senseDistance);

            //telemetry.addData("claw", "Offset = %.2f", clawOffset);
            telemetry.update();
        }
    }






        //@Override
        //public void stop();
        //{
        //    sidePosition = 1.0; armPosition = 0.95;
        //    leftButton.setPosition(sidePosition); //armServo.setPosition(armPosition);
        //}

    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}


