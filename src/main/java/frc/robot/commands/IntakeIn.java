package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;


/**
 * Intake in.
 */

public class IntakeIn extends Command {
    private final Intake intake;

    /**
     * Intake in Command.
     */
    public IntakeIn(Intake subsystem) {
        this.intake = subsystem;

        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setMotorVoltage(1);
    }

    @Override
    public void end(boolean interrupt) {
        intake.setMotorVoltage(0);
    }
}
