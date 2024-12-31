package com.anthony.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.anthony.Main.SCORE;
import static com.anthony.Main.PLAYERS;
import static com.anthony.Main.VIEWPORT;

public class Score implements IGameObject {

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render() {
        batch.begin();

        for (int i = 0; i < PLAYERS.length; i++) {
            font.draw(batch, SCORE[i] + "", PLAYERS[i][0], VIEWPORT.getWorldHeight());
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
