package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by nicole on 10/15/2015.
 */
public class ButtonBucket extends OpMode {

    //Servo at the base of the cowcatcher
    //Control up and down motion
    Servo bucket;

    //The position of the servo.
    // 0.2 is up, 0.5 is middle, and 0.9 is down.
    double bucketPosition = 0.7;


    //checks the states of the button and trigger
    int buttonState = 0;
    int triggerState = 0;


    public void init(){

        bucket = hardwareMap.servo.get("bucket");

    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean bucketUpButton = gamepad1.left_bumper;
        float bucketDownButton = gamepad1.left_trigger;


        if(bucketUpButton){
            buttonState = 1;
        }
        else {
            if(buttonState == 1) {
                if(bucketPosition <= 0.6){
                    bucketPosition += 0.1;
                }

                buttonState = 0;
            }
        }


        if(bucketDownButton >= 0.5){
            triggerState = 1;
        }
        else{
            if(triggerState == 1){
                if(bucketPosition >= 0.2){
                    bucketPosition -= 0.1;
                }

                triggerState = 0;
            }
        }

        if(triggerState == 1 && buttonState == 1){
            triggerState = 0;
            buttonState = 0;
        }



        bucket.setPosition(bucketPosition);

        telemetry.addData("bucket", "position:  " + String.format("%.2f", bucketPosition));





    }

}
