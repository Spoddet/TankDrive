package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase{
    
    // Defines hardware class
    public static class Hardware {
        private WPI_TalonSRX lFrontMotor, lMidMotor, lRearMotor, rFrontMotor, rMidMotor, rRearMotor;

    public Hardware(
        WPI_TalonSRX lFrontMotor,
        WPI_TalonSRX lMidMotor,
        WPI_TalonSRX lRearMotor,
        WPI_TalonSRX rFrontMotor,
        WPI_TalonSRX rMidMotor,
        WPI_TalonSRX rRearMotor
    ) {
        this.lFrontMotor = lFrontMotor;
        this.lMidMotor = lMidMotor;
        this.lRearMotor = lRearMotor;
        this.rFrontMotor = rFrontMotor;
        this.rMidMotor = rMidMotor;
        this.rRearMotor = rRearMotor;
    }
}

    // Initializes motors
    private WPI_TalonSRX m_lFrontMotor, m_lMidMotor, m_lRearMotor, m_rFrontMotor, m_rMidMotor, m_rRearMotor;
    private DifferentialDrive m_drivetrain;

    /**
   * Create an instance of DriveSubsystem
   * <p>
   * NOTE: ONLY ONE INSTANCE SHOULD EXIST AT ANY TIME!
   * <p>
   * 
   * @param drivetrainHardware   Hardware devices required by drivetrain
   */
  public DriveSubsystem(Hardware drivetrainHardware) {

    // Instantiates motors
    this.m_lFrontMotor = drivetrainHardware.lFrontMotor;
    this.m_lMidMotor = drivetrainHardware.lMidMotor;
    this.m_lRearMotor = drivetrainHardware.lRearMotor;

    this.m_rFrontMotor = drivetrainHardware.rFrontMotor;
    this.m_rMidMotor = drivetrainHardware.rMidMotor;
    this.m_rRearMotor = drivetrainHardware.rRearMotor;

    // Sets right motors inverted
    m_rFrontMotor.setInverted(true);
    m_rMidMotor.setInverted(true);
    m_rRearMotor.setInverted(true);

    // Makes back motors follow front motors
    m_lMidMotor.follow(m_lFrontMotor);
    m_lRearMotor.follow(m_lFrontMotor);
    m_rMidMotor.follow(m_rFrontMotor);
    m_rRearMotor.follow(m_rFrontMotor);

    // Creates differential drive object with master motors as parameters
    m_drivetrain = new DifferentialDrive(m_lFrontMotor, m_rFrontMotor);
  }

  /**
   * Initialize hardware devices for drive subsystem
   * 
   * @return hardware object containing all necessary devices for this subsystem
   */
  public static Hardware initializeHardware() {
    Hardware drivetrainHardware = new Hardware(
      new WPI_TalonSRX(Constants.Drive.LEFT_FRONT_MOTOR_ID),
      new WPI_TalonSRX(Constants.Drive.LEFT_MID_MOTOR_ID),
      new WPI_TalonSRX(Constants.Drive.LEFT_REAR_MOTOR_ID),
      new WPI_TalonSRX(Constants.Drive.RIGHT_FRONT_MOTOR_ID),
      new WPI_TalonSRX(Constants.Drive.RIGHT_MID_MOTOR_ID),
      new WPI_TalonSRX(Constants.Drive.RIGHT_REAR_MOTOR_ID)
    );

    return drivetrainHardware;
  }

  public void teleop(double speed, double turn) {
    m_drivetrain.arcadeDrive(speed, turn);
  }

  public void stop()
  {
    m_drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
