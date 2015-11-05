package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by nicol on 10/22/2015.
 */
public class SlowTankMode extends OpMode {


    DcMotor leftMotorFront;
    DcMotor leftMotorRear;
    DcMotor rightMotorFront;
    DcMotor rightMotorRear;

    Servo cowCatcher;

    double cowPosition = 0.9;
    float position = 1;


    @Override
    public void init() {

        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");

        cowCatcher = hardwareMap.servo.get("cowCatcher");

        //    rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
    //    rightMotorRear.setDirection(DcMotor.Direction.REVERSE);


    }



    @Override
    public void loop() {

        boolean cowUp = gamepad1.right_bumper;
        boolean cowMid = gamepad1.left_bumper;
        float cowDown = gamepad1.right_trigger;


        double leftY = -gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;

        leftY = Math.pow(leftY,2) * Math.signum(leftY);
        rightY = Math.pow(rightY,2) * Math.signum(rightY);

        leftMotorFront.setPower(leftY);
        leftMotorRear.setPower(leftY);
        rightMotorFront.setPower(rightY);
        rightMotorRear.setPower(rightY);

        if (cowUp == true) {

            if (position == 2) {
                cowPosition = 0.2;
                position = 1;
            }
            if (position == 3) {
                cowPosition = 0.5;
                position = 2;
            }
        }

        if (cowDown >= 0.9) {
            if (position == 1) {
                cowPosition = 0.5;
                position = 2;
            }
            if (position == 2) {
                cowPosition = 0.9;
                position = 3;
            }

        }

        cowCatcher.setPosition(cowPosition);

        try {
            Thread.sleep(100);                 //100 milliseconds is 0.1 second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }


        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
    }
}
