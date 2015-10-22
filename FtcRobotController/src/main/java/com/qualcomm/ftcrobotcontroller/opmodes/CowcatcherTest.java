package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by nicole on 10/15/2015.
 */
public class CowcatcherTest extends OpMode {

    //Dcmotors at the base of the cowcatcher on the robot
    //Control up and down motion
    DcMotor baseRight;
    DcMotor baseLeft;


    public CowcatcherTest() {

    }

    public void init(){

        baseRight = hardwareMap.dcMotor.get("motor_6");
        baseLeft = hardwareMap.dcMotor.get("motor_7");

        baseLeft.setDirection(DcMotor.Direction.REVERSE);

    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean cowUp = gamepad1.right_bumper;
        float cowDown = gamepad1.right_trigger;

        //Controls the direction of the cowcatcher and speed
        float speed = 0;

        //Senses which button is pressed and sets direction/speed
        //If none pressed it stops motion
        if(gamepad1.right_bumper){
            speed = 1;
        }
        else if(gamepad1.right_trigger >= 0.9){
            speed = -1;
        }
        else{
            speed = 0;
        }

        baseRight.setPower(speed);
        baseLeft.setPower(speed);






    }

}
