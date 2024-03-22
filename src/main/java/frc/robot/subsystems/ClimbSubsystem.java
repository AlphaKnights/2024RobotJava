// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants.ClimbConstants;
//import frc.robot.Constants.OIConstants;
import frc.robot.Constants.PortConstants;
import edu.wpi.first.wpilibj.DigitalInput;


public class ClimbSubsystem extends SubsystemBase {
    private TalonFX m_rightTalon = new TalonFX(PortConstants.kLeftTalonFXClimbId);
    private TalonFX m_leftTalon = new TalonFX(PortConstants.kRightTalonFXClimbId);

	// private DigitalInput m_upperLimitSwitch = new DigitalInput(PortConstants.kClimbUpperLimitSwitchPort);
	// private DigitalInput m_lowerLimitSwitch = new DigitalInput(PortConstants.kClimbLowerLimitSwitchPort);

	public ClimbSubsystem() {}

	/**
	 * Example command factory method.
	 *
	 * @return a command
	 */

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

    public void extendLeft() {
		// if (!m_upperLimitSwitch.get()) {
        	// m_rightTalon.setInverted(false);
        	m_leftTalon.setInverted(false);

        	// m_rightTalon.set(ClimbConstants.kClimbExtendSpeed);
        	m_leftTalon.set(ClimbConstants.kClimbExtendSpeed);
		// }
    }

	public void extendRight() {
		// if (!m_upperLimitSwitch.get()) {
        	m_rightTalon.setInverted(false);
        	// m_leftTalon.setInverted(false);

        	m_rightTalon.set(ClimbConstants.kClimbExtendSpeed);
        	// m_leftTalon.set(ClimbConstants.kClimbExtendSpeed);
		// }
    }

    public void retractLeft() {
		// if (!m_lowerLimitSwitch.get()) {
        	// m_rightTalon.set(ClimbConstants.kClimbRetractsSpeed);
        	m_leftTalon.set(-ClimbConstants.kClimbRetractsSpeed);
		// }
    }

	public void retractRight() {
		// if (!m_lowerLimitSwitch.get()) {
        	m_rightTalon.set(-ClimbConstants.kClimbRetractsSpeed);
        	// m_leftTalon.set(ClimbConstants.kClimbRetractsSpeed);
		// }
    }

    public void stopLeft() {
        // m_rightTalon.stopMotor();
        m_leftTalon.stopMotor();
    }

	public void stopRight() {
        m_rightTalon.stopMotor();
        // m_leftTalon.stopMotor();
    }
}
