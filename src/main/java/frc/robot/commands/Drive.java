package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.Drivetrain;

/**
 * Drive.
 */

public class Drive extends CommandBase {
    private Joystick driver;
    private Drivetrain drive;

    /**
     * Drive again.
     */

    public Drive(Drivetrain drive, Joystick controller) {
        this.driver = controller;
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        double laxis = -driver.getRawAxis(XboxController.Axis.kLeftY.value);
        double raxis = -driver.getRawAxis(XboxController.Axis.kRightY.value);
        laxis = (Math.abs(laxis) < .01) ? 0 : laxis;
        raxis = (Math.abs(raxis) < .01) ? 0 : raxis;

        this.drive.setMotor(laxis, raxis);
    }
}
