package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.climber.Climber;

/**
 * Climber down.
 */

public class ClimberDown extends CommandBase {
    private Climber climber;

    public ClimberDown(Climber climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        this.climber.climberDown();
    }

    @Override
    public void end(boolean interrupt) {
        this.climber.climberInactive();
    }
}
