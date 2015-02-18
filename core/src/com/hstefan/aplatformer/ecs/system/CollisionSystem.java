package com.hstefan.aplatformer.ecs.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hstefan.aplatformer.ecs.component.CharacterComponent;
import com.hstefan.aplatformer.ecs.component.MovementComponent;
import com.hstefan.aplatformer.ecs.component.PositionComponent;
import com.hstefan.aplatformer.ecs.component.RectCollisionComponent;
import com.hstefan.aplatformer.ecs.misc.CollisionSide;

/**
 * Created by hstefan on 2/16/2015.
 */
public class CollisionSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private ImmutableArray<Entity> characters;

    private ComponentMapper<RectCollisionComponent> rm = ComponentMapper.getFor(RectCollisionComponent.class);
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);
    private Engine engine;

    public CollisionSystem() {
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
        entities = engine.getEntitiesFor(Family.getFor(RectCollisionComponent.class, PositionComponent.class));
        characters = engine.getEntitiesFor(Family.getFor(RectCollisionComponent.class, PositionComponent.class,
                CharacterComponent.class, MovementComponent.class));
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        CharacterSystem characterSystem = engine.getSystem(CharacterSystem.class);

        RectCollisionComponent col, otherCol;
        MovementComponent mov;
        PositionComponent posComp, otherPosComp;
        Rectangle rect, otherRect;
        Vector2 pos, otherPos;

        for (int i = 0; i < characters.size(); ++i) {
            Entity e = characters.get(i);
            col = e.getComponent(RectCollisionComponent.class);
            rect = new Rectangle(col.rect); //copy of collider rect
            posComp = e.getComponent(PositionComponent.class);
            pos = posComp.position.cpy();
            mov = e.getComponent(MovementComponent.class);
            rect.x += pos.x;
            rect.y += pos.y;

            for (int j = 0; j < entities.size(); ++j) {
                Entity colEnt = entities.get(j);
                if (colEnt.getId() == e.getId()) {
                    continue;
                }
                otherCol = colEnt.getComponent(RectCollisionComponent.class);
                otherRect = new Rectangle(otherCol.rect); //copy of collider rect
                otherPosComp = colEnt.getComponent(PositionComponent.class);
                otherPos = otherPosComp.position.cpy();
                otherRect.x += otherPos.x;
                otherRect.y += otherPos.y;

                if (otherRect.overlaps(rect)) {
                    CollisionSide side = handleCollision(e, colEnt, rect, otherRect);
                    characterSystem.collision(e, colEnt, side);
                }
            }
        }
    }

    private CollisionSide handleCollision(Entity character, Entity other, Rectangle charRect, Rectangle otherRect) {
        Rectangle intersect = new Rectangle();
        PositionComponent charPos = character.getComponent(PositionComponent.class);
        CollisionSide colSide = CollisionSide.None;
        if (Intersector.intersectRectangles(charRect, otherRect, intersect)) {
            if (intersect.x > charRect.x) {
                colSide = CollisionSide.Right;
                charPos.position = charPos.position.cpy().sub(intersect.width + 1, 0f);
            }
            else if (intersect.x + intersect.width < charRect.x + charRect.width) {
                colSide = CollisionSide.Left;
                charPos.position = charPos.position.cpy().add(intersect.width + 1, 0f);
            }
            else if (intersect.y > charRect.y) {
                colSide = CollisionSide.Up;
                charPos.position = charPos.position.cpy().sub(0f, intersect.height + 1);
            }
            else if (intersect.y + intersect.height < charRect.y + charRect.height) {
                colSide = CollisionSide.Down;
                charPos.position = charPos.position.cpy().add(0f, intersect.height + 1);
            }
        }
        return colSide;
    }
}
