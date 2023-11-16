package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Elevator subsystem.
 */

public class Elevator extends SubsystemBase {
    private ElevatorIO io;
    private ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();


    /**
     * Create Wrist Intake Subsystem
     */
    public Elevator(ElevatorIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Drivetrain", inputs);
    }

    /**
     * Set power of intake motors
     *
     * @param power power of motors from -1 to 1
     */
    public void setMotor(double power) {
        Logger.getInstance().recordOutput("Drivetrain/voltage", power * 0.8);
        io.setMotorVoltage(power * 0.8);
    }
}
