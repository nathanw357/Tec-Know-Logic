package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class EncoderTutorial extends LinearOpMode {

    //Declare Motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;

    @Override public void main() throws InterruptedException {

        //Initialize Motors
        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotor = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorRear = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotorRear = hardwareMap.dcMotor.get("rightMotorRear");

        //Reverse Motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRear.setDirection(DcMotor.Direction.REVERSE);

        //Set Motors to encoder
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        leftMotorRear.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotorRear.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        //WAit for game to start
        waitForStart();




    }

}
