package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/3/2015.
 */
public class Player {

    private Body body;
    private float moveSpeed;
    private float turnSpeed;

    //constructor
    public Player(World world, float moveSpeed, float turnSpeed,
                  float x, float y) {
        this.moveSpeed = moveSpeed;
        this.turnSpeed = turnSpeed;

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
        body.getFixtureList().first().setUserData(this); //name the body this (Player)
        box.dispose(); //dispose of the shape, freeing up memory and reducing memory leaks
    }

    public void rotateClockwise(float delta) {
        body.setTransform(0,0,body.getAngle() - turnSpeed * delta);
        body.setAwake(true);
    }

    public void rotateCounterClockwise(float delta) {
        body.setTransform(0,0,body.getAngle() + turnSpeed * delta);
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
}
