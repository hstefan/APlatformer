package com.hstefan.aplatformer.ecs.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hstefan.aplatformer.ecs.component.PositionComponent;
import com.hstefan.aplatformer.ecs.component.VisualComponent;

/**
 * Created by hstefan on 2/15/2015.
 */
public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VisualComponent> vm = ComponentMapper.getFor(VisualComponent.class);

    public RenderSystem(OrthographicCamera cam) {
        camera = cam;
        batch = new SpriteBatch();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.getFor(PositionComponent.class, VisualComponent.class));
    }

    @Override
    public void removedFromEngine(Engine engine) {
    }

    @Override
    public void update(float deltaTime) {
        PositionComponent pos = null;
        VisualComponent vis = null;

        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        for (int i = 0; i < entities.size(); ++i) {
            Entity e = entities.get(i);

            pos = e.getComponent(PositionComponent.class);
            vis = e.getComponent(VisualComponent.class);

            batch.draw(vis.texRegion, pos.position.x, pos.position.y);
        }

        batch.end();
    }
}
