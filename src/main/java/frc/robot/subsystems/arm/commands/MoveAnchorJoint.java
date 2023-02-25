package frc.robot.subsystems.arm.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.time.chrono.IsoChronology;
import java.util.function.DoubleSupplier;
import frc.robot.Constants;

import frc.robot.subsystems.arm.Arm;

public class MoveAnchorJoint extends CommandBase{
    public DoubleSupplier desiredAngle;
    public Arm arm;

    public MoveAnchorJoint(DoubleSupplier desiredAngle, Arm arm) {
        this.desiredAngle = desiredAngle;
        this.arm = arm;
        SmartDashboard.putBoolean("anchor stopped", false); 
    }

    @Override
    public void execute() {
        arm.setAnchorMotorPower(Constants.Arm.Anchor.kP * (Math.sin(arm.getAnchorAngleFromEncoder() * Math.PI / 180.0) - Math.sin(this.desiredAngle)) + Constants.Arms.anchorJointAngle.kFF * Math.sin(this.desiredAngle));
    }

    @Override
    public boolean isFinished() {
        return arm.isAnchorAtAngle(Constants.Arm.Miscellaneous.maxAngleThreshold) && (arm.getAnchorMotorPower < Constants.Arm.Miscellaneous.minOscillationThreshold));
    }
}
