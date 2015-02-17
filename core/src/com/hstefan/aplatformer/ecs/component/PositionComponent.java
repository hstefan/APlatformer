package com.hstefan.aplatformer.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hstefan on 2/15/2015.
 */
public class PositionComponent extends Component {
    public Vector2 position;

    public PositionComponent(Vector2 pos) {
        position = pos.cpy();
    }
}
