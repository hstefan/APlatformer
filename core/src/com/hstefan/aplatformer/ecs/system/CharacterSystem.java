package com.hstefan.aplatformer.ecs.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.hstefan.aplatformer.ecs.component.*;
import com.hstefan.aplatformer.ecs.misc.CollisionSide;
import org.w3c.dom.css.Rect;

import java.security.Key;
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
        MovementComponent m = null;
        Entity e = null;
        CharacterComponent c;
        for (int i =0; i < entities.size(); ++i) {
            e = entities.get(i);
            updateEntityController(e);
            m = e.getComponent(MovementComponent.class);
            c = e.getComponent(CharacterComponent.class);
            if (c.jumping) {
                if (TimeUtils.millis() - c.jumpTs > 300f) {
                    c.jumping = false;
                }
                if (c.jumping) {
                    m.velocity = m.velocity.cpy().add(new Vector2(0, 900f));
                }
            }
        }
    }

    private void updateEntityController(Entity entity) {
        MovementComponent m = entity.getComponent(MovementComponent.class);
        CharacterComponent c = entity.getComponent(CharacterComponent.class);
        Vector2 calcVelocity = new Vector2(0f, 0f);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && c.grounded) {
            c.jumping = true;
            c.jumpTs = TimeUtils.millis();
            c.grounded = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            calcVelocity.x -= 750f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            calcVelocity.x += 750f;
        }
        m.velocity = calcVelocity;
    }

    public void collision(Entity character, Entity other, CollisionSide side) {
        if (side == CollisionSide.Down) {
            character.getComponent(CharacterComponent.class).grounded = true;
        }
    }
}
