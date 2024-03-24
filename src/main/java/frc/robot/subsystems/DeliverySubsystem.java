// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.DeliveryConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.PneumaticsConstants;
import frc.robot.Constants.PortConstants;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Joystick;

//note : functions as flywheels
public class DeliverySubsystem extends SubsystemBase {
	CANSparkMax LowerSparkMax = new CANSparkMax(PortConstants.kSparkMaxLDeliveryPort, MotorType.kBrushless);
	CANSparkMax UpperSparkMax = new CANSparkMax(PortConstants.kSparkMaxUDeliveryPort, MotorType.kBrushless);
	// PneumaticHub PneumaticsHub = new PneumaticHub(PneumaticsConstants.kRevPneumaticPort);
	// DoubleSolenoid Solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, PneumaticsConstants.kPistonFwdPort, PneumaticsConstants.kPistonRevPort);
	/** Creates a new DeliverySubsystem. */
	public DeliverySubsystem() {}

	@Override
	public void periodic() {

	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void fullDelivery(Joystick p_Joystick){
		Joystick tempJoystick = p_Joystick;
		System.out.println("full delivery running");
		if (tempJoystick.getRawAxis(4) > -OIConstants.kDelSpeedChange) { //if you havent flipped the switch up, fires at 0.6
			LowerSparkMax.set(-DeliveryConstants.kDeliveryFullSpeed);
			UpperSparkMax.set(-DeliveryConstants.kDeliveryFullSpeed - 0.1);
		} else { //if you have flipped the switch up, fires at 0.7 
			LowerSparkMax.set(-DeliveryConstants.kDeliveryFullSpeed - 0.1);
			UpperSparkMax.set(-DeliveryConstants.kDeliveryFullSpeed - 0.2);
		}
	}
	public void halfDelivery(){
		// Solenoid.set(DoubleSolenoid.Value.kForward);
		LowerSparkMax.set(-DeliveryConstants.kDeliveryHalfSpeed);
		UpperSparkMax.set(-DeliveryConstants.kDeliveryHalfSpeed);

	}

	public void stopDelivery(){
		// Solenoid.set(DoubleSolenoid.Value.kReverse);
		LowerSparkMax.set(DeliveryConstants.kDeliveryOffSpeed);
		UpperSparkMax.set(DeliveryConstants.kDeliveryOffSpeed);
	}

	public void towerIntake() {
		LowerSparkMax.set(0.2);
		UpperSparkMax.set(0.2);
	}

	// public void disablePiston() {
	// 	Solenoid.set(DoubleSolenoid.Value.kOff);
	// }

	// public void toggleCompressor() {
	// 	if(PneumaticsHub.getCompressor()){
	// 		PneumaticsHub.disableCompressor();
	// 	  }
	// 	  else{
	// 		PneumaticsHub.enableCompressorAnalog(PneumaticsConstants.kMinPressure, PneumaticsConstants.kMaxPressure);
	// 	  }
	// }
}
