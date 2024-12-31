package com.anthony.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

import static com.anthony.Main.CAM;
import static com.anthony.Main.SCORE;
import static com.anthony.Main.DELTA;
import static com.anthony.Main.PLAYERS;
import static com.anthony.Main.VIEWPORT;

public class Ball implements IGameObject {

    private SpriteBatch batch;
    private Texture texture;
    private Sound sound;
    private boolean restart = true;
    private boolean activeDelay = false;
    private final float[] properties = {-1f, -1f, -1f, 0, 20f, 20f};

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("color.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
    }

    @Override
    public void render() {

        if (activeDelay) {

            moveBall();
            batch.setProjectionMatrix(CAM);
            batch.begin();

            batch.draw(texture, properties[0], properties[1], properties[4], properties[5]);

            batch.end();
        } else {

            if (restart) restart();

            moveBall();
            checkCollisionBorder();
            checkCollisionPaddles();

            batch.setProjectionMatrix(CAM);
            batch.begin();

            batch.draw(texture, properties[0], properties[1], properties[4], properties[5]);

            batch.end();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    private void moveBall() {

        properties[0] += properties[2] * DELTA;
        properties[1] += properties[3] * DELTA;
    }

    private void checkCollisionBorder() {

        if (properties[0] < 0 || properties[0] > VIEWPORT.getWorldWidth() - 20f) {

            if (properties[0] < 0) {
                SCORE[1]++;
            } else {
                SCORE[0]++;
            }
            restart = true;
            delay(1000L);

        } else if (properties[1] < 0 || properties[1] > VIEWPORT.getWorldHeight() - properties[5]) {

            properties[3] *= -1;

            if (properties[1] < 0) {
                properties[1] = 0;
            } else {
                properties[1] = VIEWPORT.getWorldHeight() - properties[5];
            }
        }
    }

    private void checkCollisionPaddles() {
        for (float[] paddle : PLAYERS) {
            if (properties[0] < paddle[0] + paddle[3] &&
                properties[0] + properties[4] > paddle[0] &&
                properties[1] < paddle[1] + paddle[4] &&
                properties[1] + properties[5] > paddle[1]) {

                // Play audio
                sound.play();

                // Make ball bounce with a factor of Xf
                float relativePoint = properties[1] - paddle[1] + properties[5] / 2;
                float judgePoint;

                if (relativePoint >= paddle[4] / 2f) {
                    judgePoint = relativePoint - paddle[4] / 2;
                } else {
                    judgePoint = (paddle[4] / 2 - relativePoint) * -1f;
                }

                properties[3] = judgePoint * 7f;


                // Check which side the ball was bouncing from
                float overlapLeft = (properties[0] + properties[4]) - paddle[0];
                float overlapRight = (paddle[0] + paddle[3]) - properties[0];
                float overlapTop = (properties[1] + properties[5]) - paddle[1];
                float overlapBottom = (paddle[1] + paddle[4]) - properties[1];

                float minOverlap = Math.min(Math.min(overlapLeft, overlapRight), Math.min(overlapTop, overlapBottom));

                if (minOverlap == overlapLeft || minOverlap == overlapRight) {
                    properties[0] += properties[2] < 0 ? 1f : -1f;
                    properties[2] *= -1f;
                } else {
                    delay(500L);
                }
                break;
            }
        }
    }

    private void delay(long millis) {

        activeDelay = true;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                activeDelay = false;
            }
        }, millis / 1000f);
    }

    private void restart() {
        Random rnd = new Random();

        properties[2] = rnd.nextBoolean() ? 560f : -560f;
        properties[3] = rnd.nextFloat(-45f, 45f) * 7f;

        properties[0] = VIEWPORT.getWorldWidth() / 2f - 20f;
        properties[1] = VIEWPORT.getWorldHeight() / 2f - 20f;

        restart = false;
    }
}
