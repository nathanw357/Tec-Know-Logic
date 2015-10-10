package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by owner on 10/9/2015.
 */
public class Clawtest {

    // update the position of the claw
    if (gamepad2.left_bumper && gamepad2.right_bumper) {    /* this opens the claw?*/
        clawPosition += clawDelta;
    }

    if (gamepad2.right_/*trigger*/ && gamepad.left_/*trigger*/) {      /* this closes the claw?*/
        clawPosition -= clawDelta;
    }

    // clip the position values so that they never exceed their allowed range.
    armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
    clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

    // write position values to the elbow, shoulder and claw servo
    shoulder.setPosition(shoulderPosition);
    claw.setPosition(clawPosition);
}
n