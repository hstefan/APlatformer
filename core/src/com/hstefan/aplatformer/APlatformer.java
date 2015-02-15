package com.hstefan.aplatformer;

import com.badlogic.gdx.Game;

public class APlatformer extends Game {

	@Override
	public void create () {
        setScreen(new GameScreen());
	}
}
