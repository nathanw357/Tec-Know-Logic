package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Chris on December 17th 2015
 */
public class TKLLinearOp2 extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    @Override
    public void runOpMode()  throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");


//        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        //--------Go Forward--------------
        telemetry.addData("text", "Moving Forward!");
        leftMotor.setPower(0.75);
        rightMotor.setPower(0.75);
        leftMotor2.setPower(0.75);
        rightMotor2.setPower(0.75);

        //wait for 5 seconds
        wait(5000);
        telemetry.clearData();

        //Swerve Right
        leftMotor.setPower(0.75);
        rightMotor.setPower(0.75);
        leftMotor2.setPower(0.75);
        rightMotor2.setPower(0.75);

        //wait for a half of a second
        wait(500);

        //Swerve Left
        leftMotor.setPower(0.75);
        rightMotor.setPower(0.75);
        leftMotor2.setPower(0.75);
        rightMotor2.setPower(0.75);
    }
}
