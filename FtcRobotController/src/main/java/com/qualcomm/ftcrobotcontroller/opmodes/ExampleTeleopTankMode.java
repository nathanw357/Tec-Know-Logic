package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by nicol on 10/22/2015.
 */
public class ExampleTeleopTankMode extends OpMode {

    //  final static double ELBOW_MIN_RANGE  = 0.20;
    //  final static double ELBOW_MAX_RANGE  = 0.90;
    //  final static double CLAW_MIN_RANGE  = 0.20;
    //  final static double CLAW_MAX_RANGE  = 0.7;
    // final static double SHOULDER_MIN_RANGE  = 0;
    // final static double SHOULDER_MAX_RANGE  = 0.9;

    // position of the arm servo.
    //  double elbowPosition;

    // amount to change the arm servo position..
    //  double elbowDelta = 0.1;

    // position of the claw servo
    //  double clawPosition;

    // amount to change the claw servo position by
    // double clawDelta = 0.1;

    // position of the claw servo
//    double shoulderPosition;

    // amount to change the claw servo position by
//    double shoulderDelta = 0.1;

    DcMotor leftMotorFront;
    DcMotor leftMotorRear;
    DcMotor rightMotorFront;
    DcMotor rightMotorRear;
 //   Servo shoulder;
    int count=1;
    int Counter=0;

    @Override
    public void init() {

        Counter = 0;
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");
    //    rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
    //    rightMotorRear.setDirection(DcMotor.Direction.REVERSE);

   //     shoulder = hardwareMap.servo.get("servo_1");
        //     elbow = hardwareMap.servo.get("server_2");
        //     claw = hardwareMap.servo.get("servo_3");
        //     catcherR = hardwareMap.servo.get("server_4");
        //     catcherL = hardwareMap.servo.get("server_5");

        // assign the starting position of the wrist and claw
     //   shoulderPosition = 0.2;
        //     clawPosition = 0.2;
        //     elbowPosition = 0.2;
    }

    @Override
    public void loop() {

//        Servos

//      ShoulderTilt Variable is set to Left Stick's Y Axis
//        float ShoulderTilt = gamepad2.left_stick_y;

        // shoulderPosition += ShoulderTilt;


        //clip the position values so that they never exceed their allowed range.
  //      shoulderPosition = Range.clip(ShoulderTilt, SHOULDER_MIN_RANGE, SHOULDER_MAX_RANGE);
        // elbowPosition = Range.clip(elbowPosition, ELBOW_MIN_RANGE, ELBOW_MAX_RANGE);
        // clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

        // write position values to the elbow, shoulder and claw servo
    //    shoulder.setPosition(shoulderPosition);
        // claw.setPosition(clawPosition);
//      DC Motor code
        if (Counter >= 10) {

            Counter = 0;

        }
        float leftY = -gamepad1.left_stick_y;
        float left2Y = -gamepad1.left_stick_y;
        float rightY = gamepad1.right_stick_y;
        float right2Y = gamepad1.right_stick_y;

        if (Math.abs(leftY) == 0) {
            leftMotorFront.setPower(Math.signum(0));
            leftMotorRear.setPower(Math.signum(0));
        }

        else if (Math.abs(leftY) < 0.1){
            if (Counter == 0) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.2) {
            if (Counter == 0 || Counter == 5) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.3) {
            if (Counter == 3 || Counter == 6 || Counter == 9) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.4) {
            if (Counter == 2 || Counter == 4 || Counter == 6 || Counter == 8) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.5) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 7 || Counter == 9) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.6) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.7) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.8) {
            if (Counter == 1 || Counter == 3 || Counter == 4 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.9) {
            if (Counter == 1 || Counter == 2 || Counter == 3 || Counter == 4 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotorFront.setPower(Math.signum(leftY));
                leftMotorRear.setPower(Math.signum(left2Y));
            }
            else {
                leftMotorFront.setPower(Math.signum(0));
                leftMotorRear.setPower(Math.signum(0));
            }
        }
        else {

            leftMotorFront.setPower(Math.signum(leftY));
            leftMotorRear.setPower(Math.signum(left2Y));
        }

        if (Math.abs(rightY) == 0) {
            rightMotorFront.setPower(Math.signum(0));
            rightMotorRear.setPower(Math.signum(0));
        }

        else if (Math.abs(rightY) < 0.1){
            if (Counter == 0) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.2) {
            if (Counter == 0 || Counter == 5) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.3) {
            if (Counter == 3 || Counter == 6 || Counter == 9) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.4) {
            if (Counter == 2 || Counter == 4 || Counter == 6 || Counter == 8) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.5) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 7 || Counter == 9) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.6) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 7 || Counter == 9 || Counter == 0) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.7) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.8) {
            if (Counter == 1 || Counter == 3 || Counter == 4 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(rightY) < 0.9) {
            if (Counter == 1 || Counter == 2 || Counter == 3 || Counter == 4 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                rightMotorFront.setPower(Math.signum(rightY));
                rightMotorRear.setPower(Math.signum(right2Y));
            }
            else {
                rightMotorFront.setPower(Math.signum(0));
                rightMotorRear.setPower(Math.signum(0));
            }
        }
        else {

            rightMotorFront.setPower(Math.signum(rightY));
            rightMotorRear.setPower(Math.signum(right2Y));
        }
        telemetry.addData("Text", "** Robot Data**");
//        telemetry.addData("shoulder", "shoulder:  " + String.format("%.2f", shoulderPosition));
        //telemetry.addData("elbow", "elbow:  " + String.format("%.2f", elbowPosition));
        //telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
        telemetry.addData("left2Y tgt pwr", "left2Y  pwr: " + String.format("%.2f", left2Y));
        telemetry.addData("right2Y tgt pwr", "right2Y  pwr: " + String.format("%.2f", right2Y));
        telemetry.addData("Count: ", count++);
        //telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
        Counter++;
    }
}
