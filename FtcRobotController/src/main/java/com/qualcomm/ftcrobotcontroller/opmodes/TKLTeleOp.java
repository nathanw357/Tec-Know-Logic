package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TKLTeleOp extends OpMode {

    final static double ELBOW_MIN_RANGE  = 0.20;
    final static double ELBOW_MAX_RANGE  = 0.90;
    final static double CLAW_MIN_RANGE  = 0.20;
    final static double CLAW_MAX_RANGE  = 0.7;
    final static double WRIST_MIN_RANGE = 0.20;
    final static double WRIST_MAX_RANGE = 0.7;

//  Position of the arm servo
    double elbowPosition;

//  Amount to change the arm servo position by
    double elbowDelta = 0.1;

//  Position of the claw servo
    double clawPosition;

//  Amount to change the claw servo position by
    double clawDelta = 0.1;

//  Position of the Wrist Servo
    double wristPosition;

//  Amount to change the Wrist Servo by
    double wristDelta = 0.1;

// Setting the Dc motors
    DcMotor leftMotorFront;
    DcMotor leftMotorRear;
    DcMotor rightMotorFront;
    DcMotor rightMotorRear;
    DcMotor ShoulderX;
    DcMotor ShoulderY;

    //Setting the Serro motors
    Servo wrist;
    Servo elbow;
    Servo claw;
    Servo cowCatcher;
    int count=1;
    int Counter=0;

    @Override
    public void init() {

        Counter = 0;
//      DcMotors being linked to variable that links to DcMotor inputs on phone
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");
        ShoulderX = hardwareMap.dcMotor.get("ShoulderX");
        ShoulderY = hardwareMap.dcMotor.get("ShoulderY");

//      Servos being linked to variable that links to servo input on phone
        elbow = hardwareMap.servo.get("elbow");
        claw = hardwareMap.servo.get("claw");
        wrist = hardwareMap.servo.get("wrist");
        cowCatcher = hardwareMap.servo.get("cowCatcher");

//      Starting position for servos
        elbowPosition = 0.2;
        clawPosition = 0.2;
        wristPosition = 0.2;

    }

//    Dc Motor pulsing to alter speed

    private float DcMotorPower(float power) {

        int Step = Counter % 10;

        if (Math.abs(power) == 0) {
            return (Math.signum(power));
        }

        else if (Math.abs(power) < 0.1){
            if (Step == 0) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.2) {
            if (Step == 0 || Step == 5) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.3) {
            if (Step == 3 || Step == 6 || Step == 9) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.4) {
            if (Step == 2 || Step == 4 || Step == 6 || Step == 8) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.5) {
            if (Step == 1 || Step == 3 || Step == 5 || Step == 7 || Step == 9) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.6) {
            if (Step == 1 || Step == 3 || Step == 5 || Step == 7 || Step == 9 || Step == 0) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.7) {
            if (Step == 1 || Step == 3 || Step == 5 || Step == 6 || Step == 7 || Step == 9 || Step == 0) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.8) {
            if (Step == 1 || Step == 3 || Step == 4 || Step == 5 || Step == 6 || Step == 7 || Step == 9 || Step == 0) {
                return (Math.signum(power));
            }
        }
        else if (Math.abs(power) < 0.9) {
            if (Step == 1 || Step == 2 || Step == 3 || Step == 4 || Step == 5 || Step == 6 || Step == 7 || Step == 9 || Step == 0) {
                return (Math.signum(power));
            }
        }
        else {
            return (Math.signum(power));
        }

        return 0;
    }

    @Override
    public void loop() {

//      Gamepad1 sticks registered to variables
        float leftY = -gamepad1.left_stick_y;
        float rightY = gamepad1.right_stick_y;

//      Gamepad2 sticks registered to variables
        float rightY2 = gamepad2.right_stick_y;
        float leftY2 = -gamepad2.left_stick_y;
        float leftX2 = -gamepad2.left_stick_x;


//      Clip the position values so that they never exceed their allowed range.
        elbowPosition = Range.clip(elbowPosition, ELBOW_MIN_RANGE, ELBOW_MAX_RANGE);
        clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
        wristPosition = Range.clip(wristPosition, WRIST_MIN_RANGE, WRIST_MAX_RANGE);

//      Write position values to the elbow, shoulder and claw servo
        if(gamepad2.right_bumper = true) {
            claw.setPosition(1);
        }

        else {
            claw.setPosition(0);
        }

//        Wrist Position
        if(gamepad2.right_stick_y >= 0) {

        }
        else {

        }

//      Elbow position

        if(gamepad2.right_stick_x >= 0) {

        }
        else {

        }
        wrist.setPosition(wristPosition);
        elbow.setPosition(elbowPosition);


//      DcMotor Code to set power
        leftMotorFront.setPower(DcMotorPower(leftY));
        leftMotorRear.setPower(DcMotorPower(leftY));
        rightMotorFront.setPower(DcMotorPower(rightY));
        rightMotorRear.setPower(DcMotorPower(rightY));
        ShoulderX.setPower(DcMotorPower(leftX2));

//      Sends robot data back to driver station
        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("elbow", "elbow:  " + String.format("%.2f", elbowPosition));
        telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
        telemetry.addData("wrist", "wrist:  " + String.format("%.2f", wristPosition));
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
        telemetry.addData("leftY2 tgt pwr", "leftY2 pwr: " + String.format("%.2f", leftY2));
        telemetry.addData("rightY2 tgt pwr", "rightY2 pwr: " + String.format("%.2f", rightY2));
        telemetry.addData("Count: ", count++);

        Counter++;
    }
}
