package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


public class Encoder extends LinearOpMode {

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

        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        telemetry.clearData();

        telemetry.addData("Right Count", rightMotor.getCurrentPosition());
        telemetry.addData("Left Count", leftMotor.getCurrentPosition());

        waitForStart();

        //      Backwards start
        telemetry.clearData();
        leftMotor.setTargetPosition((int) MoveRobot(36, 2, 1));
        rightMotor.setTargetPosition((int) MoveRobot(36, 2, 2));

        telemetry.addData("text", "Set Target Position");

        leftMotor.setPower(-0.5);
        rightMotor.setPower(-0.5);
        leftMotor2.setPower(-0.5);
        rightMotor2.setPower(-0.5);

        telemetry.addData("text", "Set Power Backwards");

        while(rightMotor.getCurrentPosition() > (int) MoveRobot(36, 2, 1) + 10) {
            telemetry.addData("Right Count", rightMotor.getCurrentPosition());
            telemetry.addData("Left Count", leftMotor.getCurrentPosition());
            telemetry.addData("Motor Target", MoveRobot(36, 2, 1));
        }

        rightMotor.setPowerFloat();
        rightMotor2.setPowerFloat();
        leftMotor.setPowerFloat();
        leftMotor2.setPowerFloat();

        telemetry.addData("text", "Backwards End");

        telemetry.addData("Right Target Position", rightMotor.getTargetPosition());
        telemetry.addData("Right Current Position", rightMotor.getCurrentPosition());
        telemetry.addData("Left Current Position", leftMotor.getCurrentPosition());

        waitOneFullHardwareCycle();
        sleep(5000);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        waitOneFullHardwareCycle();
        sleep(1000);
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        leftMotor.setTargetPosition((int) MoveRobot(36, 1, 1));
        rightMotor.setTargetPosition((int) MoveRobot(36, 1, 2));

        telemetry.addData("text", "Set Target Position");

        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        leftMotor2.setPower(0.5);
        rightMotor2.setPower(0.5);

        telemetry.addData("text", "Set Power Forward");

        while(rightMotor.getCurrentPosition() < (int) MoveRobot(36, 1, 1) - 10) {
            telemetry.addData("Right Count", rightMotor.getCurrentPosition());
            telemetry.addData("Left Count", leftMotor.getCurrentPosition());
            telemetry.addData("Motor Target", MoveRobot(36, 1, 1));
        }

        rightMotor.setPower(0);
        rightMotor2.setPower(0);
        leftMotor.setPower(0);
        leftMotor2.setPower(0);

        telemetry.addData("text", "Forward End");

        telemetry.addData("Right Target Position", rightMotor.getTargetPosition());
        telemetry.addData("Right Current Position", rightMotor.getCurrentPosition());
        telemetry.addData("Left Current Position", leftMotor.getCurrentPosition());

        waitOneFullHardwareCycle();
        sleep(5000);
        telemetry.addData("Encoders", rightMotor.getCurrentPosition());
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    }

    private double MoveRobot(double DISTANCE, double DIRECTION, double MOTOR) {
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
}
