package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
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
    CommandXboxController driver = new CommandXboxController(0);

    private final Drivetrain tankDrive;
    private final Intake intake;
    private final Elevator elevator;
    private final Climber climber;

    /**
     * Robot Container
     *
     * @param isReal checks if robot is real
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
        tankDrive
            .setDefaultCommand(tankDrive.drive(() -> driver.getLeftY(), () -> driver.getRightY()));
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        driver.x().whileTrue(intake.intakeIn());
        driver.y().whileTrue(intake.intakeOut());
        driver.b().whileTrue(elevator.elevatorUp());
        driver.povUp().whileTrue(climber.climberUp());
        driver.povDown().whileTrue(climber.climberDown());
    }
}
