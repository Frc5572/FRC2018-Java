package frc.lib.utils;

import java.util.Optional;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class FieldConstants {

    public static double fieldLength = Units.inchesToMeters(651.223);
    public static double fieldWidth = Units.inchesToMeters(323.277);

    public static final class StagingLocations {
        public static double centerlineX = fieldLength / 2.0;

        // need to update
        public static double centerlineFirstY = Units.inchesToMeters(29.638);
        public static double centerlineSeparationY = Units.inchesToMeters(66);
        public static double spikeX = Units.inchesToMeters(114);
        // need
        public static double spikeFirstY = Units.inchesToMeters(161.638);
        public static double spikeSeparationY = Units.inchesToMeters(57);

        public static Translation2d[] centerlineTranslations = new Translation2d[5];
        public static Translation2d[] spikeTranslations = new Translation2d[3];

        static {
            for (int i = 0; i < centerlineTranslations.length; i++) {
                centerlineTranslations[i] =
                    new Translation2d(centerlineX, centerlineFirstY + (i * centerlineSeparationY));
            }
        }
    }

    /**
     * Flips a pose to the correct side of the field based on the current alliance color. By
     * default, all translations and poses in {@link FieldConstants} are stored with the origin at
     * the rightmost point on the BLUE ALLIANCE wall.
     *
     * @param pose Initial Pose
     * @return Pose2d flipped to Red Alliance
     */
    public static Pose2d allianceFlip(Pose2d pose) {
        Optional<Alliance> ally = DriverStation.getAlliance();
        if (ally.isPresent()) {
            if (ally.get() == Alliance.Red) {
                pose = new Pose2d(fieldLength - pose.getX(), pose.getY(),
                    new Rotation2d(-pose.getRotation().getCos(), pose.getRotation().getSin()));
            }
        }
        return pose;
    }

    /**
     * Flips a pose to the correct side of the field based on the current alliance color. By
     * default, all translations and poses in {@link FieldConstants} are stored with the origin at
     * the rightmost point on the BLUE ALLIANCE wall.
     *
     * @param pose Initial Pose
     * @return Pose2d flipped to Red Alliance
     */
    public static Translation2d allianceFlip(Translation2d pose) {
        Optional<Alliance> ally = DriverStation.getAlliance();
        if (ally.isPresent()) {
            if (ally.get() == Alliance.Red) {
                pose = new Translation2d(fieldLength - pose.getX(), pose.getY());
            }
        }
        return pose;
    }
}
