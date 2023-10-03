package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.Drive;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.drive.Drivetrain;
import frc.robot.subsystems.intake.Intake;

/**
 * Robot Container file.
 */
public class RobotContainer {
    Joystick driver = new Joystick(0);
    Joystick operator = new Joystick(1);

    private final Intake intake = new Intake();
    private final Climber climb = new Climber();
    private final Elevator elevator = new Elevator();
    private final Drivetrain tankDrive = new Drivetrain();

    private final POVButton climberUp = new POVButton(driver, 180);
    private final POVButton climberDown = new POVButton(driver, 0);
    private final JoystickButton intakeIn =
        new JoystickButton(operator, XboxController.Button.kX.value);
    private final JoystickButton intakeOut =
        new JoystickButton(operator, XboxController.Button.kY.value);
    private final JoystickButton elevatorUp =
        new JoystickButton(driver, XboxController.Button.kB.value);

    public RobotContainer() {
        tankDrive.setDefaultCommand(new Drive(tankDrive, driver));
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        climberUp.whileTrue(new ClimberUp(climb));
        climberDown.whileTrue(new ClimberDown(climb));
        intakeIn.whileTrue(new IntakeIn(intake));
        intakeOut.whileTrue(new IntakeOut(intake));
        elevatorUp.whileTrue(new ElevatorUp(elevator));
    }
}
