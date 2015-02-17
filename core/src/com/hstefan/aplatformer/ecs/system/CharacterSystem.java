package com.hstefan.aplatformer.ecs.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hstefan.aplatformer.ecs.component.*;
import org.w3c.dom.css.Rect;

import java.util.Vector;

/**
 * Created by hstefan on 2/16/2015.
 */
public class CharacterSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<RectCollisionComponent> rm = ComponentMapper.getFor(RectCollisionComponent.class);
    private ComponentMapper<CharacterComponent> cm = ComponentMapper.getFor(CharacterComponent.class);

    public CharacterSystem() {

    }

    @Override
    public void addedToEngine(Engine engine) {
        System.out.println("CharacterSystemAdded");
        entities = engine.getEntitiesFor(Family.getFor(CharacterComponent.class, RectCollisionComponent.class,
                MovementComponent.class));
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        for (int i =0; i < entities.size(); ++i) {
            Entity e = entities.get(i);
            updateEntityController(e);
        }
    }

    private void updateEntityController(Entity entity) {
        MovementComponent m = entity.getComponent(MovementComponent.class);
        Vector2 calcVelocity = new Vector2(0f, 0f);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            calcVelocity.y += 1000f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            calcVelocity.y -= 1000f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            calcVelocity.x -= 1000f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            calcVelocity.x += 1000f;
        }
        m.velocity = calcVelocity;
    }

    public void collision(Entity character, Entity other, Rectangle charRect, Rectangle otherRect) {
    }
}
