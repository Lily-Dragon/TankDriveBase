// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.proto.Controller;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
   // Creating all our variables, we will initialize them and set their values later
     //create motors : motorcontrolers are talon srx/ talon fx in code : check pheonix 5 doc
   //create differential drive or arcade drive : check WPILib docs

  private final WPI_TalonSRX m_leftLeader = new WPI_TalonSRX(Constants.leftLeaderCANID);
  private final WPI_TalonSRX m_leftFollower = new WPI_TalonSRX(Constants.leftFollowerCANID);

  private final WPI_TalonSRX m_rightFollower = new WPI_TalonSRX(Constants.rightLeaderCANID);
  private final WPI_TalonSRX m_rightLeader = new WPI_TalonSRX(Constants.rightFollowerCANID);

  private final DifferentialDrive drive;
  
  public DriveSubsystem() {
    //set to factory defaults
     m_leftLeader.configFactoryDefault();
     m_rightLeader.configFactoryDefault();

     //set motors to default to braking
    m_leftLeader.setNeutralMode(NeutralMode.Brake);
    m_rightLeader.setNeutralMode(NeutralMode.Brake);

    //create differential drive
    drive = new DifferentialDrive(m_leftLeader, m_rightLeader);

    //Makes follower motors do the same thing as the leaders so that we don't have to pass arguments for all four
    m_leftFollower.follow(m_leftLeader);
    m_rightFollower.follow(m_rightLeader);

    // invert left motors from the right motors because they are inverted 180 degrees
    m_leftLeader.setInverted(true);
    
    
    
  }

  public void drive(double left, double right) {
    //Drive command
    drive.tankDrive(left, right);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
