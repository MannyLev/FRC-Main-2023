import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class PIDTesting extends PIDCommand {

    public PIDTesting(Arm arm) {
        new PIDController(Constants.Arms.AnchorJoint.kP, Constants.Arms.AnchorJoint.kI, Constants.Arms.AnchorJoint.kD),
        () -> {
            return Math.sin(arm.getAnchorAngleFromEncoder());
        },
        () -> setpoint,
            (outputPower) -> {
                SmartDashboard.putNumber("Output", outputPower);
                arm.setAnchorMotorPower(0, -outputPower / 100);
            },
            arm
    }
}