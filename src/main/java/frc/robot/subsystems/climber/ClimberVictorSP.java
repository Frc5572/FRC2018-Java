package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * CLimber VictorSP
 */
public class ClimberVictorSP implements ClimberIO {
    private final VictorSP climb = new VictorSP(0);

    public void setMotorVoltage(double power) {
        climb.set(power);
    }


}

