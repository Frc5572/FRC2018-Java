package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Drivetrain subsystem.
 */

public class Drivetrain extends SubsystemBase {
    private DrivetrainVictorSP io;



    /**
     * Create Wrist Intake Subsystem
     */
    public Drivetrain() {
        io = new DrivetrainVictorSP();
    }

    @Override
    public void periodic() {}

    /**
     * Set power of intake motors
     *
     * @param power power of motors from -1 to 1
     */
    public void setMotor(double lpower, double rpower) {
        io.setDriveVoltage(lpower, rpower);
    }
}


