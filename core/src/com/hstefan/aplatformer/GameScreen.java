package com.hstefan.aplatformer;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hstefan.aplatformer.ecs.component.*;
import com.hstefan.aplatformer.ecs.component.debug.RectCollisionDbg;
import com.hstefan.aplatformer.ecs.system.*;

/**
 * Created by hstefan on 2/15/2015.
 */
public class GameScreen extends ScreenAdapter {
    public static final int GAME_RUNNING = 0;

    private WorldRenderer worldRenderer;
    private GameCamera gameCamera;
    private Engine engine;

    public GameScreen() {
        engine = new Engine();
        gameCamera = new GameCamera();
        worldRenderer = new WorldRenderer(engine);

        createTestCharacter();

        engine.addSystem(new MovementSystem());
        engine.addSystem(new CharacterSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new RenderSystem(gameCamera.getCamera()));
        engine.addSystem(new DebugSystem(gameCamera.getCamera()));
    }


    private void createTestCharacter() {
        Entity charac = new Entity();

        charac.add(new PositionComponent(new Vector2(120, 400)));

        TextureRegion region = new TextureRegion();
        Texture tex = new Texture("character.png");
        region.setTexture(tex);
        region.setRegion(tex);
        charac.add(new VisualComponent(region));
        charac.add(new MovementComponent(new Vector2(0f, 0f), new Vector2(0, -400f)));
        charac.add(new ControllerComponent());
        charac.add(new RectCollisionComponent(new Rectangle(0, 0, tex.getWidth(), tex.getHeight())));
        charac.add(new CharacterComponent());
        charac.add(new RectCollisionDbg(Color.GREEN));
        engine.addEntity(charac);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCamera.render(delta);
        worldRenderer.render(gameCamera.getCamera());
        engine.update(delta);
    }
}
