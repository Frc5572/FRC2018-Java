package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * Intake subsystem.
 */

public class IntakeVictorSP implements IntakeIO {
    private final VictorSP leftIntake = new VictorSP(1);
    private final VictorSP rightIntake = new VictorSP(2);


    public void setMotorVoltage(double power) {
        leftIntake.addFollower(rightIntake);
        leftIntake.set(power);
    }

    public void intakeInactive() {
        leftIntake.addFollower(rightIntake);
        leftIntake.set(0);
    }
}
