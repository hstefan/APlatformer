package com.hstefan.aplatformer.ecs.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by hstefan on 2/16/2015.
 */
public class CharacterComponent extends Component {
    public boolean grounded;
    public boolean jumping;
    public long jumpTs;

    public CharacterComponent() {
        grounded = false;
        jumping = false;
        jumpTs = 0L;
    }
}
