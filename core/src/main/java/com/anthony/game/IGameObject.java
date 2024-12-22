package com.anthony.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IGameObject {
    void create();

    void render();

    void dispose();
}
