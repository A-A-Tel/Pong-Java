package com.anthony.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.anthony.Main.VIEWPORT;

public class Backdrop implements IGameObject {

    private SpriteBatch batch;
    private Texture texture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("backdrop.png");
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT.getWorldWidth(), VIEWPORT.getWorldHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
