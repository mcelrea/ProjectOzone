package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/17/2015.
 */
public class DomPoint {

    Body body;
    String owner;
    Sprite white;
    Sprite red;
    Sprite blue;

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

        Texture t = new Texture(Gdx.files.internal("white dom capture.png"));
        white = new Sprite(t);
        t = new Texture(Gdx.files.internal("red dom capture.png"));
        red = new Sprite(t);
        t = new Texture(Gdx.files.internal("blue dom capture.png"));
        blue = new Sprite(t);

    }

    public void paint(SpriteBatch batch, OrthographicCamera camera) {
        //grab the world coordinates of the player (meters)
        Vector3 worldCoords = new Vector3(body.getPosition().x,
                body.getPosition().y,
                0);
        //convert the players position from world coordinates(meters)
        //to screen coordinates (pixels)
        Vector3 screenCoords = camera.project(worldCoords);

        if(owner.equals("player1")) {
            batch.draw(blue, screenCoords.x-white.getWidth()/2, screenCoords.y-white.getHeight()/2);
        }
        else if (owner.equals("player2")) {
            batch.draw(red, screenCoords.x-white.getWidth()/2, screenCoords.y-white.getHeight()/2);
        }
        else {
            batch.draw(white, screenCoords.x-white.getWidth()/2, screenCoords.y-white.getHeight()/2);
        }
    }
}
