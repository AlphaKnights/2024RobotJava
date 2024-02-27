// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoCommands;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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

	// Replace with CommandPS4Controller or CommandJoystick if needed
	private final CommandXboxController m_driverController =
		new CommandXboxController(OIConstants.kDriverControllerPort);

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		// Configure the trigger bindings
		configureBindings();
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
		// An example command will be run in autonomous
		return AutoCommands.exampleAuto(m_ExampleSubsystem);
	}
}