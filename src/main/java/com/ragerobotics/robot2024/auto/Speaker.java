package com.ragerobotics.robot2024.auto;

import com.ragerobotics.lib.control.Path;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class Speaker extends TaskList {
        public enum Position {
                Amp, Center, Far
        }

        public Speaker(boolean redAlliance, Position position) {
                if (position == Position.Amp) {
                        add(new SpinShooter());
                        add(new Wait(1));
                        add(new RunShooter());
                        add(new Wait(1));
                        add(new StowShooter());

                        add(new TaskList(new StartIntake(), new FollowPath(new Path(
                                        new Pose2d(new Translation2d(0, 0),
                                                        new Rotation2d(redAlliance ? 0.5235987755982988
                                                                        : Math.PI - 0.5235987755982988)),
                                        new Pose2d(new Translation2d(4,
                                                        0),
                                                        new Rotation2d(redAlliance ? 0.5235987755982988
                                                                        : Math.PI - 0.5235987755982988))),
                                        true, 5.0), new StopIntake()));
                } else if (position == Position.Center) {
                        add(new FollowPath(new Path(
                                        new Pose2d(new Translation2d(0, 0),
                                                        new Rotation2d(Math.PI / 2)),
                                        new Pose2d(new Translation2d(0.5,
                                                        0),
                                                        new Rotation2d(Math.PI / 2))),
                                        true, 5.0));

                        add(new SpinShooter());
                        add(new Wait(1));
                        add(new RunShooter());
                        add(new Wait(1));
                        add(new StowShooter());

                        add(new TaskList(new StartIntake(), new FollowPath(new Path(
                                        new Pose2d(new Translation2d(0.5, 0),
                                                        new Rotation2d(Math.PI / 2)),
                                        new Pose2d(new Translation2d(2.5,
                                                        0),
                                                        new Rotation2d(Math.PI / 2))),
                                        true, 5.0), new StopIntake()));

                        add(new TaskList(new StartIntake(), new FollowPath(new Path(
                                        new Pose2d(new Translation2d(2.5,
                                                        0),
                                                        new Rotation2d(Math.PI / 2)),
                                        new Pose2d(new Translation2d(0.5, 0),
                                                        new Rotation2d(Math.PI / 2))),
                                        true, 5.0), new StopIntake()));

                        add(new SpinShooter());
                        add(new Wait(1));
                        add(new RunShooter());
                        add(new Wait(1));
                        add(new StowShooter());
                } else if (position == Position.Far) {
                        add(new SpinShooter());
                        add(new Wait(1));
                        add(new RunShooter());
                        add(new Wait(1));
                        add(new StowShooter());

                        add(new FollowPath(new Path(
                                        new Pose2d(new Translation2d(0, 0),
                                                        new Rotation2d(redAlliance ? 0.5235987755982988 + Math.PI / 2
                                                                        : Math.PI - 0.5235987755982988 - Math.PI / 2)),
                                        new Pose2d(new Translation2d(0,
                                                        redAlliance ? 2 : 2),
                                                        new Rotation2d(redAlliance ? 0.5235987755982988 + Math.PI / 2
                                                                        : Math.PI - 0.5235987755982988 - Math.PI / 2))),
                                        true, 5.0));
                        add(new FollowPath(new Path(
                                        new Pose2d(new Translation2d(0,
                                                        redAlliance ? 2 : 2),
                                                        new Rotation2d(redAlliance ? 0.5235987755982988 + Math.PI / 2
                                                                        : Math.PI - 0.5235987755982988 - Math.PI / 2)),
                                        new Pose2d(new Translation2d(4,
                                                        redAlliance ? 2 : 2),
                                                        new Rotation2d(redAlliance ? 0.5235987755982988 + Math.PI / 2
                                                                        : Math.PI - 0.5235987755982988 - Math.PI / 2))),
                                        true, 5.0));
                }
        }
}
