package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by nicol on 10/22/2015.
 */
public class ExampleTeleopTankMode extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    int count=1;
    int Counter=0;

    @Override
    public void init() {

        Counter = 0;
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {

        if (Counter >= 10) {

            Counter = 0;

        }
            float leftY = -gamepad1.left_stick_y;
            float rightY = -gamepad1.right_stick_y;
            float left2Y = -gamepad2.left_stick_y;
            float right2Y = -gamepad2.right_stick_y;

        if (Math.abs(leftY) == 0) {
            leftMotor.setPower(Math.signum(0));
        }

        else if (Math.abs(leftY) < 0.1){
            if (Counter == 0) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.2) {
            if (Counter == 0 || Counter == 5) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.3) {
            if (Counter == 3 || Counter == 6 || Counter == 9) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.4) {
            if (Counter == 2 || Counter == 4 || Counter == 6 || Counter == 8) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.5) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 7 || Counter == 9) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.6) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.7) {
            if (Counter == 1 || Counter == 3 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.8) {
            if (Counter == 1 || Counter == 3 || Counter == 4 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else if (Math.abs(leftY) < 0.9) {
            if (Counter == 1 || Counter == 2 || Counter == 3 || Counter == 4 || Counter == 5 || Counter == 6 || Counter == 7 || Counter == 9 || Counter == 0) {
                leftMotor.setPower(Math.signum(leftY));
            }
            else {
                leftMotor.setPower(Math.signum(0));
            }
        }
        else {

            leftMotor.setPower(Math.signum(leftY));

        }
        rightMotor.setPower(rightY);
        telemetry.addData("Text", "** Robot Data**");
        //telemetry.addData("shoulder", "shoulder:  " + String.format("%.2f", shoulderPosition));
        //telemetry.addData("elbow", "elbow:  " + String.format("%.2f", elbowPosition));
        //telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
        telemetry.addData("left2Y tgt pwr", "leftY  pwr: " + String.format("%.2f", left2Y));
        telemetry.addData("right2Y tgt pwr", "rightY  pwr: " + String.format("%.2f", right2Y));
        telemetry.addData("Count: ", count++);
        //telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
        Counter++;
    }
}
