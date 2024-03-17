package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommands {

    IntakeSubsystem intakeSubsystem;
    
    public IntakeCommands(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
    }

    public InstantCommand intakeOn()
    {
        return new InstantCommand(() -> intakeSubsystem.intakeOn(), intakeSubsystem);
    }

    public InstantCommand intakeOff()
    {
        return new InstantCommand(() -> intakeSubsystem.intakeOff(), intakeSubsystem);
    }
}
