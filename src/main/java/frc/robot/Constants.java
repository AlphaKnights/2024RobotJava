// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	public static class OperatorConstants {
		public static final int kDriverControllerPort = 0;
	}

	public static class DeliveryConstants {
		public static final kDeliveryFullSpeed = 1.0;
		public static final kDeliveryHalfSpeed = 0.5;
		public static final kDeliveryOffSpeed = 0.0;
	}

	public static class IntakeConstants {
		public static final kIntakeOnSpeed = 1.0;
		public static final kIntakeOffSpeed = 0.0;
	}

	public static class PortConstants {
		// CAN Ports 1-4 are avalible for use for subsystem motors
		public static final kSparkMaxDeliveryPort = 0;
		public static final kSparkMaxIntakePort = 1;

		public static final kIntakeLimitSwitchPort = 0;

		// SPARK MAX CAN IDs
		// CAN Ports 5-12 are avalible for use for subsystem motors
		public static final int kFrontLeftDrivingCanId = 6;
		public static final int kRearLeftDrivingCanId = 7;
		public static final int kFrontRightDrivingCanId = 5;
		public static final int kRearRightDrivingCanId = 8;

		public static final int kFrontLeftTurningCanId = 10;
		public static final int kRearLeftTurningCanId = 11;
		public static final int kFrontRightTurningCanId = 9;
		public static final int kRearRightTurningCanId = 12;
	}

	public static class DriveConstants {
		// Driving Parameters - Note that these are not the maximum capable speeds of
		// the robot, rather the allowed maximum speeds
		public static final double kMaxSpeedMetersPerSecond = 5.6; //TODO: Find max Speed <- that was from 2023...
		public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

		//slew containts to add motion curve
		public static final double kDirectionSlewRate = 1.2; // radians per second
		public static final double kMagnitudeSlewRate = 1.8; // percent per second (1 = 100%)
		public static final double kRotationalSlewRate = 2.0; 
		// Chassis configuration
		public static final double kTrackWidth = Units.inchesToMeters(22.125); // THIS WILL NEED TO BE CHANGED
		// Distance between centers of right and left wheels on robot
		public static final double kWheelBase = Units.inchesToMeters(32);  // THIS WILL NEED TO BE CHANGED
		// Distance between front and back wheels on robot
		public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
			new Translation2d(kWheelBase / 2, kTrackWidth / 2),
			new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
			new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
			new Translation2d(-kWheelBase / 2, -kTrackWidth / 2)
		);

		// Angular offsets of the modules relative to the chassis in degrees 
		public static final double kFrontLeftChassisAngularOffset = -Math.PI/2;
		public static final double kFrontRightChassisAngularOffset = 0;
		public static final double kBackLeftChassisAngularOffset = Math.PI;
		public static final double kBackRightChassisAngularOffset = Math.PI/2;

		public static final boolean kGyroReversed = true;
	}
}
