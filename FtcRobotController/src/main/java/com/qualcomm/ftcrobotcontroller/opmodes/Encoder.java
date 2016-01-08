package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by chris on 12/6/2015.
 */
public class Encoder extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    double count;
    String step = "";
    final static int ENCODER_CFR = 1440;
    final static double GEAR_RATIO = 1;
    final static int WHEEL_DIAMETER = 4;
    final static int DISTANCE = 36;

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final static double ROTATIONS = DISTANCE / CIRCUMFERENCE;
    final static double COUNTS = ENCODER_CFR * ROTATIONS *GEAR_RATIO;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");

        //rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        leftMotor2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void start() {
        leftMotor.setTargetPosition((int) COUNTS);
        rightMotor.setTargetPosition((int) COUNTS);
        leftMotor2.setTargetPosition((int) COUNTS);
        rightMotor2.setTargetPosition((int) COUNTS);
        step = "TargetPosition";
        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        leftMotor2.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor2.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        step = "Run To Position";
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        leftMotor2.setPower(0.5);
        rightMotor2.setPower(0.5);
        step = "Set Power";


        while(rightMotor.isBusy());

        leftMotor.setPowerFloat();
        leftMotor2.setPowerFloat();
            rightMotor.setPowerFloat();
            rightMotor2.setPowerFloat();
        step = "Set Power Float";
    }

    @Override
    public void loop() {


        telemetry.addData("Motor Target", COUNTS);
        telemetry.addData("LeftPosition", leftMotor.getCurrentPosition());
        telemetry.addData("Right Position", rightMotor.getCurrentPosition());
        telemetry.addData("Count", count++);
        telemetry.addData("Step", step);
    }
}
