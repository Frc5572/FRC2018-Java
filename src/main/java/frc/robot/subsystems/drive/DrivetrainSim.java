package frc.robot.subsystems.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.lib.sim.SimulatedBlinky;

public class DrivetrainSim implements DrivetrainIO {
    private FlywheelSim driveSim = new FlywheelSim(DCMotor.getAndymark9015(4), (8.14 / 1.0), 0.025);

    private double angle;
    private double distance;

    private double driveAppliedVolts = 0.0;
    private SimpleMotorFeedforward driveFeedforward = new SimpleMotorFeedforward(0.0, 0.13);
    private PIDController driveFeedback = new PIDController(0.5, 0.0, 0.0);
    private Pose2d currentPose = new Pose2d();

    SimulatedBlinky blinky;

    /**
     * Swerve Module Sim
     */
    public DrivetrainSim(SimulatedBlinky blinky) {
        this.blinky = blinky;
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {}

    @Override
    public void setPose(Pose2d pose) {
        this.currentPose = pose;
        this.blinky.setPose(this.currentPose);
    }

}
