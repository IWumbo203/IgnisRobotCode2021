// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto extends SequentialCommandGroup {
  /** Creates a new Autonomous. */
  Timer m_timer = new Timer();
  public Auto(Drivetrain drive, Shooter shoot) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new TurnDegrees(120, drive).withTimeout(1.5),
      new DriveDistance(5, drive),
     // new RunCommand(() -> m_timer.delay(1)),
      new TurnDegrees(90, drive).withTimeout(1.5),
     // new RunCommand(() -> m_timer.delay(1)),
      new RunCommand(() -> shoot.manualSpinMotor(.5), shoot).withTimeout(2)
    );
  }
}
