package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private final VictorSP elevator = new VictorSP(3);

    public void elevatorUp() {
        elevator.set(-.5);
    }

    public void elevatorInactive() {
        elevator.set(0);
    }
}
