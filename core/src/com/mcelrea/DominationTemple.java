package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/5/2015.
 */
public class DominationTemple implements Map {

    public DominationTemple(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);
        PolygonShape box = new PolygonShape();
        box.setAsBox(3,5);
        fixtureDef.shape = box;
        fixtureDef.restitution = 0f;
        fixtureDef.friction = 1f;
        fixtureDef.density = 1000;
        Body temp = world.createBody(bodyDef);
        temp.createFixture(fixtureDef);
        temp.getFixtureList().first().setUserData("wall");
        box.dispose();
    }

    @Override
    public void update(float delta) {

    }
}
