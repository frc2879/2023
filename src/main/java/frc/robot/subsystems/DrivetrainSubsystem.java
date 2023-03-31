package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
    private TalonFX rightMotor;
    private TalonFX leftMotor;
    
    public DrivetrainSubsystem() {
        this.rightMotor = new TalonFX(Constants.RobotConstants.kDrivetrainRightMotor);
        this.leftMotor = new TalonFX(Constants.RobotConstants.kDrivetrainLeftMotor);

        rightMotor.configOpenloopRamp(2);
        leftMotor.configOpenloopRamp(2);
    }

    public void moveTank(double throttle, double yaw) {
        setAllWheels(getMotorSpeeds(throttle, yaw));
    }

    private void setAllWheels(double[] wheelSpeeds) {
        setEachWheel(wheelSpeeds[0], wheelSpeeds[1]);
    }

    private void setEachWheel(double right, double left) {
        rightMotor.set(TalonFXControlMode.PercentOutput,right);
        leftMotor.set(TalonFXControlMode.PercentOutput,left);
    }

    private double[] getMotorSpeeds(double throttle, double yaw) {
        double[] motion = {throttle, yaw};
        int[][] wheelBooleans = {{1, -1},{-1, -1}};
        double[] wheelSpeeds = {0, 0};
        for(int w = 0; w < 2; w++) {
            for(int b = 0; b < 2; b++) { 
                wheelSpeeds[w]+=(wheelBooleans[w][b]*motion[b]);
            }
        }
        return wheelSpeeds;
    }
}
