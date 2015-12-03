package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by nicole on 10/15/2015.
 */
public class Main extends OpMode {

    //Servo at the base of the bucket
    //Control up and down motion
    Servo bucketLeft;
    Servo bucketRight;

    //Servo at the base of the cowcatcher
    Servo cowCatcher;

    //Drive train motors
    DcMotor leftMotorFront;
    DcMotor leftMotorRear;
    DcMotor rightMotorFront;
    DcMotor rightMotorRear;

    //The position of the servo.
    double bucketLeftPosition = 0.3;
    double bucketRightPosition = 0.7;

    //The position of the servo.
    // 0.2 is down, 0.5 is middle, and 0.9 is up.
    double cowPosition = 0.2;

    //A simplified position method.
    //1 is down, 2 is middle, and 3 is up
    int position = 1;


    //checks the states of the right bumper and right trigger
    int rightButtonState = 0;
    int rightTriggerState = 0;

    //checks the states of the left bumper and left trigger
    int leftButtonState = 0;
    int leftTriggerState = 0;


    public void init(){


        //Maps the bucket servos
        bucketLeft = hardwareMap.servo.get("bucketLeft");
        bucketRight = hardwareMap.servo.get("bucketRight");

        //Maps the cowcathcer's motor
        cowCatcher = hardwareMap.servo.get("cowCatcher");

        //Maps the drive train motors
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");


        //Setting the motors in reverse
        leftMotorRear.setDirection(DcMotor.Direction.REVERSE);
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);

    }

    public void loop() {

        //The left bumper activates upward movement
        //The left trigger activates downward movement
        boolean bucketUpButton = gamepad1.left_bumper;
        float bucketDownButton = gamepad1.left_trigger;

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean cowUpButton = gamepad1.right_bumper;
        float cowDownButton = gamepad1.right_trigger;

        //Drive train controls
        double leftY = -gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;

        //Reads the right bumper and trigger to move the bucket
        if(bucketUpButton){
            rightButtonState = 1;   //Checks the state of the right button
        }
        else {
            if(rightButtonState == 1) {
                if(bucketLeftPosition >= 0.2){  //Changes the position of the bucket
                    bucketLeftPosition -= 0.1;  //The motors are mirrored so they get diffrent values
                    bucketRightPosition += 0.1;
                }

                rightButtonState = 0; //returns state to 0
            }
        }


        if(bucketDownButton >= 0.8){ //Checks to see if right trigger has been pressed
            rightTriggerState = 1;
        }
        else{
            if(rightTriggerState == 1){  //If trigger has been pressed and released
                if(bucketLeftPosition <= 0.9){  //changes the positon of the bucket
                    bucketLeftPosition += 0.1;
                    bucketRightPosition -= 0.1;
                }

                rightTriggerState = 0;
            }
        }

        if(rightTriggerState == 1 && rightButtonState == 1){
            rightTriggerState = 0;
            rightButtonState = 0;
        }

        if(cowUpButton){
            leftButtonState = 1;
        }
        else {
            if(leftButtonState == 1) {
                if (position == 1) {
                    cowPosition = 0.6;
                    position = 2;
                }
                else {
                    cowPosition = 0.9;
                    position = 3;
                }

                leftButtonState = 0;
            }
        }


        if(cowDownButton >= 0.9){
            leftTriggerState = 1;
        }
        else{
            if(leftTriggerState == 1){
                if(position == 3){
                    cowPosition = 0.6;
                    position = 2;
                }
                else{
                    cowPosition = 0.2;
                    position = 1;
                }

                leftTriggerState = 0;
            }
        }

        if(leftTriggerState == 1 && leftButtonState == 1){
            leftTriggerState = 0;
            leftButtonState = 0;
        }

        //Changes the speed of the motors
        leftY = Math.pow(leftY, 2) * Math.signum(leftY);
        rightY = Math.pow(rightY, 2) * Math.signum(rightY);


        //Setting postion of bucket
        bucketLeft.setPosition(bucketLeftPosition);
        bucketRight.setPosition(bucketRightPosition);

        //Sets postion of cowcatcher
        cowCatcher.setPosition(cowPosition);

        //Sets speed on the drive train
        leftMotorFront.setPower(leftY);
        leftMotorRear.setPower(leftY);
        rightMotorFront.setPower(rightY);
        rightMotorRear.setPower(rightY);

        telemetry.addData("leftbucket", "position:  " + String.format("%.2f", bucketLeftPosition));
        telemetry.addData("rightbucket", "position:" + String.format("%.2f", bucketRightPosition));





    }

}
