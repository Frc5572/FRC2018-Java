package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;

/**
 * Climber down.
 */

public class ClimberDown extends Command {
    private Climber climber;

    public ClimberDown(Climber climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        this.climber.setMotor(-1);
    }

    @Override
    public void end(boolean interrupt) {
        this.climber.setMotor(0);
    }
}
