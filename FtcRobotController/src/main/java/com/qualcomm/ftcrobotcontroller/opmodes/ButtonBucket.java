package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by nicole on 10/15/2015.
 */
public class ButtonBucket extends OpMode {

    //Servo at the base of the cowcatcher
    //Control up and down motion
    Servo bucketLeft;
    Servo bucketRight;

    //The position of the servo.
    // 0.2 is up, 0.5 is middle, and 0.9 is down.
    double bucketLeftPosition = 0.3;
    double bucketRightPosition = 0.7;


    //checks the states of the button and trigger
    int rightButtonState = 0;
    int rightTriggerState = 0;


    public void init(){

        bucketLeft = hardwareMap.servo.get("bucketLeft");
        bucketRight = hardwareMap.servo.get("bucketRight");

    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean bucketUpButton = gamepad1.left_bumper;
        float bucketDownButton = gamepad1.left_trigger;


        if(bucketUpButton){
            rightButtonState = 1;
        }
        else {
            if(rightButtonState == 1) {
                if(bucketLeftPosition >= 0.2){
                    bucketLeftPosition -= 0.1;
                    bucketRightPosition += 0.1;
                }

                rightButtonState = 0;
            }
        }


        if(bucketDownButton >= 0.8){
            rightTriggerState = 1;
        }
        else{
            if(rightTriggerState == 1){
                if(bucketLeftPosition <= 0.9){
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



        bucketLeft.setPosition(bucketLeftPosition);
        bucketRight.setPosition(bucketRightPosition);

        telemetry.addData("left", "position:  " + String.format("%.2f", bucketLeftPosition));
        telemetry.addData("right", "position:" + String.format("%.2f", bucketRightPosition));





    }

}
