// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.PortConstants;
import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeSubsystem extends SubsystemBase {
	 DigitalInput m_limitSwitch = new DigitalInput(PortConstants.kIntakeLimitSwitchPort);
	 CANSparkMax m_sparkMax = new CANSparkMax(PortConstants.kSparkMaxIntakePort, MotorType.kBrushless);
	 public int switchCounter = 0;
	 boolean lastTickInput = false;
	/** Creates a new DeliverySubsystem. */
	public IntakeSubsystem() {}

	@Override
	public void periodic() { // Runs every 20ms
		if (m_limitSwitch.get() && !lastTickInput) {
			 switchCounter += 1;
		}
		if (switchCounter == 2) {
			switchCounter = 0;
			intakeOff();
		}
		lastTickInput = m_limitSwitch.get();
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void intakeOn(){
		if( switchCounter != 2 ){
			m_sparkMax.setInverted(true);
			m_sparkMax.set(IntakeConstants.kIntakeOnSpeed);
		}
	}

	public void intakeOff(){
		m_sparkMax.set(IntakeConstants.kIntakeOffSpeed);
	}

	public void intakeReverse() {
		m_sparkMax.setInverted(true);
		m_sparkMax.set(IntakeConstants.kIntakeOnSpeed);
	}
}
