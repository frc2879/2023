package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax jointMotor;
    private final TalonSRX extensionMotor;
    private final SparkMaxPIDController jointPIDController;

    public ArmSubsystem() {
        // Initialize joint motor with Spark Max controller on CAN bus
        jointMotor = new CANSparkMax(1, MotorType.kBrushless);
        jointMotor.restoreFactoryDefaults();

        // Initialize extension motor with Talon SRX controller on CAN bus
        extensionMotor = new TalonSRX(1);
        extensionMotor.configFactoryDefault();

        // Configure joint motor for Motion Control
        jointPIDController = jointMotor.getPIDController();
        jointPIDController.setP(5e-5); // set P gain
        jointPIDController.setI(1e-6); // set I gain
        jointPIDController.setD(0); // set D gain
        jointPIDController.setFF(0.00118); // set feedforward gain
        jointPIDController.setOutputRange(-1, 1); // set output range

        int smartMotionSlot = 0;
        jointPIDController.setSmartMotionMaxVelocity(2000, smartMotionSlot);
        jointPIDController.setSmartMotionMaxAccel(1000, smartMotionSlot);
        jointPIDController.setSmartMotionAllowedClosedLoopError(0, smartMotionSlot);
    }

    public void moveJoint(double speed) {
        jointMotor.set(speed);
    }

    public void moveExtension(double speed) {
        extensionMotor.set(ControlMode.PercentOutput, speed);
    }

    public void moveJointToPosition1() {
        jointPIDController.setReference(1000, ControlType.kSmartMotion);
    }

    public void moveJointToPosition2() {
        jointPIDController.setReference(2000, ControlType.kSmartMotion);
    }

    public void moveJointToPosition3() {
        jointPIDController.setReference(3000, ControlType.kSmartMotion);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}