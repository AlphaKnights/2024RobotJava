// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.Constants.PortConstants;


public class LedSubsystem extends SubsystemBase {
    private final AddressableLED m_led = new AddressableLED(PortConstants.PWMPort);
	private final AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(60);     
    

	/** Creates a new ExampleSubsystem. */
	public LedSubsystem() {
        m_led.setLength(m_ledBuffer.getLength());
    }

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}


    public void red(){
        for(var i = 0; i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 255, 0, 0);
        }
        m_led.setData(m_ledBuffer);

    }
    public void green(){
        for(var i = 0; i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 0, 255, 0);
        }
        m_led.setData(m_ledBuffer);
    }
    public void blue(){
        for(var i = 0; i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 0, 0, 255);
        }
        m_led.setData(m_ledBuffer);

    }


    

}
