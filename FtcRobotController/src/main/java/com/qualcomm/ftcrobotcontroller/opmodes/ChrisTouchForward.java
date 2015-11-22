package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class ChrisTouchForward extends OpMode {

    DcMotor leftFront;
    DcMotor leftRear;
    DcMotor rightFront;
    DcMotor rightRear;
    TouchSensor touchSensor;

    @Override
    public void init() {

        //Left Side Motors
        leftFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftRear = hardwareMap.dcMotor.get("leftMotorRear");

        //Right Side Motors
        rightRear = hardwareMap.dcMotor.get("rightMotorRear");
        rightFront = hardwareMap.dcMotor.get("rightMotorFront");

        //Reversing of right side
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        touchSensor = hardwareMap.touchSensor.get("sensor_touch");
    }

    @Override
    public void loop() {

        if(touchSensor.isPressed()) {
            //stop Motors
            leftFront.setPower(0.0);
            rightFront.setPower(0.0);
            leftRear.setPower(0.0);
            rightRear.setPower(0.0);
        } else {
            rightFront.setPower(0.5);
            leftFront.setPower(0.5);
            rightRear.setPower(0.5);
            leftRear.setPower(0.5);
        }
        telemetry.addData("isPressed", String.valueOf(touchSensor.isPressed()));

    }
}