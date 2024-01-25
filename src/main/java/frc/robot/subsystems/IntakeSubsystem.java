// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import Constants.MotorConstants;

public class IntakeSubsystem extends SubsystemBase {

	CANSparkMax sparkMax = new CANSparkMax(kSparkMaxIntakePort, MotorType.brushless);
	/** Creates a new DeliverySubsystem. */
	public IntakeSubsystem() {}

	// DONT USE UNLESS VERY SPECIFIC PURPOSE
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void intakeOn(){
		sparkMax.set(MotorConstants.kIntakeOnSpeed);
	}

	public void intakeOff(){
		sparkMax.set(MotorConstants.kIntakeOffSpeed)
	}
}
