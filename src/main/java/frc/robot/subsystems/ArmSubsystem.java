package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax jointMotor;
    private final TalonSRX extensionMotor;
    private final SparkMaxPIDController jointPIDController;
    private final SparkMaxLimitSwitch jointFwdLimitSwitch;
    private final RelativeEncoder encoder;

    private double tare;

    public ArmSubsystem() {
        // Initialize joint motor with Spark Max controller on CAN bus
        jointMotor = new CANSparkMax(Constants.RobotConstants.shoulderMotor, MotorType.kBrushless);
        jointMotor.restoreFactoryDefaults();

        jointFwdLimitSwitch = jointMotor.getForwardLimitSwitch(Type.kNormallyOpen);
        encoder  = this.jointMotor.getEncoder();
        
        tare = 0;

        // Initialize extension motor with Talon SRX controller on CAN bus
         extensionMotor = new TalonSRX(Constants.RobotConstants.extendMotor);
         extensionMotor.configFactoryDefault();

        // Configure joint motor for Motion Control
        jointPIDController = jointMotor.getPIDController();
        jointPIDController.setP(0.02); // set P gain
        jointPIDController.setI(0); // set I gain
        jointPIDController.setD(0.005); // set D gain
        jointPIDController.setFF(0.0005); // set feedforward gain
        jointPIDController.setOutputRange(-0.5, 0.5); // set output range
    }

    public void moveJoint(double speed) {
        jointMotor.set(speed);
    }

    // public void moveExtension(double speed) {
    //     extensionMotor.set(ControlMode.PercentOutput, speed);
    // }

    public void moveJointToPosition1() {
        jointPIDController.setReference(tare - 5, ControlType.kPosition);
    }

    public void moveJointToPosition2() {
        jointPIDController.setReference(tare - 10, ControlType.kPosition);
    }

    public void moveJointToPosition3() {
        jointPIDController.setReference(tare - 20, ControlType.kPosition);
    }

    public boolean fwdLimitIsPressed() {
       return this.jointFwdLimitSwitch.isPressed();
    }

    public void slowMoveDown() {
        this.jointMotor.set(0.05);
    }

    public void setArmTare() {
        this.tare = this.encoder.getPosition();
    }

    public void slide(double pct) {
        this.extensionMotor.set(ControlMode.PercentOutput, pct);
    }

    public void extend(){
        this.extensionMotor.set(ControlMode.PercentOutput, 0.5);
    }

    public void retract() {
        this.extensionMotor.set(ControlMode.PercentOutput, -0.5);
    }

    public boolean slideFwdLimitIsPressed() {
        return this.extensionMotor.isFwdLimitSwitchClosed() > 0;
    }

    public boolean slideRevLimitIsPressed() {
        return this.extensionMotor.isRevLimitSwitchClosed() > 0;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}