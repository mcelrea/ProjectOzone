package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/3/2015.
 */
public class Player {

    Body body;
    float moveSpeed;
    float turnSpeed;
    float bulletSpeed = 15;
    float bulletSize = 0.3f;
    float playerSize = 0.5f;
    String name;
    long lastShot;
    long shootDelay = 1000; //1000 = 1 second
    boolean canShoot = true;
    int score;
    float startx;
    float starty;
    Sprite sprite;
    boolean reset = false;
    Sound shootSound;
    Sound hitSound;

    //constructor
    public Player(World world, float moveSpeed, float turnSpeed,
                  float x, float y, String name, String path) {
        this.moveSpeed = moveSpeed;
        this.turnSpeed = turnSpeed;
        this.name = name;
        startx = x;
        starty = y;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody; //make the player dynamic, affected by forces and gravity
        bodyDef.position.set(x, y);
        PolygonShape box = new PolygonShape();
        box.setAsBox(playerSize,playerSize);
        fixtureDef.shape = box;
        fixtureDef.restitution = 0.2f; //how much bounce it has
        fixtureDef.friction = 0.4f;
        fixtureDef.density = 700; //kg/m^2
        body = world.createBody(bodyDef);  //create the body in the world
        body.createFixture(fixtureDef); //attach the square fixture to the body
        body.getFixtureList().first().setUserData(new PlayerUserData(this,name)); //name the body this (Player)
        box.dispose(); //dispose of the shape, freeing up memory and reducing memory leaks

        Texture t = new Texture(Gdx.files.internal(path));
        sprite = new Sprite(t);

        shootSound = Gdx.audio.newSound(Gdx.files.internal("Laser_Shoot31.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("tankHitSound.wav"));
    }

    public void rotateClockwise(float delta) {
        body.setTransform(body.getPosition().x,
                          body.getPosition().y,
                          body.getAngle() - turnSpeed * delta);
        body.setAngularVelocity(0);
        body.setAwake(true);
    }

    public void rotateCounterClockwise(float delta) {
        body.setTransform(body.getPosition().x,
                          body.getPosition().y,
                          body.getAngle() + turnSpeed * delta);
        body.setAngularVelocity(0);
        body.setAwake(true);
    }

    public float moveXByAngle(float angle)
    {
        return (float)Math.cos(angle);
    }

    public float moveYByAngle(float angle)
    {
        return (float)Math.sin(angle);
    }

    public void moveForward(float delta) {
        body.setLinearVelocity(moveXByAngle(body.getAngle())*delta*moveSpeed,moveYByAngle(body.getAngle()) * delta * moveSpeed);
        body.setAwake(true);
    }

    public void moveBackward(float delta) {
        body.setLinearVelocity(moveXByAngle(body.getAngle())*delta*-1*moveSpeed/2,moveYByAngle(body.getAngle()) * delta * -1 * moveSpeed/2);
        body.setAwake(true);
    }

    public void stopMovement() {
        body.setLinearVelocity(0,0);
        body.setAwake(true);
    }

    public void shootBullet(World world) {

        if(lastShot + shootDelay < System.currentTimeMillis()) {
            canShoot = true;
        }

        if(canShoot == true) {
            float xvel = moveXByAngle(body.getAngle());
            float yvel = moveYByAngle(body.getAngle());
            Bullet b = new Bullet(world, bulletSize,
                    body.getPosition().x,
                    body.getPosition().y,
                    xvel * bulletSpeed,
                    yvel * bulletSpeed,
                    name + " bullet");
            GamePlayScreen.bullets.add(b);
            shootSound.play();
            canShoot = false;
            lastShot = System.currentTimeMillis();
        }
    }

    public void update() {
        if(reset) {
            hitSound.play();
            if(name.equals("player1")) {
                body.setTransform(startx, starty, 0);
            }
            else {
                body.setTransform(startx, starty, (float)(Math.toRadians(180)));
            }
            body.setAwake(true);
            reset = false;
        }
    }

    public void paint(SpriteBatch batch, OrthographicCamera camera) {
        //grab the world coordinates of the player (meters)
        Vector3 worldCoords = new Vector3(body.getPosition().x-playerSize,
                                          body.getPosition().y-playerSize,
                                          0);
        //convert the players position from world coordinates(meters)
        //to screen coordinates (pixels)
        Vector3 screenCoords = camera.project(worldCoords);

        float def = (float)(Math.toDegrees(body.getAngle()));
        sprite.setRotation(def);
        //draw the player image onto the screen
        batch.draw(sprite, screenCoords.x, screenCoords.y,
                   sprite.getWidth()/2, sprite.getHeight()/2,
                   sprite.getWidth(), sprite.getHeight(),
                   1, 1,
                   sprite.getRotation()-90);
    }
}
