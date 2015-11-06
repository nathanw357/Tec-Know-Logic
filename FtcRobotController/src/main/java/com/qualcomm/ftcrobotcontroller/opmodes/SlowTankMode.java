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

    double cowPosition = 0;
    float position = 1;

    int buttonState = 0;
    int triggerState = 0;


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

        boolean cowUpButton = gamepad1.right_bumper;
        float cowDownButton = gamepad1.right_trigger;


        double leftY = -gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;

        leftY = Math.pow(leftY,2) * Math.signum(leftY);
        rightY = Math.pow(rightY,2) * Math.signum(rightY);

        leftMotorFront.setPower(leftY);
        leftMotorRear.setPower(leftY);
        rightMotorFront.setPower(rightY);
        rightMotorRear.setPower(rightY);

        if(cowUpButton){
            buttonState = 1;
        }
        else {
            if(buttonState == 1) {
                if (position == 3) {
                    cowPosition = 0.5;
                    position = 2;
                }
                else {
                    cowPosition = 0.2;
                    position = 1;
                }

                buttonState = 0;
            }
        }


        if(cowDownButton >= 0.9){
            triggerState = 1;
        }
        else{
            if(triggerState == 1){
                if(position == 1){
                    cowPosition = 0.5;
                    position = 2;
                }
                else{
                    cowPosition = 0.9;
                    position = 3;
                }

                triggerState = 0;
            }
        }

        if(triggerState == 1 && buttonState == 1){
            triggerState = 0;
            buttonState = 0;
        }

        cowCatcher.setPosition(cowPosition);

        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
    }
}
