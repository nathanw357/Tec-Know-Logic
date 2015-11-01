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

        float leftY = -gamepad1.left_stick_y;
        float rightY = gamepad1.right_stick_y;

        leftMotorFront.setPower(DcMotorPower(leftY));
        leftMotorRear.setPower(DcMotorPower(leftY));
        rightMotorFront.setPower(DcMotorPower(rightY));
        rightMotorRear.setPower(DcMotorPower(rightY));


        telemetry.addData("Text", "** Robot Data**");
        //telemetry.addData("shoulder", "shoulder:  " + String.format("%.2f", shoulderPosition));
        //telemetry.addData("elbow", "elbow:  " + String.format("%.2f", elbowPosition));
        //telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
        telemetry.addData("Count: ", count++);
        //telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
        Counter++;
    }
}
