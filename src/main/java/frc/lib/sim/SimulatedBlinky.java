package frc.lib.sim;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

public class SimulatedBlinky {

    public final int id;

    /**
     * Simulated Pumbaa
     */
    SimulatedBlinky(int id) {
        this.id = id;
    }

    private static final double SHOOTER_DIV = 100.0;
    private static final double SHOOTER_FRONT = 0.271162;
    private Pose2d pose = new Pose2d();
    private double intake = 0.0;
    private double piecePosition = 0.9;
    private boolean hasGamePiece = true;
    private double shooterSpeed = 0.0;
    private double height = 24.0;
    private Rotation2d intakeHeight = new Rotation2d();


    public void setPose(Pose2d pose) {
        this.pose = pose;
    }

    public Pose2d getPose() {
        return pose;
    }

    public boolean couldIntake() {
        return intake > 0.2;
    }

    public void setIntake(double value) {
        this.intake = value;
    }

    public void intakeOnePiece() {
        this.piecePosition = 0.0;
        this.hasGamePiece = true;
    }

    /**
     * Get Shot Pose
     *
     * @return Pose shooting from
     */
    public Pose3d getShootFrom() {
        Translation3d t = new Translation3d(
            this.getPose().getX() + SHOOTER_FRONT * this.getPose().getRotation().getCos(),
            this.getPose().getY() + SHOOTER_FRONT * this.getPose().getRotation().getSin(),
            Units.inchesToMeters(height));
        Rotation3d r = new Rotation3d(0.0, intakeHeight.getRadians(),
            this.getPose().getRotation().getRadians());
        return new Pose3d(t, r);
    }

    /**
     * Advance Note
     *
     * @param dt Change in time
     * @param arena Simulated Arena
     */
    public void advancePiece(double dt, SimulatedArena arena) {
        Rotation2d yaw = this.getPose().getRotation();
        Rotation2d pitch = this.intakeHeight;
        if (hasGamePiece) {
            if (piecePosition < 0.85) {
                piecePosition += intake * dt;
            } else if (intake < -0.2) {
                this.hasGamePiece = false;
                arena.shootPiece(new Pose3d(this.getPose()), yaw, Rotation2d.fromDegrees(0.0), 0.0);
            } else if (piecePosition > 1.0) {
                this.hasGamePiece = false;
                arena.shootPiece(this.getShootFrom(), yaw, pitch, this.shooterSpeed / SHOOTER_DIV);
            } else {
                piecePosition += intake * dt;
            }
        }
    }
}
