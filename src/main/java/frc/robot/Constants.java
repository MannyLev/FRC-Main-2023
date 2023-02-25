package frc.robot;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;

public final class Constants {
    public static final class Arms {

        public static final class FloatingJoint{
            public static final double kP = 0.1;
            public static final double kI = 0.0;
            public static final double kD = 0.0;
            public static final double kFF = 0.0;
            public static final double kMinAngle = 22.0;
            public static final double kMaxAngle = 180.0;
            public static double kFloatingJointErrorThreshold = 0.0;
        }

        public static final class AnchorJoint{
            public static final double kP = 0.1;
            public static final double kI = 0.0;
            public static final double kD = 0.0;
            public static final double kFF = 0.0;
            public static final double kMinAngle = 13.0;
            public static final double kMaxAngle = 85.0;
            public static double kanchorJointErrorThreshold = 0.0;
        }

        public static final class Ports {
            public static final int kAnchorJointPort = 0;
            public static final int kFloatingArmPort = 9;
            public static final int kAnchorLimitSwitchPort = 0;
            public static final int kFloatingLimitSwitchPort = 0;
        }

        public static final class Positions{
            public static final double kLowFloatingCube = 84.5;
            public static final double kLowFloatingCone = 75.4;
            public static final double kLowAnchor = 13.0;
            public static final double kMidNodeFloating = 78.1;
            public static final double kMidNodeAnchor = 51.0;
            public static final double kMidShelfFloating = 96.5;
            public static final double kMidShelfAnchor = 45.6;
            public static final double kHighNodeFloating = 139.6;
            public static final double kHighNodeAnchor = 86.5;
            public static final double kHighShelfFloating = 159.6;
            public static final double kHighShelfAnchor = 81.6;
            public static final double kIntakeShelfFloating = 0.0;
            public static final double kIntakeShelfAnchor = 0.0; 
        }
        
        public static final class Miscellaneous {
            public static final double kAnchorArmLength = 101.6;
            public static final double kFloatingArmLength = 40.64;
            public static final double kDegreesPerTick = 360 / 42;
            public static final double kContractedAnchorAngle = 13.5;
            public static final double kContractedFloatingAngle = 24.0;
            public static final double kUndershotAngle = 0.0;
            public static final double kLowPower = 0.05;
            public static final double distanceBetweenPivotLimelight = 48.26;
            public static final boolean kAnchorMotorInverted = false;
            public static final boolean kFloatingMotorInverted = false;
        }
    }

  public static  class Grabber {
        public static int kPistonDeploy = 0;
        public static int kPistonRetract = 1;
        public static int kGrabberSensor;
        public static int kBits;
        public static int kChannel;
        public static double kMax;
        public static double kStart;
        public static double kMin;
    }

    public static final double kThrottleFilter = 1.25;
    public static final double kTurnFilter = 3;
    public static final int kGyroPort = 1;

    public static class Trajectory {
        public static final double ksVolts = 0.131;
        public static final double ksVoltSecondsPerMeter =  4.03;
        public static final double kaVoltSecondsSquaredPerMeter = 0.521;

        public static final double kTrackWidthMeters = 0.702;
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackWidthMeters);

        public static final double kMaxSpeedMetersPerSecond = 2.5;
        public static final double kMaxAccelerationMetersPerSecondSquared = 2;

        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;

        public static final double kPDriveVel = 0;


        public static final double kGearRatio = 6.8027597438; 
        public static final double kWheelRadiusInches = 3; 
        public static final double kMetersPerRot = Units.inchesToMeters(2 * Math.PI * kWheelRadiusInches / kGearRatio); 
        // (3.072/100);
        // Units.inchesToMeters(kGearRatio * 2 * kWheelRadiusInches * Math.PI / 42); 

        public static final double kMetersPerSecondPerRPM = kMetersPerRot / 60; 
        

    }
    public static class Drivetrain {
        public static class LeftMotors {
            public static final int kLeftMotor1_Port = 10;
            public static final int kLeftMotor2_Port = 15;
            public static final int kLeftMotor3_Port = 11;
        }
        public static class RightMotors {
            public static final int kRightMotor1_Port = 19;
            public static final int kRightMotor2_Port = 18; 
            public static final int kRightMotor3_Port = 17;
        }

        public static final int kMaxAmps = 30; 
        public static final double kThrottleMultiplier = 0.75;
        public static final double kTurnMultiplier = 0.6;
    }

    /*TODO: provide the constants */

    // referenced in frc.robot.subsystems.gyro.Balance but never defined; TODO: edit these values
    public static class BalanceConstants {
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kErrorThreshold = 1;
    }
}
