package frc.robot.subsystems.drive;

import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
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
        Logger.processInputs("Drivetrain", inputs);
    }

    /**
     * Set power of drive motors
     *
     * @param lpower power of left motor from -1 to 1
     * @param rpower power of right motor from -1 to 1
     */
    public void setMotor(double lpower, double rpower) {
        Logger.recordOutput("Drivetrain/left voltage", lpower);
        Logger.recordOutput("Drivetrain/right voltage", rpower);

        io.setDriveVoltage(lpower, rpower);
    }

    public Command drive(DoubleSupplier left, DoubleSupplier right) {
        double laxis = MathUtil.applyDeadband(-left.getAsDouble(), .01);
        double raxis = MathUtil.applyDeadband(-right.getAsDouble(), .01);

        return Commands.run(() -> this.setMotor(laxis, raxis), this);
    }
}


