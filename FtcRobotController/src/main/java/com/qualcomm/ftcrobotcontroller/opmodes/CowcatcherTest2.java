package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by nicole on 10/15/2015.
 */
public class CowcatcherTest2 extends OpMode {

    //Servo at the base of the cowcatcher
    Servo cowCatcher;

    //The position of the servo.
    // 0.2 is down, 0.5 is middle, and 0.9 is up.
    double cowPosition = 0.2;

    //A simplified position method.
    //1 is down, 2 is middle, and 3 is up
    int position = 1;

    //checks the states of the button and trigger
    int leftButtonState = 0;
    int leftTriggerState = 0;


    public void init(){

        cowCatcher = hardwareMap.servo.get("cowCatcher");

    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean cowUpButton = gamepad1.right_bumper;
        float cowDownButton = gamepad1.right_trigger;


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



        cowCatcher.setPosition(cowPosition);





    }

}
