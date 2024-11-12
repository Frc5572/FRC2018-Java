package frc.lib.sim;

import edu.wpi.first.math.geometry.Pose2d;

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

    public void setPose(Pose2d pose) {
        this.pose = pose;
    }

}
