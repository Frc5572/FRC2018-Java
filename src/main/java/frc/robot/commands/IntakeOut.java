package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

/**
 * Intake out.
 */

public class IntakeOut extends Command {
    private final Intake intake;

    public IntakeOut(Intake subsystem) {
        this.intake = subsystem;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setMotorVoltage(-1);
    }

    @Override
    public void end(boolean interruptible) {
        intake.setMotorVoltage(0);
    }
}
