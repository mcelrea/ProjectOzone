package com.mcelrea;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tech on 3/2/2015.
 */
public class GamePlayScreen implements Screen {

    World world;                        //a large collection of Bodies and Fixtures
    OrthographicCamera camera;          //display a small portion of the world
    Box2DDebugRenderer debugRenderer;   //display the Bodies and Fixtures
    SpriteBatch batch;                  //used to draw Textures and Sprites
    Player player1;

    @Override
    public void show() {
        //create world without gravity
        world = new World(new Vector2(0,0), true);

        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        player1 = new Player(world, 4, 4, 0, 0);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        world.step(1/60f, 8, 3);
        camera.update();

        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width/25f;
        camera.viewportHeight = height/25f;
    }

    public void update(float delta) {
        updatePlayer1(delta);
    }

    public void updatePlayer1(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player1.rotateClockwise(delta);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
