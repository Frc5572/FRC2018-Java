package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
        Logger.getInstance().processInputs("Intake", inputs);
    }

    /**
     * Set power of intake motors
     *
     * @param power power of motors from -1 to 1
     */
    public void setMotorVoltage(double power) {
        Logger.getInstance().recordOutput("Intake/voltage", power * 0.8);
        io.setMotorVoltage(power * 0.8);
    }
}
