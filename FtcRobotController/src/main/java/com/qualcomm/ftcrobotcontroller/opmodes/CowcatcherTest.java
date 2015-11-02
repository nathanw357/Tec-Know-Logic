package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by nicole on 10/15/2015.
 */
public class CowcatcherTest extends OpMode {

    //Servo at the base of the cowcatcher
    //Control up and down motion
    Servo cowCatcher;

    double speed = 0.5;


    public CowcatcherTest() {

    }

    public void init(){

        cowCatcher = hardwareMap.servo.get("cowCatcher");



    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean cowUp = gamepad1.right_bumper;
        float cowDown = gamepad1.right_trigger;

        //Controls the direction of the cowcatcher and speed


        //Senses which button is pressed and sets direction/speed
        //If none pressed it stops motion
        if(gamepad1.right_bumper){
            if(speed != 0.9) {
                speed += 0.1;
            }
        }
        else if(gamepad1.right_trigger >= 0.9){
                speed = 0.2;

        }
        else{

            //speed = 0.5;
        }

        cowCatcher.setPosition(speed);





    }

}
