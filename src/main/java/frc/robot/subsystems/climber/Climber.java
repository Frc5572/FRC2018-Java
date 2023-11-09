package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Climber subsystem.
 */

public class Climber extends SubsystemBase {
    private final VictorSP climb = new VictorSP(0);

    public void climberUp() {
        climb.set(.5);
    }

    public void climberDown() {
        climb.set(-.5);
    }

    public void climberInactive() {
        climb.set(0);
    }
}
