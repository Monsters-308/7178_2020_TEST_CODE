/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {
  private static final int kRearLeftChannel = 1;
  private static final int kFrontLeftChannel = 2;
  private static final int kRearRightChannel = 3;
  private static final int kFrontRightChannel = 4;

  private static final int kJoystickChannel = 0;
  

  private DifferentialDrive m_robotDrive;
  private Joystick m_stick;

  @Override
  public void robotInit() {
    
    final WPI_TalonSRX frontLeft = new WPI_TalonSRX(kFrontLeftChannel);
    final WPI_TalonSRX rearLeft = new WPI_TalonSRX(kRearLeftChannel);
    final WPI_TalonSRX frontRight = new WPI_TalonSRX(kFrontRightChannel);
    final WPI_TalonSRX rearRight = new WPI_TalonSRX(kRearRightChannel);

    SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeft, rearLeft);
    SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRight, rearRight);

    // Invert the left side motors.
    // You may need to change or remove this to match your robot.
    
    m_robotDrive = new DifferentialDrive(leftSide, rightSide);

    m_stick = new Joystick(kJoystickChannel);
    
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
    m_robotDrive.tankDrive(m_stick.getRawAxis(5), m_stick.getRawAxis(1));
  }
}
