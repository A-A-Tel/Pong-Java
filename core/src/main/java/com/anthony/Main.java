package com.anthony;

import com.anthony.game.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {

    // Game Variables
    public static float DELTA;
    public static Matrix4 CAM;
    public static final short[] SCORE = {0, 0};
    public static final float[][] PLAYERS = new float[2][];

    // Main config
    public static FitViewport VIEWPORT;
    private OrthographicCamera camera;

    private final List<IGameObject> gameObjects = List.of(new Backdrop(), new LeftPlayer(), new RightPlayer(), new Ball(), new Score());

    @Override
    public void create() {
        camera = new OrthographicCamera();
        VIEWPORT = new FitViewport(1280, 720, camera);
        VIEWPORT.apply();
        camera.position.set(1280f / 2, 720f / 2, 0);

        for (IGameObject object : gameObjects) {
            object.create();
        }
    }

    @Override
    public void render() {

        ScreenUtils.clear(0f, 0f, 0f, 0f);
        camera.update();
        CAM = camera.combined;
        DELTA = Gdx.graphics.getDeltaTime();

        for (IGameObject object : gameObjects) {
            object.render();
        }
    }

    @Override
    public void dispose() {
        for (IGameObject object : gameObjects) {
            object.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        VIEWPORT.update(width, height, true);
    }
}
