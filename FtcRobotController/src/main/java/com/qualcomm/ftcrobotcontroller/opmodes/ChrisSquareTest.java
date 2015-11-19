package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class ChrisSquareTest extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2 = hardwareMap.dcMotor.get("left_drive2");
        rightMotor2 = hardwareMap.dcMotor.get("right_drive2");
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        for(int i=0; i<4; i++) {
            leftMotor.setPower(1.0);
            leftMotor2.setPower(1.0);
            rightMotor.setPower(1.0);
            rightMotor2.setPower(1.0);
            sleep(1000);

            leftMotor.setPower(0.5);
            leftMotor2.setPower(0.5);
            rightMotor.setPower(-0.5);
            rightMotor2.setPower(-0.5);
            sleep(1000);
        }

        leftMotor.setPowerFloat();
        leftMotor2.setPowerFloat();
        rightMotor.setPowerFloat();
        rightMotor2.setPowerFloat();
    }

}
