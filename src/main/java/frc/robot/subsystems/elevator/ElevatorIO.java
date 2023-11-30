package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

/**
 * ElevatorIO interface
 */
public interface ElevatorIO {
    /**
     * ElevatorIO
     */
    @AutoLog
    public static class ElevatorIOInputs {
    }

    public default void setMotorVoltage(double voltage) {

    }

    public default void setMotorPercentage(double percent) {

    }


    /** Updates the set of loggable inputs. */
    public default void updateInputs(ElevatorIOInputs inputs) {}
}
