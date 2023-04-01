package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmPositionCommand extends CommandBase {
    private final ArmSubsystem armSubsystem;
    private final int position;

    public ArmPositionCommand(ArmSubsystem armSubsystem, int position) {
        this.armSubsystem = armSubsystem;
        this.position = position;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        switch(position) {
            case 1:
                armSubsystem.moveJointToPosition1();
                break;
            case 2:
                armSubsystem.moveJointToPosition2();
                break;
            case 3:
                armSubsystem.moveJointToPosition3();
                break;
            default:
                break;
        }
    }

    @Override
    public void execute() {
        // No-op
    }

    @Override
    public void end(boolean interrupted) {
        // No-op
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
