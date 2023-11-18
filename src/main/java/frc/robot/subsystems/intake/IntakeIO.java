package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

/**
 * Intake IO
 */
public interface IntakeIO {
    /**
     * Intake Inputs
     */
    @AutoLog
    public static class IntakeInputs {
        public double intakeVelocityRotPerSecond;
    }

    public default void setMotorVoltage(double voltage) {

    }

    public default void setMotorPercentage(double percent) {

    }


    /** Updates the set of loggable inputs. */
    public default void updateInputs(IntakeInputs inputs) {}

}
