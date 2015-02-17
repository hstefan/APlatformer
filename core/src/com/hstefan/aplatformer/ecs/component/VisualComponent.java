package com.hstefan.aplatformer.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hstefan on 2/15/2015.
 */
public class VisualComponent extends Component {
    public TextureRegion texRegion;

    public VisualComponent(TextureRegion region) {
        this.texRegion = region;
    }
}
