package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.AutoLog;

public interface ClimberIO {
    /**
     * ClimberIO
     */
    @AutoLog
    public static class ClimberIOInputs {
        public double climberVelocityRotPerSecond;
    }

    public default void setMotorVoltage(double voltage) {

    }

    public default void setMotorPercentage(double percent) {

    }


    /** Updates the set of loggable inputs. */
    public default void updateInputs(ClimberIOInputs inputs) {}

   
}
