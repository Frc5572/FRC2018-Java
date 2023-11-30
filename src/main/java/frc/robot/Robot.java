// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project

package frc.robot;

import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * Robot file.
 */
public class Robot extends LoggedRobot {
    private RobotContainer robotContainer;

    @Override
    public void robotInit() {
        Logger logger = Logger.getInstance();
        if (isReal()) {
            logger.addDataReceiver(new WPILOGWriter("/media/sda1"));
            logger.addDataReceiver(new NT4Publisher());
            setUseTiming(true);
        } else {
            String path = LogFileUtil.findReplayLog();
            logger.setReplaySource(new WPILOGReader(path));
            logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(path, "_sim")));
            setUseTiming(false);
        }
        logger.start();
        robotContainer = new RobotContainer(isReal());
        CameraServer.startAutomaticCapture();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        // System.out.println("Teleop Init");
    }

    @Override
    public void teleopPeriodic() {}

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {}

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
