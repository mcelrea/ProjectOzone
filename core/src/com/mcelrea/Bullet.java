package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/6/2015.
 */
public class Bullet {

    Body body;
    boolean alive;

    public Bullet(World world, float size,
                  float x, float y,
                  float xvel, float yvel,
                  String name) {

        alive = true;
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        CircleShape c = new CircleShape();
        c.setRadius(size);
        fixtureDef.shape = c;
        fixtureDef.restitution = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.density = 500f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.getFixtureList().first().setUserData(name);
        body.setLinearVelocity(xvel, yvel);
        c.dispose();
    }
}
