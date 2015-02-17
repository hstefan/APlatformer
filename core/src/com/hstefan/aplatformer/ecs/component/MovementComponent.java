package com.hstefan.aplatformer.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hstefan on 2/15/2015.
 */
public class MovementComponent extends Component{
    public Vector2 velocity;

    public MovementComponent(Vector2 velocity) {
        this.velocity = velocity.cpy();
    }
}
