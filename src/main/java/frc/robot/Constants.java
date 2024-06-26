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
/*
/*
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
		
	}
}

*/
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	public static final class NetworkTableConstants{
		public static final boolean DEBUG = true;
		public static final NetworkTableInstance kNetworkTableInstance = NetworkTableInstance.getDefault();
		public static final NetworkTable kElevatorTable = kNetworkTableInstance.getTable("Elevator");
		public static final NetworkTable kArmTable = kNetworkTableInstance.getTable("Arm");
		public static final NetworkTable kDriveTable = kNetworkTableInstance.getTable("Drive");
		public static final NetworkTable kPneumaticTable = kNetworkTableInstance.getTable("Pneumatic");
	}

	public static final class OIConstants {
		//PSP Controller
		public static final int kNavXZeroButton = 1;
		public static final int kDriverControllerPort = 1;
		public static final double kDriveDeadband = 0.025;
		//public static final int kNavXZeroButton = 1;
		public static final int kResetEncodersButton = 2;
		public static final int kHoldPositionButton = 3;
		//public static final int kAutoBalanceButton = 4;

		//Intake, Delivery, and Climb Controller
		public static final int kGenControllerPort = 0;
		public static final int kFloorIntakeButton = 3;
		public static final int kFloorIntakeOutButton = 4;
		public static final int kClimbExtendLeftButton = 9;
		public static final int kClimbRetractLeftButton = 11;
		public static final int kClimbExtendRightButton = 10;
		public static final int kClimbRetractRightButton = 12;
		public static final int kClimbLockButton = 10;
		public static final int kFireSlowButton = 5;
		public static final int kTowerIntakeButton = 6;
		public static final int kFireButton = 1; // Trigger Button
		// public static final int kFlyWheelSpinUp = 2; // Thumb Button

		public static final double kDelSpeedChange = 0.7;

		

		//Speed Limiters:
		public static final double kJoystickInput = 1;
	}

	public static final class VisionConstants {
		public static final Transform3d robotToCam = new Transform3d(1,0,0.5, new Rotation3d());

		public static final String kCameraName = "camera";
		public static final Transform3d kCameraOffset = new Transform3d();
	}

	public static final class DriveConstants {
		// Driving Parameters - Note that these are not the maximum capable speeds of
		// the robot, rather the allowed maximum speeds
		public static final double kMaxSpeedMetersPerSecond = 9;//TODO: Find max Speed
		public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

		//slew containts to add motion curve
		public static final double kDirectionSlewRate = 1.2; // radians per second
		public static final double kMagnitudeSlewRate = 1.8; // percent per second (1 = 100%)
		public static final double kRotationalSlewRate = 2.0; 
		// Chassis configuration
		public static final double kTrackWidth = Units.inchesToMeters(28);
		// Distance between centers of right and left wheels on robot
		public static final double kWheelBase = Units.inchesToMeters(28);
		// Distance between front and back wheels on robot
		public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
			new Translation2d(kWheelBase / 2, kTrackWidth / 2),
			new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
			new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
			new Translation2d(-kWheelBase / 2, -kTrackWidth / 2)
			);

		// Angular offsets of the modules relative to the chassis in degrees 
		public static final double kFrontLeftChassisAngularOffset = (-60 - 45)*(Math.PI/180);
		public static final double kFrontRightChassisAngularOffset = (43-180 - 45)*(Math.PI/180);
		public static final double kBackLeftChassisAngularOffset = (45 - 45)*(Math.PI/180);
		public static final double kBackRightChassisAngularOffset = (270 - 45)*(Math.PI/180);

		// SPARK MAX CAN IDs
		// GROUPINGS - Drive = 00 Turn = 10 Delivery = 20 Intake = 30 Climb = 40
		public static final int kFrontLeftDrivingCanId = 1;
		public static final int kRearLeftDrivingCanId = 2;
		public static final int kFrontRightDrivingCanId = 3;
		public static final int kRearRightDrivingCanId = 4;	

		public static final int kFrontLeftTurningCanId = 11;
		public static final int kRearLeftTurningCanId = 12;
		public static final int kFrontRightTurningCanId = 13;
		public static final int kRearRightTurningCanId = 14;

		public static final boolean kGyroReversed = true;
	}

	public static final class PortConstants {
		// CAN Ports 1-4 are avalible for use for subsystem motors
		public static final int kSparkMaxLDeliveryPort = 21;
		public static final int kSparkMaxUDeliveryPort = 22;

		public static final int kLeftTalonFXClimbId = 41;
		public static final int kRightTalonFXClimbId = 42;

		public static final int kSparkMaxUIntakePort = 31;
		public static final int kSparkMaxLIntakePort = 32;

		public static final int kIntakeUltrasonicPort = 1;
		public static final int kIntakeUltrasonicEchoPort = 0;

		// public static final int kClimbUpperLimitSwitchPort = 0;
		// public static final int kClimbLowerLimitSwitchPort = 1;

		public static final int PWMPort = 0;
	}

	public static final class ModuleConstants {
		// The MAXSwerve module can be configured with one of three pinion gears: 12T, 13T, or 14T.
		// This changes the drive speed of the module (a pinion gear with more teeth will result in a
		// robot that drives faster).
		public static final int kDrivingMotorPinionTeeth = 14;

		// Invert the turning encoder, since the output shaft rotates in the opposite direction of
		// the steering motor in the MAXSwerve Module.
		public static final boolean kTurningEncoderInverted = true;

		// Calculations required for driving motor conversion factors and feed forward
		public static final double kDrivingMotorFreeSpeedRps = DriveMotorConstants.kFreeSpeedRpm / 60;
		public static final double kWheelDiameterMeters = 0.0762;
		public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
		// 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15 teeth on the bevel pinion
		public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
		// public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
		//     / kDrivingMotorReduction;
		public static final double kDriveWheelFreeSpeedRps = 11;

		public static final double kDrivingEncoderPositionFactor = (kWheelDiameterMeters * Math.PI)
			/ kDrivingMotorReduction; // meters
		public static final double kDrivingEncoderVelocityFactor = ((kWheelDiameterMeters * Math.PI)
			/ kDrivingMotorReduction) / 60.0; // meters per second

		public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
		public static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second

		// public static final double kTurningEncoderPositionPIDMinInput = Math.PI/180; // radians
		public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
		public static final double kTurningEncoderPositionPIDMaxInput = kTurningEncoderPositionFactor; // radians

		public static final double kDrivingP = 0.04;
		public static final double kDrivingI = 0;
		public static final double kDrivingD = 0;
		public static final double kDrivingFF = 1 / kDriveWheelFreeSpeedRps;
		public static final double kDrivingMinOutput = -1;
		public static final double kDrivingMaxOutput = 1;

		public static final double kTurningP = .5;
		public static final double kTurningI = 0;
		public static final double kTurningD = 0;
		public static final double kTurningFF = 0;
		public static final double kTurningMinOutput = -1;
		public static final double kTurningMaxOutput = 1;

		public static final IdleMode kDrivingMotorIdleMode = IdleMode.kBrake;
		public static final IdleMode kTurningMotorIdleMode = IdleMode.kBrake;

		public static final int kDrivingMotorCurrentLimit = 40; // amps
		public static final int kTurningMotorCurrentLimit = 20; // amps
	}

	public static final class AutoConstants {
		public static final int kAutoMode = 1;

		public static final double kMaxSpeedMetersPerSecond = 0.3;
		public static final double kMaxAccelerationMetersPerSecondSquared = 0.3;
		public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
		public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

		public static final double kPXController = 1;
		public static final double kPYController = 1;
		public static final double kPThetaController = 1;

		// Constraint for the motion profiled robot angle controller
		public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
			kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
	} 

	public static final class DriveMotorConstants {
		public static final double kFreeSpeedRpm = 5676;
	}

	public static final class IntakeConstants {
		public static final double kIntakeOnSpeed = 0.5;
		public static final double kIntakeOffSpeed = 0;
		public static final double kUltrasonicDetectDist = 4.5;
	}

	public static final class DeliveryConstants {
		public static final double kDeliveryFullSpeed = 0.65;
		public static final double kDeliveryHalfSpeed = 0.24;
		public static final double kDeliveryOffSpeed = 0;
	}

	public static final class ClimbConstants {
		public static final double kClimbExtendSpeed = 0.5;
		
		public static final double kClimbRetractsSpeed = 0.5;

		public static final double kGearRatioLeft = 64;
		public static final double kGearRatioRight = 20;
		public static final double kGearRatioMax = 64;
	}

	public static final class PneumaticsConstants {
		// public static final int kPistonFwdPort = 1;
		// public static final int kPistonRevPort = 0;

		// public static final int kRevPneumaticPort = 2;

		// public static final double kMaxPressure = 120.0;
		// public static final double kMinPressure = 60.0;
		
	}
}

/*
* Can IDs:
*  0: PDP
*  1:
*  2: Rev PH
*  3: Arm Falcon 500
*  4: Elevator Falcon 500
*  5: Front Right Swerve Drive Neo v1.1
*  6: Front Left Swerve Drive Neo v1.1
*  7: Back Right Swerve Drive Neo v1.1
*  8: Back Left Swerve Drive Neo v1.1
*  9: Front Right Swerve Steering Neo 550
*  10: Front Left Swerve Steering Neo 550
*  11: Back Right Swerve Steering Neo 550
*  12: Back Left Swerve Steering Neo 550
* 
* DIO Ports:
*  0: Elevator Bottom
*  1: Arm Bottom
* 
* Controllers:
*  PSP: 0: Driver:
*   Axis:
*    1: LJX:Swerve Left/Right
*    2: LJY:Swerve Forward/Backward
*    3: RJX:Swerve Rotate
*    4: RJY:
*    5: X-Hat:
*    6: Y-Hat:
*   Buttons:
*    1: X: Zero NavX
*    2: A: Reset Encoders
*    3: B: Hold Position(X Pattern)
*    4: Y:
*    5: RB:
*    6: LB:
*    7: RT:
*    8: LT:
*    9: Back:
*    10: Start: 
*    11: LJ Click:
*    12: RJ Click:
*  Left Flight Stick: 1: Operator
*   Axis:
*    1: X: 
*    2: Y: Elevator Up/Down
*    3: Z(Twist): 
*    4: Throttle:
*    5: X-Hat:
*    6: Y-Hat:
*   Buttons:
*    1: Trigger: Log Robot Data
*    2: Thumb Button:
*    3: Back Left: 
*    4: Back Right:
*    5: Front Left:
*    6: Front Right:
*    7: Button 7:
*    8: Button 8:
*    9: Button 9:
*    10: Button 10:
*    11: Button 11:
*    12: Button 12:
*  Right Flight Stick: 2: Operator
*   Axis:
*    1: X: 
*    2: Y: Arm In/Out
*    3: Z(Twist): 
*    4: Throttle:
*    5: X-Hat:
*    6: Y-Hat:
*   Buttons:
*    1: Trigger: Claw Toggle
*    2: Thumb Button: Toggle Compressor
*    3: Back Left:
*    4: Back Right: 
*    5: Front Left: Claw Pneumatics Off
*    6: Front Right:
*    7: Button 7:
*    8: Button 8:
*    9: Button 9:
*    10: Button 10:
*    11: Button 11:
*    12: Button 12:
*/