package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.concurrent.TimeUnit;

/**
 * Created by nicole on 10/15/2015.
 */
public class CowcatcherTest extends OpMode {

    //Servo at the base of the cowcatcher
    //Control up and down motion
    Servo cowCatcher;

    double cowPosition = 0.9;
    float position = 1;

    public CowcatcherTest() {

    }

    public void init(){

        cowCatcher = hardwareMap.servo.get("cowCatcher");


    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean cowUp = gamepad1.right_bumper;
        boolean cowMid = gamepad1.left_bumper;
        float cowDown = gamepad1.right_trigger;

        //Controls the direction of the cowcatcher and speed


        //Senses which button is pressed and sets direction/speed
        //If none pressed it stops motion

       /* if(cowUp == true) {
            cowPosition = 0.2;
        }
        else if (cowMid == true){
            cowPosition = 0.5;
        }


        if(cowDown >= 0.9){
            cowPosition = 0.8;
        }*/

        if (cowUp == true) {

            if (position == 2) {
                cowPosition = 0.2;
                position = 1;
            }
            if (position == 3) {
                cowPosition = 0.5;
                position = 2;
            }
        }

        if (cowDown >= 0.9) {
            if (position == 1) {
                cowPosition = 0.5;
                position = 2;
            }
            if (position == 2) {
                cowPosition = 0.9;
                position = 3;
            }

        }

        cowCatcher.setPosition(cowPosition);

        /*try {
            Thread.sleep(100);                 //100 milliseconds is 0.1 second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }*/

        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("position", "position:  " + String.format("%.2f", position));




    }

}
