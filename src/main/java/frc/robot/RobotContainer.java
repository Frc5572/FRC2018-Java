package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.Drive;
import frc.robot.subsystems.drive.Drivetrain;
import frc.robot.subsystems.drive.DrivetrainIO;
import frc.robot.subsystems.drive.DrivetrainVictorSP;

/**
 * Robot Container file.
 */
public class RobotContainer {
    Joystick driver = new Joystick(0);

    private final Drivetrain tankDrive;

    public RobotContainer(boolean isReal) {
        if (isReal) {
            tankDrive = new Drivetrain(new DrivetrainVictorSP());
        } else {
            tankDrive = new Drivetrain(new DrivetrainIO() {});
        }
        tankDrive.setDefaultCommand(new Drive(tankDrive, driver));
        configureButtonBindings();
    }

    private void configureButtonBindings() {}
}
