package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by nicol on 10/22/2015.
 */
public class ExampleTeleopTankMode extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    int count=1;

    @Override
    public void init() {

        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {

            float leftY = -gamepad1.left_stick_y;
            float rightY = -gamepad1.right_stick_y;
            float left2Y = -gamepad2.left_stick_y;
            float right2Y = -gamepad2.right_stick_y;

            leftMotor.setPower(leftY);
            rightMotor.setPower(rightY);

        telemetry.addData("Text", "** Robot Data**");
        //telemetry.addData("shoulder", "shoulder:  " + String.format("%.2f", shoulderPosition));
        //telemetry.addData("elbow", "elbow:  " + String.format("%.2f", elbowPosition));
        //telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
        telemetry.addData("leftY tgt pwr", "leftY  pwr: " + String.format("%.2f", leftY));
        telemetry.addData("rightY tgt pwr", "rightY  pwr: " + String.format("%.2f", rightY));
        telemetry.addData("left2Y tgt pwr", "leftY  pwr: " + String.format("%.2f", left2Y));
        telemetry.addData("right2Y tgt pwr", "rightY  pwr: " + String.format("%.2f", right2Y));
        telemetry.addData("Count: ", count++);
        //telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));

    }
}
