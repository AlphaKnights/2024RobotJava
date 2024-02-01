// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.DeliveryConstants;
import frc.robot.Constants.PortConstants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DeliverySubsystem extends SubsystemBase {
	CANSparkMax sparkMax = new CANSparkMax(PortConstants.kSparkMaxDeliveryPort, MotorType.brushless);
	/** Creates a new DeliverySubsystem. */
	public DeliverySubsystem() {}

	@Override
	public void periodic() {

	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void fullDelivery(){
		sparkMax.set(DeliveryConstants.kDeliveryFullSpeed);
	}

	public void halfDelivery(){
		sparkMax.set(DeliveryConstants.kDeliveryHalfSpeed)
	}

	public void stopDelivery(){
		sparkMax.set(DeliveryConstants.kDeliveryOffSpeed)
	}
}
