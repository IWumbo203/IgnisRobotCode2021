// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveToDistance extends PIDCommand {
  /** Creates a new DriveToDistance. */
  public DriveToDistance(int targetDistance, Drivetrain drive) {
    super(
        // The controller that the command will use
        new PIDController(.05, 0, 0),
        // This should return the measurement
        Limelight::calcDistance,
        // This should return the setpoint (can also be a constant)
        targetDistance,
        // This uses the output
        output -> drive.arcadeDrive(MathUtil.clamp(output, -.2, .2), 0),
          // Use the output here
        drive);
    // Use addRequirements() here to declare subsystem dependencies.
    getController().setTolerance(10, 0);
    
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
