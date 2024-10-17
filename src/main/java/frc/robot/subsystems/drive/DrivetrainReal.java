package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * Drivetrain VictorSP
 */
public class DrivetrainReal implements DrivetrainIO {
    private final VictorSP left1 = new VictorSP(4);
    private final VictorSP left2 = new VictorSP(5);

    private final VictorSP right1 = new VictorSP(6);
    private final VictorSP right2 = new VictorSP(7);

    /**
     * Drivetrain VictorSP
     */
    public DrivetrainReal() {
        left1.addFollower(left2);
        right1.addFollower(right2);
        right1.setInverted(true);
        right2.setInverted(true);
    }

    /**
     * Drive Voltage
     */
    public void setDriveVoltage(double lvolts, double rvolts) {
        left1.set(lvolts);
        right1.set(rvolts);
    }

}
