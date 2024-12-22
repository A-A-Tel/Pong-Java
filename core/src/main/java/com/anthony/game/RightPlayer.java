package com.anthony.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.anthony.Main.CAM;
import static com.anthony.Main.DELTA;

public class RightPlayer implements IGameObject {

    private SpriteBatch batch;
    private Texture texture;
    private final float[] properties = {0, 100f, 280f};

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("color.png");
    }

    @Override
    public void render() {

        if (properties[0] == 0 && Gdx.graphics != null) {
            properties[0] = Gdx.graphics.getWidth() - 120f;
        }

        movePlayer();

        batch.setProjectionMatrix(CAM);
        batch.begin();

        batch.draw(texture,
            properties[0], properties[1],
            20f, 100f);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    private void movePlayer() {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            properties[1] += properties[2] * DELTA;

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            properties[1] -= properties[2] * DELTA;
        }

        if (properties[1] < 0) {
            properties[1] = 0;
        } else if (properties[1] > Gdx.graphics.getHeight() - 100f) {
            properties[1] = Gdx.graphics.getHeight() - 100f;
        }
    }
}
