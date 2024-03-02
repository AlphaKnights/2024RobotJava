// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.AutoCommands;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.RunCommand;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import java.util.List;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
*/
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	private final ExampleSubsystem m_ExampleSubsystem = new ExampleSubsystem();

	private final IntakeSubsystem m_intakeSybsystem = new IntakeSubsystem();
	private final DeliverySubsystem m_deliverySubsystem = new DeliverySubsystem();

	private final DriveSubsystem m_robotDrive = new DriveSubsystem();

	// Replace with CommandPS4Controller or CommandJoystick if needed
	private final CommandXboxController m_driverController =
		new CommandXboxController(OIConstants.kDriverControllerPort);

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		// Configure the trigger bindings
		configureBindings();

		m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY()*OIConstants.kJoystickInput, OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX()*OIConstants.kJoystickInput, OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                false, true),
            m_robotDrive));
	}

	/**
	 * Use this method to define your trigger->command mappings. Triggers can be created via the
	 * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
	 * predicate, or via the named factories in {@link
	 * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
	 * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
	 * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
	 * joysticks}.
	 */
	private void configureBindings() {
		// Schedule `ExampleCommand` when `exampleCondition` changes to `true`
		// new Trigger(m_exampleSubsystem::exampleCondition)
		//     .onTrue(new ExampleCommand(m_exampleSubsystem));

		// Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
		// cancelling on release.
		// m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

		Command fullDelivery = Commands.runOnce(m_deliverySubsystem::fullDelivery, m_deliverySubsystem);
		Command halfDelivery = Commands.runOnce(m_deliverySubsystem::halfDelivery, m_deliverySubsystem);
		Command stopDelivery = Commands.runOnce(m_deliverySubsystem::stopDelivery, m_deliverySubsystem);

		Command intakeOn = Commands.runOnce(m_intakeSybsystem::intakeOn, m_intakeSybsystem);
		Command intakeOff = Commands.runOnce(m_intakeSybsystem::intakeOn, m_intakeSybsystem);

		m_driverController.rightBumper().onTrue(fullDelivery).onFalse(stopDelivery);
		m_driverController.leftBumper().onTrue(halfDelivery).onFalse(stopDelivery);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {

    	// homeElevatorCommand.schedule();
    	// Create config for trajectory
    	TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(DriveConstants.kDriveKinematics);

		switch (AutoConstants.kAutoMode) {
			default :
			case 0 : //Auto Command to leave
				// An example trajectory to follow. All units in meters.
				Trajectory leaveTrajectory = TrajectoryGenerator.generateTrajectory(
					// Start at the origin facing the +X direction
					new Pose2d(0, 0, new Rotation2d(0)),
					//no other points to pass through
					List.of(),
					// End posiition to reach, 4.5 meters forward
					new Pose2d(4.5, 0, new Rotation2d(0)),
					config);
				var thetaController = new ProfiledPIDController(
				AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
				thetaController.enableContinuousInput(-Math.PI, Math.PI);
			
				SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
					leaveTrajectory,
					m_robotDrive::getPose, // Functional interface to feed supplier
					DriveConstants.kDriveKinematics,
			
					// Position controllers
					new PIDController(AutoConstants.kPXController, 0, 0),
					new PIDController(AutoConstants.kPYController, 0, 0),
					thetaController,
					m_robotDrive::setModuleStates,
					m_robotDrive);
			
				// Reset odometry to the starting pose of the trajectory.
				m_robotDrive.resetOdometry(leaveTrajectory.getInitialPose());
				//moves along given trajectory (forward 4.5 meters)
				return swerveControllerCommand;
		}
	}
	}