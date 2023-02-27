package frc.robot.subsystems.arm.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.arm.Arm;

public class MoveToPos extends SequentialCommandGroup {
    public MoveToPos(Arm arm, double desiredAnchor, double desiredFloating){
        addCommands(
            new SequentialCommandGroup(
                new MoveFloatingJoint(() -> Constants.Arm.Floating.kContracted, arm),
                new MoveAnchorJoint(() -> desiredAnchor, arm),
                new MoveFloatingJoint(() -> desiredFloating, arm)
            )
        );

        addRequirements(arm);
    }
}
