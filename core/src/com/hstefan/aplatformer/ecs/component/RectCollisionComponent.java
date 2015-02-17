package com.hstefan.aplatformer.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hstefan on 2/16/2015.
 */
public class RectCollisionComponent extends Component {
    public Rectangle rect;

    public RectCollisionComponent(Rectangle rect) {
        this.rect = rect;
    }
}
