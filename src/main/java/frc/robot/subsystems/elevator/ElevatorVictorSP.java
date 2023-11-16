package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class ElevatorVictorSP implements ElevatorIO {
    private final VictorSP elevator = new VictorSP(3);

    public void setMotorVoltage() {
        elevator.set(-.5);
    }

}
