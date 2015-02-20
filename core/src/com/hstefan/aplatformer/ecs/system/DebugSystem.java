package com.hstefan.aplatformer.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.hstefan.aplatformer.ecs.component.PositionComponent;
import com.hstefan.aplatformer.ecs.component.RectCollisionComponent;
import com.hstefan.aplatformer.ecs.component.debug.RectCollisionDbg;

/**
 * Created by hstefan on 2/20/2015.
 */
public class DebugSystem extends EntitySystem {
    private OrthographicCamera camera;
    private ImmutableArray<Entity> entities;
    private ShapeRenderer shapeRenderer;

    public DebugSystem(OrthographicCamera camera) {
        this.camera = camera;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.getFor(PositionComponent.class, RectCollisionComponent.class,
                RectCollisionDbg.class));
    }

    @Override
    public void removedFromEngine(Engine engine) {
    }

    @Override
    public void update(float deltaTime) {
        Entity e = null;
        RectCollisionComponent rect = null;
        PositionComponent pos = null;
        RectCollisionDbg dbg = null;
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < entities.size(); ++i) {
            e = entities.get(i);
            rect = e.getComponent(RectCollisionComponent.class);
            pos = e.getComponent(PositionComponent.class);
            dbg = e.getComponent(RectCollisionDbg.class);
            shapeRenderer.setColor(dbg.color);
            shapeRenderer.rect(rect.rect.x + pos.position.x, rect.rect.y + pos.position.y,
                    rect.rect.width, rect.rect.height);
        }
        shapeRenderer.end();
    }
}

