package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TKLTeleOp extends OpMode {

    final static double WRIST_MIN_RANGE = 0.20;
    final static double WRIST_MAX_RANGE = 0.90;

//  Position of the claw servo
    double clawPosition;


//  Position of the Wrist Servo
    double wristPosition;

// Setting the Dc motors
//    DcMotor leftMotorFront;
//    DcMotor leftMotorRear;
//    DcMotor rightMotorFront;
//    DcMotor rightMotorRear;
    DcMotor ShoulderX;
    DcMotor ShoulderY;
    DcMotor elbow;

//  Setting the Servo motors
    Servo wrist;
    Servo claw;
//    Servo cowCatcher;
    int count=1;

    @Override
    public void init() {


//        DcMotors being linked to variable that links to DcMotor inputs on phone
//        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
//        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
//        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
//        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");
        ShoulderX = hardwareMap.dcMotor.get("ShoulderX");
        ShoulderY = hardwareMap.dcMotor.get("ShoulderY");
        elbow = hardwareMap.dcMotor.get("elbow");

//      Servos being linked to variable that links to servo input on phone

        claw = hardwareMap.servo.get("claw");
        wrist = hardwareMap.servo.get("wrist");

//      cowCatcher = hardwareMap.servo.get("cowCatcher");

//      Starting position for servos

        clawPosition = 0.5;
        wristPosition = 0.2;

    }

    @Override
    public void loop() {


        double leftY = -gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;

//      Gamepad2 sticks registered to variables
        double rightY2 = gamepad2.right_stick_y;
        double rightX2 = gamepad2.right_stick_x;
        double leftY2 = -gamepad2.left_stick_y;
        double leftX2 = -gamepad2.left_stick_x;


        leftY = Math.pow(leftY,2) * Math.signum(leftY);
        rightY = Math.pow(rightY,2) * Math.signum(rightY);
        leftY2 = Math.pow(leftY2,2) * Math.signum(leftY2);
        leftX2 = Math.pow(leftX2,2) * Math.signum(leftX2);
        rightX2 = Math.pow(rightX2,2) * Math.signum(rightX2);
        wristPosition = Math.pow(rightY2,2) * Math.signum(rightY2);


//      Clip the position values so that they never exceed their allowed range.
        wristPosition = Range.clip(wristPosition, WRIST_MIN_RANGE, WRIST_MAX_RANGE);

//      Write position values to the elbow, shoulder and claw servo
        if(gamepad2.right_bumper) {
            claw.setPosition(0.9);
        }

        else {
            claw.setPosition(clawPosition);
        }

//      Wrist Position

        wrist.setPosition(wristPosition);

//      Elbow power

        elbow.setPower(rightX2);


//      DcMotor Code to set power
//        leftMotorFront.setPower(leftY);
//        leftMotorRear.setPower(leftY);
//        rightMotorFront.setPower(rightY);
//        rightMotorRear.setPower(rightY);
        ShoulderX.setPower(leftX2);
        ShoulderY.setPower(leftY2);


//      Sends robot data back to driver station
        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("elbow", "elbow:  " + String.format("%.2f", rightX2));
        telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
        telemetry.addData("wrist", "wrist:  " + String.format("%.2f", wristPosition));
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
        telemetry.addData("leftY2 tgt pwr", "leftY2 pwr: " + String.format("%.2f", leftY2));
        telemetry.addData("rightY2 tgt pwr", "rightY2 pwr: " + String.format("%.2f", rightY2));
        telemetry.addData("Count: ", count++);

    }
}