// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants.PneumaticConstants;

public class PneumaticsSubsystem extends SubsystemBase {
  PneumaticHub pneumaticHub = new PneumaticHub();
  DoubleSolenoid m_doubleSolenoid1 = new DoubleSolenoid(null, PneumaticConstants.fwdChannel, PneumaticConstants.revChannel);
  DoubleSolenoid m_doubleSolenoid2 = new DoubleSolenoid(null, PneumaticConstants.fwdChannel, PneumaticConstants.revChannel);

  Compressor m_compressor = new Compressor(null);
  /** Creates a new ExampleSubsystem. */
  public PneumaticsSubsystem() {
}

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command disablePistonCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          this.disablePiston();

        });
  }
  public Command enablePistonCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          this.enablePiston();

        });
  }
  public Command togglePistonCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          this.togglePiston();

        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void disablePiston(){
    m_doubleSolenoid1.set(Value.kOff);
    m_doubleSolenoid2.set(Value.kOff);

  }
  public void togglePiston(){
    m_doubleSolenoid1.toggle();
    m_doubleSolenoid2.toggle();

  }
  public void enablePiston() {
    m_doubleSolenoid1.set(Value.kReverse);
    m_doubleSolenoid2.set(Value.kReverse);

  }
  public void disableCompressor(){
    m_compressor.disable();
  }
  public void enableCompressor(){
    m_compressor.enableDigital();
  }

  
}
