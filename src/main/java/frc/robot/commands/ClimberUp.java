package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * Climber up.
 */

public class ClimberUp extends CommandBase {
    private Climber climber;

    public ClimberUp(Climber climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        this.climber.climberUp();
    }

    @Override
    public void end(boolean interrupt) {
        this.climber.climberInactive();
    }
}
