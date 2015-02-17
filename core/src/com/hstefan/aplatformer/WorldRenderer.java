package com.hstefan.aplatformer;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hstefan.aplatformer.ecs.component.PositionComponent;
import com.hstefan.aplatformer.ecs.component.RectCollisionComponent;

import java.util.Iterator;

/**
 * Created by hstefan on 2/15/2015.
 */
public class WorldRenderer {
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Engine engine;

    public WorldRenderer(Engine engine) {
        this.engine = engine;
        tiledMap = new TmxMapLoader().load("test_nature.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        createEntities();
    }

    private void createEntities() {
        MapLayer collLayer = tiledMap.getLayers().get("colliders");
        if (collLayer != null) {
            MapObjects objects = collLayer.getObjects();
            Entity ent = null;
            MapObject mapObj = null;
            MapProperties props = null;
            PositionComponent posComp = null;
            RectCollisionComponent rectComp = null;
            for (int i = 0; i < objects.getCount(); ++i) {
                ent = new Entity();
                mapObj = objects.get(i);
                props = mapObj.getProperties();
                posComp = new PositionComponent(new Vector2(props.get("x", float.class), props.get("y", float.class)));
                rectComp = new RectCollisionComponent(new Rectangle(0f, 0f, props.get("width", float.class),
                        props.get("height", float.class)));
                ent.add(posComp);
                ent.add(rectComp);
                engine.addEntity(ent);
            }
        }
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }
}
