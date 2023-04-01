package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmZeroCommand extends CommandBase{ 
    private final ArmSubsystem armSubsystem;

    public ArmZeroCommand(ArmSubsystem arm) {
        this.armSubsystem = arm;
    }

    @Override
    public void initialize() {
        this.armSubsystem.slowMoveDown();
    }

    @Override
    public void execute() {
        // No-op
    }

    @Override
    public void end(boolean interrupted) {
        if(armSubsystem.fwdLimitIsPressed()) {
            armSubsystem.setArmTare();
        } else {
            System.out.println("WARN: Arm zero command finished without reaching zero!!!");
        }
    }

    @Override
    public boolean isFinished() {
        return this.armSubsystem.fwdLimitIsPressed();
    }
}
