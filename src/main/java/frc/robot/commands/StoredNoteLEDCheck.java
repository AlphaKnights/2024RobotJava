// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LedSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class StoredNoteLEDCheck extends Command {
	@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
	private final LedSubsystem m_LedSubsystem;
    private final IntakeSubsystem m_intakeSubsystem;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public StoredNoteLEDCheck(LedSubsystem p_LedSubsystem, IntakeSubsystem p_IntakeSubsytem) {
		m_LedSubsystem = p_LedSubsystem;
        m_intakeSubsystem = p_IntakeSubsytem;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(p_LedSubsystem, p_IntakeSubsytem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
        if (m_intakeSubsystem.switchCounter >= 1) {
            m_LedSubsystem.green();
        } else {
            m_LedSubsystem.red();
        }
    }

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
