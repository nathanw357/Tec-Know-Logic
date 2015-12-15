package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Chris on December 14th, 2015
 */
public class TKLLinearOp1 extends LinearOpMode {

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

        //Drive forward
        leftMotor.setPower(.75);
        rightMotor.setPower(.75);
        leftMotor2.setPower(.75);
        rightMotor2.setPower(.75);

        wait(5000);

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor2.setPower(0);
        rightMotor2.setPower(0);


        }

}
