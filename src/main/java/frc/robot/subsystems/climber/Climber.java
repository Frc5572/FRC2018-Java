package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.Logger;
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
        Logger.getInstance().processInputs("Climber", inputs);
    }

    /**
     * Set power of intake motors
     *
     * @param power power of motors from -1 to 1
     */
    public void setMotor(double power) {
        Logger.getInstance().recordOutput("Climber/voltage", power);
        io.setMotorVoltage(power);
    }
}
