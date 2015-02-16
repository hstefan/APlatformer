package com.hstefan.aplatformer.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hstefan on 2/15/2015.
 */
public class MovementComponent extends Component{
    public Vector2 velocityNorm;
    public float speed;

    public MovementComponent(Vector2 velocityNorm, float speed) {
        this.velocityNorm = new Vector2(velocityNorm);
        this.speed = speed;
    }
}
