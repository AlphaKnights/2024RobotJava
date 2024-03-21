package frc.robot.subsystems;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;

enum Direction {
    FRONT,
    REAR
}

public class CameraSubsystem {
    private static UsbCamera frontCamera;
    private static UsbCamera rearCamera;

    private static VideoSink server;

    public static void init(UsbCamera fCamera, UsbCamera rCamera, VideoSink table){
        frontCamera = fCamera;
        rearCamera = rCamera;

        server = table;
    }

    public static void select(Direction direction){
        if (direction == Direction.FRONT) {
            server.setSource(frontCamera);
            return;
        }
        server.setSource(rearCamera);
    }
}
