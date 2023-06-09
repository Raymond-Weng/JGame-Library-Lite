package jGame.loop.render;

public class RenderImpl extends Render {
    /**
     * create the object
     *
     * @param maxFps the maximum fps, the maximum frame per second, is different from update rate
     */
    public RenderImpl(double maxFps) {
        super(1d / maxFps);
    }

    @Override
    public void renderGame() {
    }

    //TODO
}
