package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Controller;

public class TelemetryDebuggerTwo extends OpMode {
    private Controller zoe = new Controller();
    private Controller kayle = new Controller();
    private DcMotorEx arm;
    private Servo shoulder;
    private Servo elbow;
    private CRServo wrist;
    private Servo claw;
    @Override
    public void init() {
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulder = hardwareMap.get(Servo.class, "shoulder");
        elbow = hardwareMap.get(Servo.class, "elbow");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");

        zoe.bind(
                () -> gamepad1.a,
                () -> arm.setPower(0.1)
        );
        zoe.bind(
                () -> gamepad1.b,
                () -> arm.setPower(-0.1)
        );
        zoe.bind(
                () -> gamepad1.dpadUpWasPressed(),
                () -> shoulder.setPosition(shoulder.getPosition() + 0.025)
        );
        zoe.bind(
                () -> gamepad1.dpadDownWasPressed(),
                () -> shoulder.setPosition(shoulder.getPosition() - 0.025)
        );
        zoe.bind(
                () -> gamepad1.dpadLeftWasPressed(),
                () -> elbow.setPosition(elbow.getPosition() + 0.025)
        );
        zoe.bind(
                () -> gamepad1.dpadRightWasPressed(),
                () -> elbow.setPosition(elbow.getPosition() - 0.025)
        );
        zoe.bind(
                () -> gamepad1.leftBumperWasPressed(),
                () -> wrist.setPower(0.1)
        );
        zoe.bind(
                () -> gamepad1.rightBumperWasPressed(),
                () -> wrist.setPower(-0.1)
        );

        zoe.bind(
                () -> gamepad1.rightTriggerWasPressed(),
                () -> claw.setPosition(claw.getPosition() + 0.025)
        );
        zoe.bind(
                () -> gamepad1.leftTriggerWasPressed(),
                () -> claw.setPosition(claw.getPosition() - 0.025)
        );
    }

    @Override
    public void loop() {
        zoe.update();
        telemetry.update();
        telemetry.addData("Arm Pos", arm.getCurrentPosition());
        telemetry.addData("Shoulder Pos", shoulder.getPosition());
        telemetry.addData("Elbow Pos", elbow.getPosition());
        telemetry.addData("Claw Pos", claw.getPosition());
    }
}
