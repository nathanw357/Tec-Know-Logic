package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Nathan on 12/27/2015.
 */
public class TKLLinear extends OpMode {


    DcMotor leftMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor;
    DcMotor rightMotor2;


    @Override
    public void init() {

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotor2");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotor2");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);

    }

    @Override
    public void start(){

        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        leftMotor2.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor2.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(1.0);
        leftMotor2.setPower(1.0);
        rightMotor.setPower(1.0);
        rightMotor2.setPower(1.0);

        leftMotor.setTargetPosition(0);
        rightMotor.setTargetPosition(0);
    }

    @Override
    public void loop() {

        leftMotor.setTargetPosition((int) COUNTS(2));

    }

    @Override

    public void stop() {



    }

    private double COUNTS(double DISTANCE) {
        final int ENCODER_CFR = 1440;            //Encoder Counts per Revolution
        final int GEAR_RATIO = 1;             //Gear Ratio
        final double WHEEL_DIAMETER = 3.1715;    //Wheel diameter

        final double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        final double ROTATIONS = DISTANCE / CIRCUMFERENCE;
        final double COUNT = ENCODER_CFR * ROTATIONS * GEAR_RATIO;

        return COUNT;

    }
}

