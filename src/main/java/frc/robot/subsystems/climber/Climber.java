package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Climber subsystem.
 */

public class Climber extends SubsystemBase {
    private ClimberIO io;
    private ClimberIOInputsAutoLogged inputs = new ClimberIOInputsAutoLogged();


    /**
     * Create Wrist Intake Subsystem
     */
    public Climber(ClimberIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Climber", inputs);
    }

    /**
     * Set power of intake motors
     *
     * @param power power of motors from -1 to 1
     */
    public void setMotor(double power) {
        Logger.recordOutput("Climber/voltage", power);
        io.setMotorVoltage(power);
    }

    /**
     * Climber up command
     *
     * @return Command
     */
    public Command climberUp() {
        return Commands.runEnd(() -> setMotor(1), () -> setMotor(0), this);
    }

    /**
     * Climber down command
     *
     * @return Command
     */
    public Command climberDown() {
        return Commands.runEnd(() -> setMotor(-1), () -> setMotor(0), this);
    }
}
