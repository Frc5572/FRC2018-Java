package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Drivetrain subsystem.
 */

public class Drivetrain extends SubsystemBase {
    private final MotorControllerGroup leftMotors =
        new MotorControllerGroup(new VictorSP(4), new VictorSP(5));
    private final MotorControllerGroup rightMotors =
        new MotorControllerGroup(new VictorSP(6), new VictorSP(7));

    public Drivetrain() {
        rightMotors.setInverted(true);
    }

    public void drive(double axis1, double axis2) {
        leftMotors.set(axis2);
        rightMotors.set(axis1);
    }
}


