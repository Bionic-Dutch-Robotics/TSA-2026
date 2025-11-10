package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {
    public DcMotorEx shooter = null;
    public Servo transfer = null;
    public Shooter (HardwareMap hwMap) {
        shooter = hwMap.get(DcMotorEx.class, "shooter");
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        transfer = hwMap.get(Servo.class, "push");
    }

    public void shoot() {
        shooter.setPower(0.75);
    }

    public void eject() {
        shooter.setPower(-0.05);
    }

    public void stop() {
        shooter.setPower(0);
    }
    public void idle() {
        shooter.setPower(0.43);
    }

    public void lob() {
        shooter.setPower(0.525);
    }

    public void transfer() {
        transfer.setPosition(0.845);
    }
    public void reload() {
        transfer.setPosition(1);
    }
}
