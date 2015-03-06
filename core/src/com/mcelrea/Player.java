package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/3/2015.
 */
public class Player {

    private Body body;
    private float moveSpeed;
    private float turnSpeed;
    String name;

    //constructor
    public Player(World world, float moveSpeed, float turnSpeed,
                  float x, float y, String name) {
        this.moveSpeed = moveSpeed;
        this.turnSpeed = turnSpeed;
        this.name = name;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody; //make the player dynamic, affected by forces and gravity
        bodyDef.position.set(x, y);
        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f,0.5f);
        fixtureDef.shape = box;
        fixtureDef.restitution = 0.2f; //how much bounce it has
        fixtureDef.friction = 0.4f;
        fixtureDef.density = 700; //kg/m^2
        body = world.createBody(bodyDef);  //create the body in the world
        body.createFixture(fixtureDef); //attach the square fixture to the body
        body.getFixtureList().first().setUserData(name); //name the body this (Player)
        box.dispose(); //dispose of the shape, freeing up memory and reducing memory leaks
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
        float xvel = moveXByAngle(body.getAngle());
        float yvel = moveYByAngle(body.getAngle());
        new Bullet(world, 0.3f,
                   body.getPosition().x,
                   body.getPosition().y,
                   xvel*4,
                   yvel*4,
                   name + " bullet");
    }
}
