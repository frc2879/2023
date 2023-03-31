// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArmPositionCommand;
import frc.robot.commands.ArmZeroCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.CloseClawCommand;
import frc.robot.commands.MoveClawCommand;
import frc.robot.commands.OpenClawCommand;
import frc.robot.commands.RetractArmCommand;
import frc.robot.commands.SlideArmCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final ClawSubsystem claw_subsystem = new ClawSubsystem();
  public final DrivetrainSubsystem drivetrain_subsystem = new DrivetrainSubsystem();
  public final ArmSubsystem armSubsystem = new ArmSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.rightBumper().whileTrue(new MoveClawCommand(claw_subsystem, 0.2));
    m_driverController.rightBumper().onFalse(new MoveClawCommand(claw_subsystem, 0));
    m_driverController.leftBumper().onTrue(new OpenClawCommand(claw_subsystem)).debounce(2);
    m_driverController.leftTrigger().whileTrue(new SlideArmCommand(armSubsystem, 0.5));
    m_driverController.rightTrigger().whileTrue(new SlideArmCommand(armSubsystem, -0.5));
    m_driverController.leftTrigger().onFalse(new SlideArmCommand(armSubsystem, 0));
    m_driverController.rightTrigger().onFalse(new SlideArmCommand(armSubsystem, 0));
    m_driverController.a().onTrue(new RetractArmCommand(armSubsystem).andThen(new ArmPositionCommand(armSubsystem, 1)));
    m_driverController.b().onTrue(new ArmPositionCommand(armSubsystem, 2));
    m_driverController.y().onTrue(new ArmPositionCommand(armSubsystem, 3));
    m_driverController.x().onTrue(new RetractArmCommand(armSubsystem).andThen(new ArmZeroCommand(armSubsystem)));
  }

  /**d
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
