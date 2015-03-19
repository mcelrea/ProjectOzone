package com.mcelrea;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Tech on 3/2/2015.
 */
public class GamePlayScreen implements Screen {

    World world;                        //a large collection of Bodies and Fixtures
    OrthographicCamera camera;          //display a small portion of the world
    Box2DDebugRenderer debugRenderer;   //display the Bodies and Fixtures
    SpriteBatch batch;                  //used to draw Textures and Sprites
    public static Player player1;
    public static Player player2;
    Map currentMap;
    BitmapFont font;
    boolean debug = true;

    public static ArrayList<Bullet> bullets;

    @Override
    public void show() {
        bullets = new ArrayList<Bullet>();
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        //create world without gravity
        world = new World(new Vector2(0,0), true);
        world.setContactFilter(new MyContactFilter());

        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        player1 = new Player(world, 200, 4, -10, -8, "player1", "blueTank.png");
        player2 = new Player(world, 200, 4, 10, 8, "player2", "redTank.png");

        currentMap = new DominationTemple(world);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        player1.update();
        player2.update();

        world.step(1/60f, 8, 3);
        camera.update();

        batch.begin();
        currentMap.paint(batch, camera);
        debugOutput();
        player1.paint(batch, camera);
        player2.paint(batch, camera);
        batch.end();

        debugRenderer.render(world, camera.combined);

        removeDeadBullets();
    }

    public void debugOutput() {
        if(debug == true) {
            font.draw(batch, "player 1 x: " + player1.body.getPosition().x, 5, 560);
            font.draw(batch, "player 1 y: " + player1.body.getPosition().y, 5, 540);
            font.draw(batch, "player 2 x: " + player2.body.getPosition().x, 5, 520);
            font.draw(batch, "player 2 y: " + player2.body.getPosition().y, 5, 500);
            font.draw(batch, "# of bullets: " + bullets.size(), 5, 480);
            font.draw(batch, "player 1 score: " + player1.score, 5, 460);
            font.draw(batch, "player 2 score: " + player2.score, 5, 440);
        }
    }

    public void removeDeadBullets() {
        //go through the array of bullets
        for(int i=0; i < bullets.size(); i++) {

            //if the current bullet is tagged for removal
            if(bullets.get(i).alive == false) {
                world.destroyBody(bullets.get(i).body);
                bullets.remove(i);
                i--;
                System.out.println("Tyring to destroy a bullet");
            }
            else {//check if bullet is off screen

                //ask the world for world coords of bullet (meters)
                float x = bullets.get(i).body.getPosition().x;
                float y = bullets.get(i).body.getPosition().y;
                Vector3 worldCoords = new Vector3(x,y,0);

                //ask the camera to convert from meters to pixels
                Vector3 pixelCoords = camera.project(worldCoords);

                //if the bullet is off screen
                if(pixelCoords.x < 0 || pixelCoords.x > 800 ||
                        pixelCoords.y < 0 || pixelCoords.y > 600) {
                    world.destroyBody(bullets.get(i).body);
                    bullets.remove(i);
                    i--;
                    System.out.println("Tyring to destroy a bullet");
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width/25f;
        camera.viewportHeight = height/25f;
    }

    public void update(float delta) {

        updatePlayer1(delta);
        updatePlayer2(delta);
        currentMap.update(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.F10)) {
            debug = !debug;
        }
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
