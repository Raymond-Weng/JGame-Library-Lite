package exampleCode.math;

import jGame.core.Position;
import jGame.output.Frame;

public class PositionConverter {

    private final Frame frame;

    public PositionConverter(Frame frame) {
        this.frame = frame;
    }

    public Position jGameToJFrame(Position position) {
        return position.add(frame.getSize().divide(2));
    }

    public Position jFrameTojGame(Position position) {
        return position.subtract(frame.getSize().divide(2));
    }
}
