package com.anthony.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.anthony.Main.CAM;

public class Ball implements IGameObject {

    private SpriteBatch batch;
    private Texture texture;
    private final float[] properties = {-1f, -1f, 560f};

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("color.png");
    }

    @Override
    public void render() {

        if (properties[0] == -1f) {
            properties[0] = Gdx.graphics.getWidth() / 2f - 20f;
            properties[1] = Gdx.graphics.getHeight() / 2f - 20f;
        }

        moveBall();

        batch.setProjectionMatrix(CAM);
        batch.begin();

        batch.draw(texture,
            properties[0], properties[1],
            20f, 20f);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    private void moveBall() {

    }
}
