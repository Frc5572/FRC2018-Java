package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeOut extends CommandBase {
    private final Intake intake;

    public IntakeOut(Intake subsystem) {
        this.intake = subsystem;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.intakeOut();
    }

    @Override
    public void end(boolean interruptible) {
        intake.intakeInactive();
    }
}
