package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Drivetrain subsystem.
 */

public class Drivetrain extends SubsystemBase {
    private DrivetrainIO io;
    private DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();


    /**
     * Create Wrist Intake Subsystem
     */
    public Drivetrain(DrivetrainIO io) {
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
        io.setDriveVoltage(power * 0.8);
    }
}


