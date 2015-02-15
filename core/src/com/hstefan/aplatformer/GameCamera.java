package com.hstefan.aplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by hstefan on 2/15/2015.
 */
public class GameCamera {
    private OrthographicCamera camera;

    public GameCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
    }

    public void render(float dt) {
        float camSpeed = 1000 * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            camera.translate(-camSpeed, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            camera.translate(camSpeed, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            camera.translate(0, camSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            camera.translate(0, -camSpeed);

        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}

