package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FinalDrive")
public class FinalDrive extends OpMode {
    private BOTT bot;

    @Override
    public void init() {
        bot = new BOTT(hardwareMap);
    }

    @Override
    public void loop() {
        bot.drivetrain(gamepad1);
        bot.intake(gamepad2);
        bot.shooter(gamepad2);

        telemetry.addData("Shoot: ", BOTT.shotPos.name());
        telemetry.update();
    }
}
