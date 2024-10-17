package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * Elevator VictorSP
 */
public class ElevatorReal implements ElevatorIO {
    private final VictorSP elevator = new VictorSP(3);

    public void setMotorVoltage(double power) {
        elevator.set(power);
    }


}
