package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Ultrasonic;

public class IntakeCommands {

    IntakeSubsystem intakeSubsystem;
    
    public IntakeCommands(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
    }

    public InstantCommand intakeOn(Ultrasonic m)
    {
        return new InstantCommand(() -> intakeSubsystem.intakeOn(m), intakeSubsystem);
    }

    public InstantCommand intakeOff()
    {
        return new InstantCommand(() -> intakeSubsystem.intakeOff(), intakeSubsystem);
    }
}
