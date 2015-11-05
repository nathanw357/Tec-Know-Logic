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

        //right bumper goes to position 1
        boolean cowUp = gamepad1.right_bumper;
        //left bumper goes to position 2
        boolean cowMid = gamepad1.left_bumper;
        //right trigger goes to position 3
        float cowDown = gamepad1.right_trigger;



        //Senses which button is pressed and sets position

        if(cowUp == true) {
            cowPosition = 0.2;
        }
        else if (cowMid == true){
            cowPosition = 0.5;
        }
        else if(cowDown >= 0.9){
            cowPosition = 0.9;
        }

        telemetry.addData("Text", "** Robot Data**");
        telemetry.addData("position", "position:  " + String.format("%.2f", position));




    }

}
