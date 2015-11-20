package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

import static com.qualcomm.robotcore.hardware.DcMotor.*;

/**
 * Created by Ascher on 11/19/2015.
 */
public class AschersFails {
    DcMotor leftMotor;


public void runOpMode() throws InterruptedException {
    leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
    rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
    rightMotor.setDirection(Direction.REVERSE);
    leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
    rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");
    rightMotor2.setDirection(Direction.REVERSE);
 }
}
