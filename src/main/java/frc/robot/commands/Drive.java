package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

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
    double LeftYAxis = -driver.getRawAxis(XboxController.Axis.kLeftY.value);
    double RightYAxis = -driver.getRawAxis(XboxController.Axis.kRightY.value);
    LeftYAxis = (Math.abs(LeftYAxis) < .01) ? 0 : LeftYAxis;
    RightYAxis = (Math.abs(RightYAxis) < .01) ? 0 : RightYAxis;
    this.drive.drive(LeftYAxis, RightYAxis);
  }
}
