package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Intake subsystem.
 */

public class Intake extends SubsystemBase {
    private final MotorControllerGroup intakeMotors =
        new MotorControllerGroup(new VictorSP(1), new VictorSP(2));

    public void intakeIn() {
        intakeMotors.set(1);
    }

    public void intakeOut() {
        intakeMotors.set(-1);
    }

    public void intakeInactive() {
        intakeMotors.set(0);
    }
}
