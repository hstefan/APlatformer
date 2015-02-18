package com.hstefan.aplatformer.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hstefan on 2/15/2015.
 */
public class MovementComponent extends Component{
    public Vector2 velocity;
    public Vector2 gravity;

    public MovementComponent(Vector2 velocity, Vector2 gravity) {
        this.velocity = velocity.cpy();
        this.gravity = gravity.cpy();
    }
}
