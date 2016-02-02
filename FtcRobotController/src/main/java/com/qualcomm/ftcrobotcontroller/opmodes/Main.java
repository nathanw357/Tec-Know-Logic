package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Main extends OpMode {
    final static double WRIST_MIN_RANGE = 0.20;
    final static double WRIST_MAX_RANGE = 0.7;

    //  Position of the arm servo
    double wristPosition;

//    Servo at the base of the bucket

    Servo bumperLeft;
    Servo bumperRight;

//    Servo at the base of the cowcatcher

    Servo cowCatcher;

//    Claw & Wrist Servos

    Servo wrist;
    Servo claw;

//    Elbow DcMotor

    DcMotor elbow;

//    Shoulder X & Y
    DcMotor ShoulderX;
    DcMotor ShoulderY;

//    Drive train motors

    DcMotor leftMotorFront;
    DcMotor leftMotorRear;
    DcMotor rightMotorFront;
    DcMotor rightMotorRear;

//    The position of the servo.

    double bumperLeftPosition = 0.2;
    double bumperRightPosition = 0.8;

//    The position of the servo.
//     0.3 is down, 0.6 is middle, and 0.9 is up.
    double cowPosition = 0.6;

//    A simplified position method.
//    1 is down, 2 is middle, and 3 is up
    int position = 2;


//    checks the states of the right bumper and right trigger
    int rightButtonState = 0;
    int rightTriggerState = 0;

//    checks the states of the left bumper and left trigger
    int leftButtonState = 0;
    int leftTriggerState = 0;


    public void init(){


//      Maps the bucket servos
        bumperLeft = hardwareMap.servo.get("bucketLeft");
        bumperRight = hardwareMap.servo.get("bucketRight");

//      Maps the cowcathcer's motor
        cowCatcher = hardwareMap.servo.get("cowCatcher");

//      Maps the drive train motors
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");

//      Maps the Elbow, Claw & Wrist motors
        elbow = hardwareMap.dcMotor.get("elbow");
        wrist = hardwareMap.servo.get("wrist");
        claw = hardwareMap.servo.get("claw");

//      Maps the Shoulder X & Y
        ShoulderX = hardwareMap.dcMotor.get("ShoulderX");
        ShoulderY = hardwareMap.dcMotor.get("ShoulderY");

//      Setting the motors in reverse
        leftMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        elbow.setDirection(DcMotor.Direction.REVERSE);
        ShoulderY.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {

//      The left bumper activates upward movement
//      The left trigger activates downward movement
        boolean bucketUpButton = gamepad1.left_bumper;
        float bucketDownButton = gamepad1.left_trigger;

//      Right bumper activates upward movement
//      The right trigger activates downward movement
        boolean cowUpButton = gamepad1.right_bumper;
        float cowDownButton = gamepad1.right_trigger;

//      Drive train controls
        double leftY = -gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;

//      Elbow & Wrist controls
        double rightY2 = gamepad2.right_stick_y;
        double rightX2 = gamepad2.right_stick_x;

//      ShoulderX & ShoulderY controls
        double leftY2  = -gamepad2.left_stick_y;
        double leftX2 = -gamepad2.left_stick_x;

//      Squares output of joysticks to create a smaller number; adds more control over DcMotor speed
        rightY2 = Math.pow(rightY2, 2) * Math.signum(rightY2);
        rightY = Math.pow(rightY, 2) * Math.signum(rightY);
        leftY = Math.pow(leftY, 2) * Math.signum(leftY);
        leftY2 = Math.pow(leftY2, 2) * Math.signum(leftY2);
        leftX2 = Math.pow(leftX2, 2) * Math.signum(leftX2);
        rightX2 = Math.pow(rightX2, 2) * Math.signum(rightX2);

//      Wrist position is set to rightY2's output after it transforms the number range from -1-1 to 0-1
        wristPosition = rightX2/2 + 0.5;

//      Clip the position values of wrist so that they never exceed their allowed range.
        wristPosition = Range.clip(wristPosition, WRIST_MIN_RANGE, WRIST_MAX_RANGE);

//      Write position values to the wrist & claw servo
//      Claw open and close
        if(gamepad2.right_bumper == true) {
            claw.setPosition(0.7);
        }

        else {
            claw.setPosition(0);
        }

//      Wrist Position
        wrist.setPosition(wristPosition);

//      Set power to the elbow & shoulder's X & Y
//      Elbow DC power
        elbow.setPower(-rightY2);

//      ShoulderX
        ShoulderX.setPower(leftX2);

//      ShoulderY
        ShoulderY.setPower(-leftY2);

//        Reads the right bumper and trigger to move the bucket-
        if(bucketUpButton){   //Checks if right bumper is pressed
            rightButtonState = 1;  //Sets buttonState to 1
        }
        else {
            if(rightButtonState == 1) {    //Checks the state of the right button
                  //Changes the position of the bucket
                    bumperLeftPosition = 0.1;  //The motors are mirrored so they get different values
                    bumperRightPosition = 0.9;


                rightButtonState = 0; //returns state to 0
            }
        }


        if(bucketDownButton >= 0.8){ //Checks to see if right trigger has been pressed
            rightTriggerState = 1;
        }
        else{
            if(rightTriggerState == 1){  //If trigger has been pressed and released
                    bumperLeftPosition = 1;   //Set bumper position to down
                    bumperRightPosition = 0;

                rightTriggerState = 0;
            }
        }

        //If both are pressed set both to 0
        if(rightTriggerState == 1 && rightButtonState == 1){
            rightTriggerState = 0;
            rightButtonState = 0;
        }

        if(cowUpButton){   //Checks to see if left bumper has been pressed
            leftButtonState = 1;   //Sets button state to 1
        }
        else {
            if(leftButtonState == 1) {   //Checks button state
                if (position == 1) {    //Checks current position of cowcatcher
                    cowPosition = 0.6;  //Sets cowcatcher's postion
                    position = 2;
                }
                else {                  //Checks current position of cowCatcher
                    cowPosition = 0.9;  //Sets cowcatcher's postion
                    position = 3;
                }

                leftButtonState = 0;    //Set button state to zero
            }
        }


        if(cowDownButton >= 0.9){    //Checks to see if left trigger has been pressed
            leftTriggerState = 1;    //Sets button state to 1
        }
        else{
            if(leftTriggerState == 1){
                if(position == 3){          //Checks current position of cowcatcher
                    cowPosition = 0.6;      //Sets cowcatcher's postion
                    position = 2;
                }
                else{                       //Checks current position of cowcatcher
                    cowPosition = 0.3 ;     //Sets cowcatcher's postion
                    position = 1;
                }

                leftTriggerState = 0;      //Set button state to zero
            }
        }

        //If both trigger and bumper pressed sets both state to 0
        if(leftTriggerState == 1 && leftButtonState == 1){
            leftTriggerState = 0;
            leftButtonState = 0;
        }

//      Setting position of bucket
        bumperLeft.setPosition(bumperLeftPosition);
        bumperRight.setPosition(bumperRightPosition);

//      Sets position of cowcatcher
        cowCatcher.setPosition(cowPosition);

//      Sets speed on the drive train
        leftMotorFront.setPower(leftY);
        leftMotorRear.setPower(leftY);
        rightMotorFront.setPower(rightY);
        rightMotorRear.setPower(rightY);


//      Telemetry data sent back to driver station
        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("leftbumper", "position:  " + String.format("%.2f", bumperLeftPosition));
        telemetry.addData("rightbumper", "position:" + String.format("%.2f", bumperRightPosition));
        telemetry.addData("leftMotors tgt pwr", "leftMotors  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightMotors tgt pwr", "rightMotors  pwr: " + String.format("%.2f", rightY));
        telemetry.addData("Elbow tgt pwr", "Elbow pwr: " + String.format("%.2f", rightX2));
        telemetry.addData("Wrist tgt pwr", "Wrist pwr: " + String.format("%.2f", wristPosition));
        telemetry.addData("ShoulderY tgt pwr", "ShoulderY pwr: " + String.format("%.2f", leftY2));
        telemetry.addData("ShoulderX tgt pwr", "ShoulderX pwr: " + String.format("%.2f", leftX2));
        telemetry.addData("Claw Position:", "Claw Position:" + String.format("%.2f", claw.getPosition()));




    }

}
