package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {
    /**
     * ElevatorIO
     */
    @AutoLog
    public static class ElevatorIOInputs {
        public double elevatorVelocityRotPerSecond;
    }

    public default void setMotorVoltage(double voltage) {

    }

    public default void setMotorPercentage(double percent) {

    }

    public default void getVoltage() {}

    /** Updates the set of loggable inputs. */
    public default void updateInputs(ElevatorIOInputs inputs) {}

    public default double getOutputCurrentAmps() {
        return 0.0;
    }
}
