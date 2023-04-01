package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class XboxDriveCommand extends CommandBase {
    private final DrivetrainSubsystem m_drivetrain;
    private Supplier<Double> throttle;
    private Supplier<Double> yaw;
    
    public XboxDriveCommand(DrivetrainSubsystem drivetrain, Supplier<Double> throttle, Supplier<Double> yaw) {
        m_drivetrain = drivetrain;
        this.throttle = throttle;
        this.yaw = yaw;


        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drivetrain.moveTank(throttle.get(), yaw.get());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
