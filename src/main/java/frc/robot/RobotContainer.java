// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OIConstants;
//import frc.robot.commands.Drive.NavXZeroCommand;
import frc.robot.Constants.PortConstants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;

import java.util.List;

import org.w3c.dom.stylesheets.MediaList;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.AnalogInput;

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
	private final DriveSubsystem m_robotDrive = new DriveSubsystem();
	private final IntakeSubsystem m_intakeSybsystem = new IntakeSubsystem();
	private final DeliverySubsystem m_deliverySubsystem = new DeliverySubsystem();
	private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
	// private final LedSubsystem m_ledSubsystem = new LedSubsystem();
	//private final NavXZeroCommand m_zeroCommand = new NavXZeroCommand(m_robotDrive);
	private IntakeCommands m_IntakeCommands;

	// Replace with CommandPS4Controller or CommandJoystick if needed
	//private final NavXZeroCommand m_zeroCommand = new NavXZeroCommand(m_robotDrive);
	//private final AutoLevel m_autoLevel = new AutoLevel(m_robotDrive);
  	//private final AutoZeroCommand m_AutoZeroCommand = new AutoZeroCommand(m_robotDrive);
	//private final XboxController m_driverController = new XboxController();
	private final CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);
	private final Joystick m_genController = new Joystick(OIConstants.kGenControllerPort);
	//private final Joystick m_towerIntakeButton = new JoystickButton(m_genController, OIConstants.kTowerIntakeButton);
	private final JoystickButton m_extendButton = new JoystickButton(m_genController, OIConstants.kClimbExtendButton);
	private final JoystickButton m_retractButton = new JoystickButton(m_genController, OIConstants.kClimbRetractButton);
	private final JoystickButton m_intakeOnButton = new JoystickButton(m_genController, OIConstants.kFloorIntakeButton);
	private final JoystickButton m_towerIntakeButton = new JoystickButton(m_genController, OIConstants.kTowerIntakeButton);
	private final JoystickButton m_intakeOutButton = new JoystickButton(m_genController, OIConstants.kFloorIntakeOutButton);
	private final JoystickButton m_slowShotButton = new JoystickButton(m_genController, OIConstants.kFireSlowButton);
	private final JoystickButton m_fireShotButton = new JoystickButton(m_genController, OIConstants.kFireButton);
	//private final AnalogInput m_ultrasonic = new AnalogInput(PortConstants.kIntakeUltrasonicPort);
	private int switchCounter = 0;
	private boolean lastTickInput = false;
	
	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer(UsbCamera frontCamera, UsbCamera rearCamera) {

		m_IntakeCommands = new IntakeCommands(m_intakeSybsystem);
		// Configure the trigger bindings
		configureBindings();
		m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                MathUtil.applyDeadband(m_driverController.getLeftY()*OIConstants.kJoystickInput, OIConstants.kDriveDeadband),
                MathUtil.applyDeadband(m_driverController.getLeftX()*OIConstants.kJoystickInput, OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRawAxis(2), OIConstants.kDriveDeadband),
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


		//InstantCommand fullDelivery = new InstantCommand(()-> m_deliverySubsystem.fullDelivery(), m_deliverySubsystem);
		//InstantCommand halfDelivery = new InstantCommand(()-> m_deliverySubsystem.halfDelivery(), m_deliverySubsystem);
		//InstantCommand stopDelivery = new InstantCommand(()-> m_deliverySubsystem.stopDelivery(), m_deliverySubsystem);

		//InstantCommand intakeOn = new InstantCommand(() -> m_intakeSybsystem.intakeOn(), m_intakeSybsystem);
		InstantCommand intakeOut = new InstantCommand(()-> m_intakeSybsystem.intakeReverse(), m_intakeSybsystem);
		//InstantCommand intakeOff = new InstantCommand(()-> m_intakeSybsystem.intakeOn(), m_intakeSybsystem);

		InstantCommand extendClimb = new InstantCommand(()-> m_climbSubsystem.extend(), m_climbSubsystem);
		InstantCommand retractClimb = new InstantCommand(()-> m_climbSubsystem.retract(), m_climbSubsystem);
		InstantCommand haltClimb = new InstantCommand(()-> m_climbSubsystem.stop(), m_climbSubsystem);

		// Command blueLED = Commands.runOnce(m_ledSubsystem::blue, m_ledSubsystem);
		// Command redLED = Commands.runOnce(m_ledSubsystem::red, m_ledSubsystem);
		// Command greenLED = Commands.runOnce(m_ledSubsystem::green, m_ledSubsystem);

		// Command m_storedNoteLEDCheck = new StoredNoteLEDCheck(m_ledSubsystem, m_intakeSybsystem);

		// SequentialCommandGroup towerIntake = new SequentialCommandGroup(Commands.runOnce(m_deliverySubsystem::towerIntake, m_deliverySubsystem), new WaitCommand(1.5), stopDelivery);

		// Review might have issues
		SequentialCommandGroup playerIntake = new SequentialCommandGroup(
			new InstantCommand(()-> m_deliverySubsystem.towerIntake(), m_deliverySubsystem),
			new InstantCommand(()-> m_intakeSybsystem.intakeReverse(), m_intakeSybsystem)
		);
		SequentialCommandGroup playerIntakeOff = new SequentialCommandGroup(
			new InstantCommand(()-> m_deliverySubsystem.stopDelivery(), m_deliverySubsystem),
			new InstantCommand(()-> m_intakeSybsystem.intakeOff(), m_intakeSybsystem)
		);
		SequentialCommandGroup fullFire = new SequentialCommandGroup(
			new InstantCommand(()-> m_deliverySubsystem.fullDelivery(), m_deliverySubsystem),
			new WaitCommand(0.4),
			new InstantCommand(() -> m_intakeSybsystem.intakeOut(), m_intakeSybsystem),
			new WaitCommand(0.5),
			new InstantCommand(()-> m_deliverySubsystem.stopDelivery(), m_deliverySubsystem),
			new InstantCommand(()-> m_intakeSybsystem.intakeOff(), m_intakeSybsystem));
		SequentialCommandGroup halfFire = new SequentialCommandGroup(
			new InstantCommand(()-> m_deliverySubsystem.halfDelivery(), m_deliverySubsystem), 
			new WaitCommand(1.5), 
			new InstantCommand(() -> m_intakeSybsystem.intakeOut(), m_intakeSybsystem), 
			new WaitCommand(1.5),
			new InstantCommand(()-> m_deliverySubsystem.stopDelivery(), m_deliverySubsystem),
			new InstantCommand(()-> m_intakeSybsystem.intakeOff(), m_intakeSybsystem));

		//controls
		// m_driverController.rightBumper().onTrue(fullDelivery).onFalse(stopDelivery);
		// m_driverController.leftBumper().onTrue(halfDelivery).onFalse(stopDelivery);

		//m_driverController.rightTrigger().onTrue(blueLED).onFalse(redLED);
		//m_driverController.leftTrigger().onTrue(greenLED).onFalse(redLED); id rather a new set of buttons or that you un these checks via periodic
		//cus otherwise these might overwrite delivery
//needs to be tested^



		// Climb
		m_extendButton.onTrue(extendClimb).onFalse(haltClimb);
		m_retractButton.onTrue(retractClimb).onFalse(haltClimb);
		
		// Intake
		m_towerIntakeButton.whileTrue(playerIntake).onFalse(playerIntakeOff);
		// m_towerIntakeButton.onTrue(towerIntake);
		m_intakeOnButton.whileTrue(m_IntakeCommands.intakeOn()).onFalse(m_IntakeCommands.intakeOff());
		// m_intakeOnButton.onTrue(blueLED); 
		m_intakeOutButton.onTrue(intakeOut);
		
		// Firing
		m_slowShotButton.onTrue(halfFire);
		m_fireShotButton.onTrue(fullFire);
		

		// m_ledSubsystems.setDefaultCommand(m_storedNoteLEDCheck);

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

		SequentialCommandGroup autosFullFire = new SequentialCommandGroup(
			new InstantCommand(()-> m_deliverySubsystem.fullDelivery(), m_deliverySubsystem),
			new WaitCommand(0.4),
			new InstantCommand(() -> m_intakeSybsystem.intakeOut(), m_intakeSybsystem),
			new WaitCommand(0.5),
			new InstantCommand(()-> m_deliverySubsystem.stopDelivery(), m_deliverySubsystem),
			new InstantCommand(()-> m_intakeSybsystem.intakeOff(), m_intakeSybsystem));

		SequentialCommandGroup autosHalfFire = new SequentialCommandGroup(
			new InstantCommand(()-> m_deliverySubsystem.halfDelivery(), m_deliverySubsystem), 
			new WaitCommand(1.5), 
			new InstantCommand(() -> m_intakeSybsystem.intakeOut(), m_intakeSybsystem), 
			new WaitCommand(1.5),
			new InstantCommand(()-> m_deliverySubsystem.stopDelivery(), m_deliverySubsystem),
			new InstantCommand(()-> m_intakeSybsystem.intakeOff(), m_intakeSybsystem));

		switch (AutoConstants.kAutoMode) {
			default :
			case 0 : //Auto Command to leave
				return swerveControllerCommand;
			case 1: 
				return new SequentialCommandGroup(autosFullFire, swerveControllerCommand) ;
			case 2:
				return new SequentialCommandGroup(autosHalfFire, swerveControllerCommand);
		}
	}
	}