package frc.lib.sim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import frc.lib.utils.FieldConstants;

public class SimulatedArena {
    /**
     * Shooting Note
     */
    private static class ShotPiece {
        public Pose3d pose;
        ArrayList<Pose3d> trajectory = new ArrayList<>();
        double t = 0.0;

        /**
         * Shooting Note
         *
         * @param startPose Starting Pose
         * @param yaw Yaw
         * @param pitch Pitch
         * @param velocity Velocity
         */
        public ShotPiece(Pose3d startPose, Rotation2d yaw, Rotation2d pitch, double velocity) {
            this.pose = startPose;
            double vz = pitch.getSin() * velocity;
            double vx = yaw.getCos() * velocity;
            double vy = yaw.getSin() * velocity;
            double prevZ = startPose.getZ();
            for (double t = 0.0; prevZ > 0.0; t += LoggedRobot.defaultPeriodSecs) {
                double x = startPose.getX() + vx * t;
                double y = startPose.getY() + vy * t;
                double z = startPose.getZ() + vz * t - 9.81 * t * t;
                trajectory.add(new Pose3d(x, y, z, new Rotation3d()));
                prevZ = z;
            }
        }

        /**
         * Update Viz
         */
        public void update(double dt) {
            int index = (int) Math.floor(t / LoggedRobot.defaultPeriodSecs);
            if (index >= trajectory.size()) {
                pose = trajectory.get(trajectory.size() - 1);
                pose = new Pose3d(pose.getX(), pose.getY(), 0.0, new Rotation3d());
            } else {
                pose = trajectory.get(index);
            }
            t += dt;
        }
    }

    private static final double NOTE_HEIGHT = Units.inchesToMeters(1.7);
    private Set<SimulatedBlinky> robots = new HashSet<>();
    private int id = 0;
    private static final Rotation3d NO_ROT = new Rotation3d();
    private List<Pose3d> pieces = Stream
        .concat(
            Stream.concat(Arrays.stream(FieldConstants.StagingLocations.centerlineTranslations),
                Arrays.stream(FieldConstants.StagingLocations.spikeTranslations)),
            Arrays.stream(FieldConstants.StagingLocations.spikeTranslations)
                .map((p) -> FieldConstants.allianceFlip(p)))
        .map((pos) -> new Pose3d(new Translation3d(pos.getX(), pos.getY(), NOTE_HEIGHT), NO_ROT))
        .collect(Collectors.toList());
    public List<ShotPiece> shotPieces = new ArrayList<>();

    /**
     * Create a new simulated robot
     *
     * @return Simulated Robot
     */
    public SimulatedBlinky newBlinky() {
        SimulatedBlinky dt = new SimulatedBlinky(this.id++);
        robots.add(dt);
        return dt;
    }

    /**
     * Update Viz
     *
     * @param dt Change in time
     */
    public void update(double dt) {
        robots: for (SimulatedBlinky robot : this.robots) {
            Logger.recordOutput("Viz/Robot" + robot.id, robot.getPose());
            robot.advancePiece(dt, this);
            if (robot.couldIntake()) {
                System.out.println("checking");
                for (int i = 0; i < pieces.size(); i++) {
                    double distance = pieces.get(i).toPose2d().getTranslation()
                        .minus(robot.getPose().getTranslation()).getNorm();
                    if (distance < 0.6) {
                        pieces.remove(i);
                        robot.intakeOnePiece();
                        continue robots;
                    }
                }
            }
        }
        for (int i = 0; i < shotPieces.size(); i++) {
            shotPieces.get(i).update(dt);
            if (shotPieces.get(i).pose.getZ() < NOTE_HEIGHT) {
                pieces.add(new Pose3d(shotPieces.get(i).pose.getX(), shotPieces.get(i).pose.getY(),
                    NOTE_HEIGHT, NO_ROT));
                shotPieces.remove(i);
                i--;
                continue;
            }
        }
        Logger.recordOutput("Viz/Notes", this.pieces.toArray(Pose3d[]::new));
        Logger.recordOutput("Viz/ShotNotes",
            this.shotPieces.stream().map((shotNote) -> shotNote.pose).toArray(Pose3d[]::new));
    }

    /**
     * Shoot note
     *
     * @param pose Shooting Pose
     * @param yaw Shooting Yaw
     * @param pitch Shooting Pitch
     * @param velocity Shooting Velocity
     */
    void shootPiece(Pose3d pose, Rotation2d yaw, Rotation2d pitch, double velocity) {
        this.shotPieces.add(new ShotPiece(pose, yaw, pitch, velocity));
    }


}
