package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by nicole on 10/15/2015.
 */
public class CowcatcherTest extends OpMode {

    //Dcmotors at the base of the cowcatcher on the robot
    DcMotor baseRight;
    DcMotor baseLeft;

    //Servos controlling the angle of the front part of the cowcatcher
    Servo topRight;
    Servo topLeft;

    public CowcatcherTest() {

    }

    public void init(){

        baseRight = hardwareMap.dcMotor.get("motor_6");
        baseLeft = hardwareMap.dcMotor.get("motor_7");

        topRight = hardwareMap.servo.get("server_4");
        topLeft = hardwareMap.servo.get("server_5");

        baseLeft.setDirection(DcMotor.Direction.REVERSE);

    }

    public void loop() {

        //right bumper activates upward movement
        //The right trigger activates downward movement
        boolean cowUp = gamepad1.right_bumper;
        float cowDown = gamepad1.right_trigger;

        float speed = 0;
        float angle = 0.2f;

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
