package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Intake Subsystem
 */
public class Intake extends SubsystemBase {
    private IntakeIO io;
    private IntakeInputsAutoLogged inputs = new IntakeInputsAutoLogged();


    /**
     * Create Wrist Intake Subsystem
     */
    public Intake(IntakeIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);
    }

    /**
     * Set power of intake motors
     *
     * @param power power of motors from -1 to 1
     */
    public void setMotorVoltage(double power) {
        Logger.recordOutput("Intake/voltage", power);
        io.setMotorVoltage(power);
    }

    public Command intakeIn() {
        return Commands.runEnd(() -> setMotorVoltage(1), () -> setMotorVoltage(0), this);
    }

    public Command intakeOut() {
        return Commands.runEnd(() -> setMotorVoltage(-1), () -> setMotorVoltage(0), this);
    }
}
