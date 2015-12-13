package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Encoder extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    final static int ENCODER_CFR = 1440;
    final static double GEAR_RATIO = 1;
    final static double WHEEL_DIAMETER = 3.1715;
    final static int DISTANCE = 1;

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final static double ROTATIONS = DISTANCE / CIRCUMFERENCE;
    final static double DriveOneInch = ENCODER_CFR * ROTATIONS *GEAR_RATIO;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
   //     leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
   //     rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");


    //    rightMotor2.setDirection(DcMotor.Direction.REVERSE);
     //   leftMotor2.setDirection(DcMotor.Direction.REVERSE);
           leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    //    leftMotor2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    //    rightMotor2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void start() {

        leftMotor.setTargetPosition((int) DriveOneInch * 20);
        rightMotor.setTargetPosition((int) DriveOneInch * 20);
//        leftMotor2.setTargetPosition((int) DriveOneInch * 20);
// rightMotor2.setTargetPosition((int) DriveOneInch * 20);

        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        //      leftMotor2.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        //    rightMotor2.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        if(leftMotor.isBusy()) {
            leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);
        } else {
            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
        }







//        while(rightMotor.isBusy());
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);
//        leftMotor2.setPower(0.5);
      //  rightMotor2.setPower(0.5);

    }

    @Override
    public void loop() {

        telemetry.addData("Motor Target", DriveOneInch);
        telemetry.addData("Left Position", leftMotor.getCurrentPosition());
        telemetry.addData("Right Position", rightMotor.getCurrentPosition());
        telemetry.addData("Is Right Motor Busy", rightMotor.isBusy());
        telemetry.addData("Is Left Motor Busy", leftMotor.isBusy());
    }
}
