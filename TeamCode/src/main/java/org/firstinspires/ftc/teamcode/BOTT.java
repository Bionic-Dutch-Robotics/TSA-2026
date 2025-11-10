package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

public class BOTT {

    public enum ShotPos {
        FAR,
        CLOSE,
        EJECT
    }
    Follower fw;
    Intake intake;
    public static ShotPos shotPos;
    boolean runIntake;
    Shooter shooter;
    boolean runShooter;
    double forwardPower, strafePower, turnPower;
    public BOTT(HardwareMap hwMap) {
        fw = Constants.createFollower(hwMap);
        fw.setStartingPose(
                new Pose(0,0,0)
        );

        intake = new Intake(hwMap);
        shooter = new Shooter(hwMap);

        fw.startTeleopDrive(true);

        runIntake = false;
        runShooter = false;

        shotPos = ShotPos.FAR;
    }

    public void drivetrain(Gamepad gp) {
        fw.update();

        forwardPower = -gp.left_stick_y;
        if (Math.abs(forwardPower) < 0.05) {
            forwardPower = 0;
        }

        strafePower = gp.left_stick_x;
        if (Math.abs(strafePower) < 0.05) {
            strafePower = 0;
        }

        turnPower = gp.right_stick_x;
        if (Math.abs(turnPower) < 0.05) {
            turnPower = 0;
        }


        fw.setTeleOpDrive(
                forwardPower,
                -strafePower,
                -turnPower,
                true
        );
    }

    public void intake(Gamepad gp) {
        if (gp.aWasPressed()) {
            runIntake = !runIntake;
        }

        if (gp.b) {
            intake.eject();
        }
        else if (runIntake) {
            intake.intake();
        }
        else {
            intake.stop();
        }
    }

    public void shooter(Gamepad gp) {
        if (gp.dpad_up) {
            shooter.reload();
        }
        else {
            shooter.transfer();
        }


        if (gp.yWasPressed()) {
            runShooter = !runShooter;
        }

        if (runShooter) {
            shooter.lob();
            shotPos = ShotPos.FAR;
        }
        else if (gp.x) {
            shooter.eject();
            shotPos = ShotPos.EJECT;
        }
        else {
            shooter.idle();
            shotPos = ShotPos.CLOSE;
        }
    }
}
