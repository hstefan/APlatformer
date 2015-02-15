package com.hstefan.aplatformer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by hstefan on 2/15/2015.
 */
public class WorldRenderer {
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    public WorldRenderer() {
        tiledMap = new TmxMapLoader().load("test_nature.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }
}
