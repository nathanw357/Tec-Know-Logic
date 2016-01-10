package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


public class Encoder extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");

        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        telemetry.addData("text", "Run To Position");

        leftMotor.setTargetPosition((int) COUNTS(36));
        rightMotor.setTargetPosition((int) COUNTS(36));

        telemetry.addData("text", "Set Target Position");

        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        leftMotor2.setPower(0.5);
        rightMotor2.setPower(0.5);
        telemetry.addData("text", "Set Power Forward");

        while(rightMotor.getCurrentPosition() < (int) COUNTS(36) - 10) {

            telemetry.addData("count", rightMotor.getCurrentPosition());
            telemetry.addData("Motor Target", COUNTS(36));
        }

        rightMotor.setPowerFloat();
        rightMotor2.setPowerFloat();
        leftMotor.setPowerFloat();
        leftMotor2.setPowerFloat();

        telemetry.addData("text", "Set Power Float");

        telemetry.addData("text", "End");
    }

    private double COUNTS(double DISTANCE) {
        final int ENCODER_CFR = 1440;            //Encoder Counts per Revolution
        final int GEAR_RATIO = 1;             //Gear Ratio
        final double WHEEL_DIAMETER = 3.1715;    //Wheel diameter

        double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        double ROTATIONS = DISTANCE / CIRCUMFERENCE;
        double COUNT = ENCODER_CFR * ROTATIONS * GEAR_RATIO;

        return COUNT;

    }
}
