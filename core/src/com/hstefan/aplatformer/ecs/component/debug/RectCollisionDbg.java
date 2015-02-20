package com.hstefan.aplatformer.ecs.component.debug;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by hstefan on 2/20/2015.
 */
public class RectCollisionDbg extends Component {
    public Color color;

    public RectCollisionDbg(Color color) {
        this.color = color;
    }
}
