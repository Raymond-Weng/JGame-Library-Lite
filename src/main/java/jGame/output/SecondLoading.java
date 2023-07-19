package jGame.output;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;
import jGame.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SecondLoading extends GameObject {
    private final Game game;
    private final double createTime;

    public SecondLoading(Game game){
        this.game = game;
        createTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if(createTime + game.GAME_START_DELAY_SECOND * 1000 < System.currentTimeMillis()){
            game.removeObject(this, 0);
            game.second_loading = false;
        }
    }

    @Override
    public Image render() {
        return game.LOADING_IMAGE;
    }

    @Override
    public Position getPosition() {
        return new Position(0,0);
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public Size getSize() {
        return new Size(50, 50);
    }
}
