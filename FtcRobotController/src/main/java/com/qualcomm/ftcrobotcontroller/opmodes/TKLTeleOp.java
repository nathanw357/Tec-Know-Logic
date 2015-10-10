package com.qualcomm.ftcrobotcontroller.opmodes;


/**
 * Imports - Used to make references to other code in the project; adding functionality
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 */
public class TKLTeleOp extends OpMode {

    final static double ELBOW_MIN_RANGE  = 0.20;
    final static double ELBOW_MAX_RANGE  = 0.90;
    final static double CLAW_MIN_RANGE  = 0.20;
    final static double CLAW_MAX_RANGE  = 0.7;
    final static double SHOULDER_MIN_RANGE  = 0.20;
    final static double SHOULDER_MAX_RANGE  = 0.7;

    // position of the arm servo.
    double elbowPosition;

    // amount to change the arm servo position.
    double elbowDelta = 0.1;

    // position of the claw servo
    double clawPosition;

    // amount to change the claw servo position by
    double clawDelta = 0.1;

    // position of the claw servo
    double shoulderPosition;

    // amount to change the claw servo position by
    double shoulderDelta = 0.1;

//    Setting of Variables to DcMotor / Servo
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    DcMotor motorArmBase;
    DcMotor motorCatcherRight;
    DcMotor motorCatcherLeft;

    Servo shoulder;
    Servo elbow;
    Servo claw;
    Servo catcherR;
    Servo catcherL;


    /**
     * Constructor
     */
    public TKLTeleOp() {

    }

    /*
     * Code to run when the op mode is first enabled goes here
     * Used for assigning motors to variables, setting start positions
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {

//        Right Tread DC Motors
        motorRight1 = hardwareMap.dcMotor.get("motor_3");
        motorRight2 = hardwareMap.dcMotor.get("motor_4");
//        Left Tread DC Motors
        motorLeft1 = hardwareMap.dcMotor.get("motor_1");
        motorLeft2 = hardwareMap.dcMotor.get("motor_2");
//        Rotating Arm Base Motor
        motorArmBase = hardwareMap.dcMotor.get("motor_5");
//        Catcher
        motorCatcherRight = hardwareMap.dcMotor.get("motor_6");
        motorCatcherLeft = hardwareMap.dcMotor.get("motor_7");
        motorLeft1.setDirection(DcMotor.Direction.REVERSE);
        motorLeft2.setDirection(DcMotor.Direction.REVERSE);

        shoulder = hardwareMap.servo.get("servo_1");
        elbow = hardwareMap.servo.get("server_2");
        claw = hardwareMap.servo.get("servo_3");
        catcherR = hardwareMap.servo.get("server_4");
        catcherL = hardwareMap.servo.get("server_5");

        // assign the starting position of the wrist and claw
        shoulderPosition = 0.2;
        clawPosition = 0.2;
        elbowPosition = 0.2;

    }

    /*
     * This code is called repeatedly in a loop
     * It’s used for detecting controls and sending the Robot’s data to the driver station
     */
    @Override
    public void loop() {


/*
* Code for Controller 1
*
*/

        float throttle = -gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;
        float right = throttle - direction;
        float left = throttle + direction;

        // clip the right/left values so that the values never exceed +/- 1
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);

        // write the values to the motors
        motorRight1.setPower(right);
        motorRight2.setPower(right);
        motorLeft1.setPower(left);
        motorLeft2.setPower(left);



/*
* Code for Controller 2
*
*/
        float ShoulderTilt = gamepad2.left_stick_y;
        float ShoulderRotation = gamepad2.left_stick_x;

        float ElbowTilt = gamepad2.right_stick_y;
        float ClawTilt = gamepad2.right_stick_x;

        // update the position of the claw
        if (gamepad2.left_bumper) {
            clawPosition += clawDelta;
        }

        if (gamepad2.right_bumper) {
            clawPosition -= clawDelta;
        }

        // clip the position values so that they never exceed their allowed range.
        shoulderPosition = Range.clip(elbowPosition, SHOULDER_MIN_RANGE, SHOULDER_MAX_RANGE);
        elbowPosition = Range.clip(elbowPosition, ELBOW_MIN_RANGE, ELBOW_MAX_RANGE);
        clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

        // write position values to the elbow, shoulder and claw servo
        shoulder.setPosition(shoulderPosition);
        claw.setPosition(clawPosition);


/*
* Code to send back the robot’s positioning to the driver station
*
*/

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("shoulder", "shoulder:  " + String.format("%.2f", shoulderPosition));
        telemetry.addData("elbow", "elbow:  " + String.format("%.2f", elbowPosition));
        telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
        telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));


    }

    /*
    * Code to run when the op mode is first disabled goes here
    *
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }

/*
	 * This method scales the joystick input so for low joystick values, the 
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */

    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}
