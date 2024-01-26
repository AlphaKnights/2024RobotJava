// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.PortConstants;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
	DigitalInput limitSwitch = new DigitalInput(PortConstants.kIntakeLimitSwitchPort);
	CANSparkMax sparkMax = new CANSparkMax(PortConstants.kSparkMaxIntakePort, MotorType.brushless);
	/** Creates a new DeliverySubsystem. */
	public IntakeSubsystem() {}

	@Override
	public void periodic() { // Runs every 20ms
		switch (limitSwitch.get()) {
			case true:
				intakeOff()
			case false:
				intakeOn()
		}
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void intakeOn(){
		sparkMax.set(IntakeConstants.kIntakeOnSpeed);
	}

	public void intakeOff(){
		sparkMax.set(IntakeConstants.kIntakeOffSpeed)
	}
}
