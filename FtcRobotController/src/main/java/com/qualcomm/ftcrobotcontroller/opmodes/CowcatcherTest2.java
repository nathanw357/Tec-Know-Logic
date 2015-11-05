package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by nicole on 10/15/2015.
 */
public class CowcatcherTest2 extends OpMode {

    //Servo at the base of the cowcatcher
    //Control up and down motion
    Servo cowCatcher;

    //The position of the servo. 0.2 is up, 0.5 is middle, and 0.9 is down.
    double cowPosition = 0.2;

    //A simplified position method.
    //1 is up, 2 is middle, and 3 is down
    float position = 1;

    //checks the states of the button and trigger
    int buttonState = 0;
    int triggerState = 0;


    public void init(){

        cowCatcher = hardwareMap.servo.get("cowCatcher");

    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean cowUpButton = gamepad1.right_bumper;
        float cowDownButton = gamepad1.right_trigger;


        if(cowUpButton){
            buttonState = 1;
        }
        if(!cowUpButton){
            if(buttonState == 1) {
                if (position == 3) {
                    cowPosition = 0.5;
                    position = 2;
                }
                else if (position == 2) {
                    cowPosition = 0.2;
                    position = 1;
                }
                else{
                    cowPosition = 0.9;
                    position = 3;
                }

                buttonState = 0;
            }
        }


        if(cowDownButton >= 0.9){
            triggerState = 1;
        }
        if(cowDownButton < 0.9){
            if(triggerState == 1){
                if(position == 1){
                    cowPosition = 0.5;
                    position = 2;
                }
                else if(position == 2){
                    cowPosition = 0.9;
                    position = 3;
                }
                else{
                    cowPosition = 0.2;
                    position = 1;
                }
            }
        }

        if(triggerState == 1 && buttonState == 1){
            triggerState = 0;
            buttonState = 0;
        }



        cowCatcher.setPosition(cowPosition);

        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("position", "position:  " + String.format("%.2f", position));




    }

}
