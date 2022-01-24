package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private final MotorControllerGroup leftMotors = new MotorControllerGroup(new VictorSP(4), new VictorSP(5));
    private final MotorControllerGroup rightMotors = new MotorControllerGroup(new VictorSP(6), new VictorSP(7));

    public Drivetrain() {
        rightMotors.setInverted(true);
    }

    public void drive(double yAxis, double yAxis2) {
        leftMotors.set(yAxis);
        rightMotors.set(yAxis2);
    }
}


