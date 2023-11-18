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
import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.climber.ClimberIO;
import frc.robot.subsystems.climber.ClimberVictorSP;
import frc.robot.subsystems.drive.Drivetrain;
import frc.robot.subsystems.drive.DrivetrainIO;
import frc.robot.subsystems.drive.DrivetrainVictorSP;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIO;
import frc.robot.subsystems.elevator.ElevatorVictorSP;
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
    private final Elevator elevator;
    private final Climber climber;

    private final JoystickButton intakeIn =
        new JoystickButton(driver, XboxController.Button.kX.value);
    private final JoystickButton intakeOut =
        new JoystickButton(driver, XboxController.Button.kY.value);

    private final JoystickButton elevatorUp =
        new JoystickButton(driver, XboxController.Button.kB.value);

    private final POVButton climberUp = new POVButton(driver, 180);
    private final POVButton climberDown = new POVButton(driver, 0);

    /**
     * Robot Container
     * 
     * @param isReal checks if robot is real or not
     */
    public RobotContainer(boolean isReal) {
        if (isReal) {
            tankDrive = new Drivetrain(new DrivetrainVictorSP());
            intake = new Intake(new IntakeVictorSP());
            elevator = new Elevator(new ElevatorVictorSP());
            climber = new Climber(new ClimberVictorSP());
        } else {
            tankDrive = new Drivetrain(new DrivetrainIO() {});
            intake = new Intake(new IntakeIO() {});
            elevator = new Elevator(new ElevatorIO() {});
            climber = new Climber(new ClimberIO() {});
        }
        tankDrive.setDefaultCommand(new Drive(tankDrive, driver));
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        intakeIn.whileTrue(new IntakeIn(intake));
        intakeOut.whileTrue(new IntakeOut(intake));
        elevatorUp.whileTrue(new ElevatorUp(elevator));
        climberUp.whileTrue(new ClimberUp(climber));
        climberDown.whileTrue(new ClimberDown(climber));
    }
}
