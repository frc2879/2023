package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class RetractArmCommand extends CommandBase{
    private final ArmSubsystem armSubsystem;

    public RetractArmCommand(ArmSubsystem arm) {
        
        this.armSubsystem = arm;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        armSubsystem.slide(0.5);
    }

    @Override
    public void end(boolean interrupted) {
            armSubsystem.slide(0);
    }

    @Override
    public boolean isFinished() {
        return armSubsystem.slideFwdLimitIsPressed();
    }
}
