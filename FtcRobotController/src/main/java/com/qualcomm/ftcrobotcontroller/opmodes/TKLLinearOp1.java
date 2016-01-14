package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TKLLinearOp1 extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    @Override
    public void runOpMode()  throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");


//        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        //--------Go Forward--------------
        telemetry.addData("text", "Moving Forward!");
        leftMotor.setPower(.75);
        rightMotor.setPower(.75);
        leftMotor2.setPower(.75);
        rightMotor2.setPower(.75);

        //wait for 5 seconds
        wait(5000);
        telemetry.clearData();

        //Halt Motors
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor2.setPower(0);
        rightMotor2.setPower(0);

        //--------- Right Turn -------------
        wait(500);

        //turn right
        telemetry.addData("text", "Turning Right!");
        leftMotor.setPower(.75);
        rightMotor.setPower(-0.75);
        leftMotor2.setPower(.75);
        rightMotor2.setPower(-0.75);

        //wait for 2.5 seconds
        wait(2500);
        telemetry.clearData();

        //Halt Motors
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor2.setPower(0);
        rightMotor2.setPower(0);

//      ---------- Forward -------------
//        Move forward onto and up mountain
        wait(500);
        telemetry.addData("text", "Onward!");
        leftMotor.setPower(.75);
        rightMotor.setPower(.75);
        leftMotor2.setPower(.75);
        rightMotor2.setPower(.75);

        //wait 6 seconds
        wait(6000);
        telemetry.clearData();

        //Halt Motors
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor2.setPower(0);
        rightMotor2.setPower(0);
        }


}
