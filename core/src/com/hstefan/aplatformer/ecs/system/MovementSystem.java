package com.hstefan.aplatformer.ecs.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.hstefan.aplatformer.ecs.component.MovementComponent;
import com.hstefan.aplatformer.ecs.component.PositionComponent;

/**
 * Created by hstefan on 2/15/2015.
 */
public class MovementSystem extends IteratingSystem {
    public ImmutableArray<Entity> entities;
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);

    public MovementSystem() {
        super(Family.getFor(PositionComponent.class, MovementComponent.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        MovementComponent movement = mm.get(entity);
        movement.velocity = movement.velocity.cpy().add(movement.gravity);
        position.position.add(movement.velocity.cpy().scl(deltaTime));
    }
}
