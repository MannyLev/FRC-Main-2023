package frc.robot.subsystems.arm.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.time.chrono.IsoChronology;
import frc.robot.Constants;

import frc.robot.subsystems.arm.Arm;

public class MoveAnchorJoint extends CommandBase{
    public double desiredAngle;
    public Arm arm;

    public MoveAnchorJoint(double desiredAngle, Arm arm) {
        this.desiredAngle = desiredAngle;
        this.arm = arm;

    }

    @Override
    public void execute() {
        arm.setAnchorMotorPower(Constants.Arms.AnchorJoint.kP * (Math.sin(arm.getAnchorAngleFromEncoder() * Math.PI / 180.0) - Math.sin(this.desiredAngle)) + Constants.Arms.anchorJointAngle.kFF * Math.sin(this.desiredAngle));
    }

    @Override
    public boolean isFinished() {
        return arm.isAnchorAtAngle(Constants.Arms.Miscellaneous.maxAngleThreshold) && (arm.getAnchorMotorPower < Constant.Arms.Miscellaneous.minOscillationThreshold));
    }
}
