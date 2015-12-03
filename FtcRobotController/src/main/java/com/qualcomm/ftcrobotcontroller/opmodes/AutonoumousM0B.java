package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AutonoumousM0B extends LinearOpMode {
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");

        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        leftMotorRear.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();


        leftMotorFront.setPowerFloat();
        leftMotorRear.setPowerFloat();
        rightMotorFront.setPowerFloat();
        rightMotorRear.setPowerFloat();
    }

}
