package frc.robot.subsystems;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;

enum Direction {
    FRONT,
    REAR
}

public class CameraSubsystem {
    private static UsbCamera frontCamera;
    private static UsbCamera rearCamera;

    private static NetworkTableEntry cameraSelection;

    public static void init(UsbCamera fCamera, UsbCamera rCamera, NetworkTableEntry table){
        frontCamera = fCamera;
        rearCamera = rCamera;

        cameraSelection = table;
    }

    public static void select(Direction direction){
        if (direction == Direction.FRONT) {
            cameraSelection.setString(frontCamera.getName());
            return;
        }
        cameraSelection.setString(rearCamera.getName());
    }
}
