package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {
    @AutoLog
    public static class IntakeInputs {
        public double intakeVelocityRotPerSecond;
    }

    public default void setMotorVoltage(double voltage) {

    }

    public default void setMotorPercentage(double percent) {

    }

    public default void getVoltage() {}

    /** Updates the set of loggable inputs. */
    public default void updateInputs(IntakeInputs inputs) {}

    public default double getOutputCurrentAmps() {
        return 0.0;
    }
}
