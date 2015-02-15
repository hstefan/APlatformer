package com.hstefan.aplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by hstefan on 2/15/2015.
 */
public class GameScreen extends ScreenAdapter {
    public static final int GAME_RUNNING = 0;

    private WorldRenderer worldRenderer;
    private GameCamera gameCamera;

    public GameScreen() {
        gameCamera = new GameCamera();
        worldRenderer = new WorldRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCamera.render(delta);
        worldRenderer.render(gameCamera.getCamera());
    }
}
