package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ChrisCircleTest extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotor2;
    DcMotor rightMotor2;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotor = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2 = hardwareMap.dcMotor.get("leftMotorRear");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotorRear");
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        for(int i=0; i<4; i++) {
            leftMotor.setPower(0.75);
            leftMotor2.setPower(0.75);
            rightMotor.setPower(0.25);
            rightMotor2.setPower(0.25);
            sleep(5000);

            leftMotor.setPowerFloat();
            leftMotor2.setPowerFloat();
            rightMotor.setPowerFloat();
            rightMotor2.setPowerFloat();
            sleep(2000);

            leftMotor.setPower(0.25);
            leftMotor2.setPower(0.25);
            rightMotor.setPower(0.75);
            rightMotor2.setPower(0.75);
            sleep(5000);
        }

        leftMotor.setPowerFloat();
        leftMotor2.setPowerFloat();
        rightMotor.setPowerFloat();
        rightMotor2.setPowerFloat();
    }

}
