package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.autos.Auto;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.Drive;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

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

  private final POVButton climberUp = new POVButton(driver, 0);
  private final POVButton climberDown = new POVButton(driver, 180);
  private final JoystickButton intakeIn =  
    new JoystickButton(operator, XboxController.Button.kX.value);
  private final JoystickButton intakeOut = 
    new JoystickButton(operator, XboxController.Button.kY.value);
  private final JoystickButton elevatorUp = 
    new JoystickButton(operator, XboxController.Button.kB.value);

  public RobotContainer() {
    tankDrive.setDefaultCommand(new Drive(tankDrive, driver));
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    climberUp.whenHeld(new ClimberUp(climb));
    climberDown.whenHeld(new ClimberDown(climb));
    intakeIn.whenPressed(new IntakeIn(intake));
    intakeOut.whenPressed(new IntakeOut(intake));
    elevatorUp.whenPressed(new ElevatorUp(elevator));
  }
}
