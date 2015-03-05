package com.mcelrea;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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
        world.createBody(bodyDef).createFixture(fixtureDef);
        box.dispose();
    }

    @Override
    public void update(float delta) {

    }
}
