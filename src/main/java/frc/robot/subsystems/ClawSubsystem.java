// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.fasterxml.jackson.core.io.OutputDecorator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {

  private TalonSRX clawMotor;

  /** Creates a new ExampleSubsystem. */
  public ClawSubsystem() {
    clawMotor = new TalonSRX(5);
  }

  public void moveClawClosed() {
    this.clawMotor.set(ControlMode.PercentOutput, 0.30);
  }

  public void moveClawOpen() {
    this.clawMotor.set(ControlMode.PercentOutput, -0.30);
  }

  public void stopClaw() {
    this.clawMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean fwdLimit() {
    return this.clawMotor.isFwdLimitSwitchClosed() > 0;
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
