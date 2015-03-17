package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/17/2015.
 */
public class DomPoint {

    Body body;
    String owner;

    public DomPoint(World world, float x, float y) {

        owner = "";

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        CircleShape cir = new CircleShape();
        cir.setRadius(1);
        fixtureDef.shape = cir;
        fixtureDef.restitution = 0f;
        fixtureDef.friction = 0f;
        fixtureDef.density = 1f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.getFixtureList().first().setUserData(this);
        cir.dispose();

    }
}
