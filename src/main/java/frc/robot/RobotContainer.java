package frc.robot;

import java.util.ResourceBundle.Control;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.gyro.Gyro;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.poseTracker.PoseTracker;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.commands.MoveToPos;
import frc.robot.subsystems.arm.commands.RunToSetpoints;
import frc.robot.subsystems.drivetrain.commands.TeleopDrive;
import frc.robot.util.Controller;
import frc.robot.util.ControllerUtils;
import frc.robot.util.DriverController;
import frc.robot.util.ManipulatorController;
import frc.robot.util.DriverController.Mode;
public class RobotContainer {
  /* Controllers */
  private final DriverController driverController = new DriverController(0);
  private final ManipulatorController manipulatorController = new ManipulatorController(1);

  /* Subsystems */
  private Drivetrain drivetrain = new Drivetrain();
  private Arm arm = new Arm();
  private Intake intake = new Intake();
  private Gyro gyro = new Gyro();
  private PoseTracker poseTracker = new PoseTracker(drivetrain);
    
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    this.drivetrain.setDefaultCommand(new TeleopDrive(drivetrain, driverController));

    this.arm.setDefaultCommand(new RunToSetpoints(arm));

    this.intake.setDefaultCommand(new RunCommand(intake::off, intake));

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // intake
    Controller.onHold(driverController.RightTrigger, new RunCommand(intake::forward, intake));
    Controller.onHold(driverController.LeftTrigger, new RunCommand(intake::backward, intake));
    Controller.onHold(manipulatorController.intakeElementTrigger, new RunCommand(intake::forward));
    Controller.onHold(manipulatorController.outtakeElementTrigger, new RunCommand(intake::backward));

    // toggle cube
    Controller.onPress(manipulatorController.RightBumper, new InstantCommand(() -> { this.arm.armMode = true; }));
    // toggle cone
    Controller.onPress(manipulatorController.LeftBumper, new InstantCommand(() -> { this.arm.armMode = false; }));

    // ground
    Controller.onPress(manipulatorController.A, new MoveToPos(arm, Constants.Arm.Positions.Ground.kAnchor, Constants.Arm.Positions.Ground.kFloating));
    // contract
    Controller.onPress(manipulatorController.B, new MoveToPos(arm, Constants.Arm.Positions.Contracted.kAnchor, Constants.Arm.Positions.Contracted.kFloating));
    // mid
    Controller.onPress(manipulatorController.X, new ConditionalCommand(
      // cube (from shelf)
      new MoveToPos(arm, Constants.Arm.Positions.Shelf.kAnchor, Constants.Arm.Positions.Shelf.kFloating),
      //cone (mid)
      new MoveToPos(arm, Constants.Arm.Positions.MiddleCone.kAnchor, Constants.Arm.Positions.MiddleCone.kFloating),
      () -> this.arm.armMode
    ));
    // high
    Controller.onPress(manipulatorController.Y, new ConditionalCommand(
      // cube (high)
      new MoveToPos(arm, Constants.Arm.Positions.Cube.kAnchor, Constants.Arm.Positions.Cube.kFloating),
      // cone (high)
      new MoveToPos(arm, Constants.Arm.Positions.HighCone.kAnchor, Constants.Arm.Positions.HighCone.kFloating),
      () -> this.arm.armMode
    ));

    // dynamic for tuning
    // SmartDashboard.putNumber("anchor-setpoint", 13.0);
    // SmartDashboard.putNumber("floating-setpoint", 22.0);
    // Controller.onPress(manipulatorController.X, new MoveToPos(
    //   arm,
    //   () -> ControllerUtils.clamp(SmartDashboard.getNumber("anchor-setpoint", 0.0), 13.0, 95.0),
    //   () -> ControllerUtils.clamp(SmartDashboard.getNumber("floating-setpoint", 0.0), 22.0, 180.0)
    // ));

    //slow mode
    Controller.onHold(driverController.RightBumper, new InstantCommand(() -> driverController.setSlowMode(Mode.SLOW)));
    Controller.onRelease(driverController.RightBumper, new InstantCommand(() -> driverController.setSlowMode(Mode.NORMAL)));
  }

  public Command getAutonomousCommand() {
    return new InstantCommand(() -> {});
  }

  public void doSendables() {
  }
}
