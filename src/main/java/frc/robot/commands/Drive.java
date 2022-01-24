package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**
* Drive
*/

public class Drive extends CommandBase {
  private Joystick driver;
  private Drivetrain drive;

  public Drive(Drivetrain drive, Joystick controller) {
    this.driver = controller;
    this.drive = drive;
    addRequirements(drive);
  }

  @Override
  public void execute() {
    double lAxis = -driver.getRawAxis(XboxController.Axis.kLeftY.value);
    double rAxis = -driver.getRawAxis(XboxController.Axis.kRightY.value);
    lAxis = (Math.abs(lAxis) < .01) ? 0 : lAxis;
    rAxis = (Math.abs(rAxis) < .01) ? 0 : rAxis;
    this.drive.drive(lAxis, rAxis);
  }
}
