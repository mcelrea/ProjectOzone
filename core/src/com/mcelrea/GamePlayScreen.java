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
    Player player2;
    Map currentMap;

    @Override
    public void show() {
        //create world without gravity
        world = new World(new Vector2(0,0), true);
        world.setContactFilter(new MyContactFilter());

        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        player1 = new Player(world, 200, 4, -10, -8, "player1");
        player2 = new Player(world, 200, 4, 10, 8, "player2");

        currentMap = new DominationTemple(world);

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
        updatePlayer2(delta);
    }

    public void updatePlayer1(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player1.rotateClockwise(delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player1.rotateCounterClockwise(delta);
        }

        boolean moved = false;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1.moveForward(delta);
            moved = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player1.moveBackward(delta);
            moved = true;
        }
        if(!moved) {
            player1.stopMovement();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player1.shootBullet(world);
        }

    }

    public void updatePlayer2(float delta) {

            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player2.rotateClockwise(delta);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player2.rotateCounterClockwise(delta);
            }

            boolean moved = false;
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                player2.moveForward(delta);
                moved = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                player2.moveBackward(delta);
                moved = true;
            }
            if(!moved) {
                player2.stopMovement();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                player2.shootBullet(world);
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
