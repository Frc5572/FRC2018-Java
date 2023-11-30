package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * Intake subsystem.
 */

public class IntakeVictorSP implements IntakeIO {
    private final MotorControllerGroup intakeMotors =
        new MotorControllerGroup(new VictorSP(1), new VictorSP(2));

    public void setMotorVoltage(double power) {
        intakeMotors.set(power);
    }

    public void intakeInactive() {
        intakeMotors.set(0);
    }
}
