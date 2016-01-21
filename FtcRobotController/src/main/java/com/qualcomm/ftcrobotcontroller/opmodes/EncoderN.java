package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


public class EncoderN extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    double LeftTargetPosition;
    double RightTargetPosition;

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");

        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


        waitForStart();

        MoveRobot(2, 10);

        waitOneFullHardwareCycle();
        sleep(2000);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        waitOneFullHardwareCycle();
        sleep(1000);
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }

    private double SetMotors(double DISTANCE, double DIRECTION, double MOTOR) {
//      Determine Counts based on distance
        final int ENCODER_CFR = 1440;            //Encoder Counts per Revolution
        final int GEAR_RATIO = 1;             //Gear Ratio
        final double WHEEL_DIAMETER = 3.1715;    //Wheel diameter

        double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        double ROTATIONS = DISTANCE / CIRCUMFERENCE;
        double COUNT = ENCODER_CFR * ROTATIONS * GEAR_RATIO;

//      Forward
        if(DIRECTION == 1) {
            LeftTargetPosition = 1 * COUNT;
            RightTargetPosition = 1 * COUNT;
        }

//      Reverse
        else if(DIRECTION == 2) {
            LeftTargetPosition = -1 * COUNT;
            RightTargetPosition = -1 * COUNT;
        }

//      Left
        else if(DIRECTION == 3) {
            LeftTargetPosition = -1 * COUNT;
            RightTargetPosition = 1 * COUNT;
        }

//      Right
        else if(DIRECTION == 4) {
            LeftTargetPosition = 1 * COUNT;
            RightTargetPosition = -1 * COUNT;
        }

        if(MOTOR == 2) {
            return RightTargetPosition;
        }

        else if(MOTOR == 1) {
            return LeftTargetPosition;
        }

        return RightTargetPosition;
    }

    private double MoveRobot( float direction, float rotations){

        leftMotor.setTargetPosition((int) SetMotors(36, direction, 1));
        rightMotor.setTargetPosition((int) SetMotors(36, direction, 2));

        telemetry.addData("text", "Set Target Position");

        leftMotor.setPower(-0.5);
        rightMotor.setPower(-0.5);
        leftMotor2.setPower(-0.5);
        rightMotor2.setPower(-0.5);

        telemetry.addData("text", "Set Power");

        while(rightMotor.getCurrentPosition() > (int) SetMotors(36, direction, 1) + rotations) {
            telemetry.addData("Right Count", rightMotor.getCurrentPosition());
            telemetry.addData("Left Count", leftMotor.getCurrentPosition());
            telemetry.addData("Motor Target", SetMotors(36, direction, 1));
        }

        rightMotor.setPowerFloat();
        rightMotor2.setPowerFloat();
        leftMotor.setPowerFloat();
        leftMotor2.setPowerFloat();

        return rotations;


    }



}