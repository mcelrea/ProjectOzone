package com.mcelrea;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/5/2015.
 */
public class DominationTemple implements Map {

    DomPoint domPoint1;
    DomPoint domPoint2;
    DomPoint domPoint3;

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

        domPoint1 = new DomPoint(world, -10, 5);
        domPoint2 = new DomPoint(world, 10, 5);
        domPoint3 = new DomPoint(world, 10, -5);
    }

    @Override
    public void update(float delta) {

        if(domPoint1.owner.equals("player1")) {
            GamePlayScreen.player1.score++;
        }
        else if(domPoint1.owner.equals("player2")) {
            GamePlayScreen.player2.score++;
        }

        if(domPoint2.owner.equals("player1")) {
            GamePlayScreen.player1.score++;
        }
        else if(domPoint2.owner.equals("player2")) {
            GamePlayScreen.player2.score++;
        }

        if(domPoint3.owner.equals("player1")) {
            GamePlayScreen.player1.score++;
        }
        else if(domPoint3.owner.equals("player2")) {
            GamePlayScreen.player2.score++;
        }
    }

    public void paint(SpriteBatch batch, OrthographicCamera camera) {

        domPoint1.paint(batch, camera);
        domPoint2.paint(batch, camera);
        domPoint3.paint(batch, camera);
    }
}
