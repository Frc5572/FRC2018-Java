package frc.robot.subsystems.drive;

import com.google.flatbuffers.Constants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;


public class DrivetrainVictorSP implements DrivetrainIO {
  private final VictorSP driveVictorSP;


  private final double driveAfterEncoderReduction = (50.0 / 14.0) * (17.0 / 27.0) * (45.0 / 15.0);
  private final double turnAfterEncoderReduction = 150.0 / 7.0;

  private final boolean isTurnMotorInverted = true;

  public DrivetrainVictorSP(int index) {
    System.out.println("[Init] Creating DrivetrainVictorSP" + Integer.toString(index));

    switch (Constants.getRobot()) {
          case 0:
            driveVictorSP = new VictorSP(4);
            break;
          case 1:
            driveVictorSP = new VictorSP(17, MotorType.kBrushless);
           
            break;
          case 2:
            driveSparkMax = new CANSparkMax(30, MotorType.kBrushless);
          
            break;
          case 3:
            driveSparkMax = new CANSparkMax(2, MotorType.kBrushless);
            turnSparkMax = new CANSparkMax(3, MotorType.kBrushless);
            turnAbsoluteEncoder = new AnalogInput(3);
            absoluteEncoderOffset = new Rotation2d(1.27915842);
            break;
          default:
            throw new RuntimeException("Invalid module index for ModuleIOSparkMax");
        }
        break;
      case ROBOT_2023P:
        switch (index) {
          case 0:
            driveSparkMax = new CANSparkMax(15, MotorType.kBrushless);
            turnSparkMax = new CANSparkMax(11, MotorType.kBrushless);
            turnAbsoluteEncoder = new AnalogInput(0);
            absoluteEncoderOffset = new Rotation2d(-0.036);
            break;
          case 1:
            driveSparkMax = new CANSparkMax(12, MotorType.kBrushless);
            turnSparkMax = new CANSparkMax(9, MotorType.kBrushless);
            turnAbsoluteEncoder = new AnalogInput(1);
            absoluteEncoderOffset = new Rotation2d(1.0185);
            break;
          case 2:
            driveSparkMax = new CANSparkMax(14, MotorType.kBrushless);
            turnSparkMax = new CANSparkMax(10, MotorType.kBrushless);
            turnAbsoluteEncoder = new AnalogInput(2);
            absoluteEncoderOffset = new Rotation2d(1.0705);
            break;
          case 3:
            driveSparkMax = new CANSparkMax(13, MotorType.kBrushless);
            turnSparkMax = new CANSparkMax(8, MotorType.kBrushless);
            turnAbsoluteEncoder = new AnalogInput(3);
            absoluteEncoderOffset = new Rotation2d(0.7465);
            break;
          default:
            throw new RuntimeException("Invalid module index for ModuleIOSparkMax");
        }
        break;
      default:
        throw new RuntimeException("Invalid robot for ModuleIOSparkMax");
    }

  if(SparkMaxBurnManager.shouldBurn())

  {
    driveSparkMax.restoreFactoryDefaults();
    turnSparkMax.restoreFactoryDefaults();
  }

  driveSparkMax.setCANTimeout(SparkMaxBurnManager.configCANTimeout);turnSparkMax.setCANTimeout(SparkMaxBurnManager.configCANTimeout);

  driveEncoder=driveSparkMax.getEncoder();turnRelativeEncoder=turnSparkMax.getEncoder();

  for(
  int i = 0;i<SparkMaxBurnManager.configCount;i++)
  {
    SparkMaxPeriodicFrameConfig.configNotLeader(driveSparkMax);
    SparkMaxPeriodicFrameConfig.configNotLeader(turnSparkMax);
    driveSparkMax.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 10);

    turnSparkMax.setInverted(isTurnMotorInverted);

    driveSparkMax.setSmartCurrentLimit(40);
    turnSparkMax.setSmartCurrentLimit(30);
    driveSparkMax.enableVoltageCompensation(12.0);
    turnSparkMax.enableVoltageCompensation(12.0);

    driveEncoder.setPosition(0.0);
    driveEncoder.setMeasurementPeriod(10);
    driveEncoder.setAverageDepth(2);

    turnRelativeEncoder.setPosition(0.0);
    turnRelativeEncoder.setMeasurementPeriod(10);
    turnRelativeEncoder.setAverageDepth(2);
  }

  driveSparkMax.setCANTimeout(0);turnSparkMax.setCANTimeout(0);


  }}

  public void updateInputs(DrivetrainIOInputs inputs) {
    inputs.drivePositionRad = cleanSparkMaxValue(inputs.drivePositionRad,
      Units.rotationsToRadians(driveEncoder.getPosition()) / driveAfterEncoderReduction);
    inputs.driveVelocityRadPerSec = cleanSparkMaxValue(inputs.driveVelocityRadPerSec,
      Units.rotationsPerMinuteToRadiansPerSecond(driveEncoder.getVelocity())
        / driveAfterEncoderReduction);
    inputs.driveAppliedVolts = driveVictorSP.getAppliedOutput() * driveVictorSP.getBusVoltage();
    inputs.driveCurrentAmps = new double[] {driveSparkMax.getOutputCurrent()};
    inputs.driveTempCelcius = new double[] {driveSparkMax.getMotorTemperature()};

    inputs.turnAbsolutePositionRad = MathUtil.angleModulus(new Rotation2d(
      turnAbsoluteEncoder.getVoltage() / RobotController.getVoltage5V() * 2.0 * Math.PI)
        .minus(absoluteEncoderOffset).getRadians());
    inputs.turnPositionRad = cleanSparkMaxValue(inputs.turnPositionRad,
      Units.rotationsToRadians(turnRelativeEncoder.getPosition()) / turnAfterEncoderReduction);
    inputs.turnVelocityRadPerSec = cleanSparkMaxValue(inputs.turnVelocityRadPerSec,
      Units.rotationsPerMinuteToRadiansPerSecond(turnRelativeEncoder.getVelocity())
        / turnAfterEncoderReduction);
    inputs.turnAppliedVolts = turnSparkMax.getAppliedOutput() * turnSparkMax.getBusVoltage();
    inputs.turnCurrentAmps = new double[] {turnSparkMax.getOutputCurrent()};
    inputs.turnTempCelcius = new double[] {turnSparkMax.getMotorTemperature()};
  }

  public void setDriveVoltage(double volts) {
    driveVictorSP.setVoltage(volts);
  }


  public void setDriveBrakeMode(boolean enable) {
    driveVictorSP.setIdleMode(enable ? IdleMode.kBrake : IdleMode.kCoast);
  }

  public void setTurnBrakeMode(boolean enable) {
    driveVictorSP.setIdleMode(enable ? IdleMode.kBrake : IdleMode.kCoast);
  }
}
