package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reva Mollison on 12/2/2017.
 */

@TeleOp (name = "TestOp", group = "Testing")
public class TestOp extends LinearOpMode
{
    public DcMotor motorLeft  = null;
    public DcMotor motorRight = null;
    public Servo   clawServo  = null;
    public DcMotor armMotor   = null;
    public DcMotor colorMotor  = null;

        private static final double ARM_RETRACTED_POSITION = 0.2;
        private static final double ARM_EXTENDED_POSITION =  0.8;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        armMotor = hardwareMap.dcMotor.get("armMotor");
        colorMotor = hardwareMap.dcMotor.get("colorMotor");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        clawServo = hardwareMap.servo.get("clawServo");

         waitForStart();

        while(opModeIsActive())
        {
            motorLeft.setPower(-gamepad1.left_stick_y/.65);
            motorRight.setPower(-gamepad1.right_stick_y/.65 );

            // Driver 2
            colorMotor.setPower(-gamepad2.right_stick_y/6);
            armMotor.setPower(-gamepad2.left_stick_y);
            //idle();

            if(gamepad2.x)  {
                clawServo.setPosition(0);
            }
            if(gamepad2.b)  {
                clawServo.setPosition(1.0);
            }





        }
    }
}
