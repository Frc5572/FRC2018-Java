package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
* Elevator up.
*/

public class ElevatorUp extends CommandBase {
  private Elevator elevator;

  public ElevatorUp(Elevator elevator) {
    this.elevator = elevator;
    addRequirements(elevator);
  }

  @Override
  public void execute() {
    this.elevator.elevatorUp();
  }

  @Override
  public void end(boolean interrupt) {
    this.elevator.elevatorInactive();
  }
}
