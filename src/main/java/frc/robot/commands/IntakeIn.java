package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

/**
* Intake in.
*/

public class IntakeIn extends CommandBase {
  private final Intake intake;

  public IntakeIn(Intake subsystem) {
    this.intake = subsystem;
    addRequirements(intake);
  }

  @Override
  public void execute() {
    intake.intakeIn();
  }

  @Override
  public void end(boolean interrupt) {
    intake.intakeInactive();
  }
}
