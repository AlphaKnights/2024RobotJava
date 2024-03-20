// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import javax.sound.sampled.Port;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.PortConstants;
import frc.robot.commands.IntakeCommands;
import frc.robot.Constants.IntakeConstants;
//import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.Constants.OIConstants;

public class IntakeSubsystem extends SubsystemBase {
	 Ultrasonic m_ultrasonic = new Ultrasonic(PortConstants.kIntakeUltrasonicPort, PortConstants.kIntakeUltrasonicEchoPort);
	 CANSparkMax m_sparkMax = new CANSparkMax(PortConstants.kSparkMaxLIntakePort, MotorType.kBrushless);
	 CANSparkMax m_sparkMaxU = new CANSparkMax(PortConstants.kSparkMaxUIntakePort, MotorType.kBrushless);
	 public int switchCounter = 0;
	 boolean lastTickInput = false;
	/** Creates a new DeliverySubsystem. */
	public IntakeSubsystem() {}

	@Override
	public void periodic() { // Runs every 20ms
		//if (m_ultrasonic.getRangeInches() <= IntakeConstants.kUltrasonicDetectDist) {
	 		//  switchCounter += 1;
	 	//} else {
	 	//	intakeOff();
	 	//}
	 	// lastTickInput = m_ultrasonic.getValue() <= 1;
				//don't think you can do that^
	}
	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void intakeOn(){
		if(m_ultrasonic.getRangeInches() < IntakeConstants.kUltrasonicDetectDist){
			m_sparkMax.setInverted(true);
			m_sparkMax.set(IntakeConstants.kIntakeOnSpeed);
			m_sparkMaxU.setInverted(true);
			m_sparkMaxU.set(IntakeConstants.kIntakeOnSpeed);
		}
	}

	public void intakeOut(){
		m_sparkMax.setInverted(true);
		m_sparkMax.set(IntakeConstants.kIntakeOnSpeed);
		m_sparkMaxU.setInverted(true);
		m_sparkMaxU.set(IntakeConstants.kIntakeOnSpeed);
	}

	public void intakeOff(){
		m_sparkMax.set(IntakeConstants.kIntakeOffSpeed);
		m_sparkMaxU.set(IntakeConstants.kIntakeOffSpeed);

	}

	public void intakeReverse() {
		m_sparkMax.set(-0.2);
		m_sparkMaxU.set(-0.2);
	}
}
