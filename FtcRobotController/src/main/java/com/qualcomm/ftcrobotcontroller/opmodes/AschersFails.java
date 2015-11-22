package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static com.qualcomm.robotcore.hardware.DcMotor.*;


public class AschersFails extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor;
    DcMotor rightMotor2;


public void runOpMode() throws InterruptedException {
    leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
    rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
    rightMotor.setDirection(Direction.REVERSE);
    leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
    rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");
    rightMotor2.setDirection(Direction.REVERSE);
 }
}


