package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by nicol on 10/22/2015.
 */
public class SlowTankMode extends OpMode {


    DcMotor leftMotorFront;
    DcMotor leftMotorRear;
    DcMotor rightMotorFront;
    DcMotor rightMotorRear;


    @Override
    public void init() {

        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");
    //    rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
    //    rightMotorRear.setDirection(DcMotor.Direction.REVERSE);


    }



    @Override
    public void loop() {


        double leftY = -gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;

        leftY = Math.pow(leftY,2) * Math.signum(leftY);
        rightY = Math.pow(rightY,2) * Math.signum(rightY);

        leftMotorFront.setPower(leftY);
        leftMotorRear.setPower(leftY);
        rightMotorFront.setPower(rightY);
        rightMotorRear.setPower(rightY);


        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
    }
}
