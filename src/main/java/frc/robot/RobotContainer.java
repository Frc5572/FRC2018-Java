package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Drive;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.subsystems.drive.Drivetrain;
import frc.robot.subsystems.drive.DrivetrainIO;
import frc.robot.subsystems.drive.DrivetrainVictorSP;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;
import frc.robot.subsystems.intake.IntakeVictorSP;

/**
 * Robot Container file.
 */
public class RobotContainer {
    Joystick driver = new Joystick(0);

    private final Drivetrain tankDrive;
    private final Intake intake;

    private final JoystickButton intakeIn =
        new JoystickButton(driver, XboxController.Button.kX.value);
    private final JoystickButton intakeOut =
        new JoystickButton(driver, XboxController.Button.kY.value);

    public RobotContainer(boolean isReal) {
        if (isReal) {
            tankDrive = new Drivetrain(new DrivetrainVictorSP());
            intake = new Intake(new IntakeVictorSP());
        } else {
            tankDrive = new Drivetrain(new DrivetrainIO() {});
            intake = new Intake(new IntakeIO() {});
        }
        tankDrive.setDefaultCommand(new Drive(tankDrive, driver));
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        intakeIn.whileTrue(new IntakeIn(intake));
        intakeOut.whileTrue(new IntakeOut(intake));
    }
}
