package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class SlideArmCommand extends CommandBase {
    private final ArmSubsystem armSubsystem;
    private final double power;

    public SlideArmCommand(ArmSubsystem arm, double power) {
        this.armSubsystem = arm;
        this.power = power;
    }

    @Override
    public void initialize() {
        armSubsystem.slide(power);
    }

    @Override
    public void execute() {
        armSubsystem.slide(power);
    }

    @Override
    public void end(boolean interrupted) {
        if(interrupted) {
            armSubsystem.slide(0);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
