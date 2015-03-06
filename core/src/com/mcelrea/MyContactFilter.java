package com.mcelrea;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Created by Tech on 3/6/2015.
 */
public class MyContactFilter implements ContactFilter {
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        if(fixtureA.getUserData().equals("player1") &&
                fixtureB.getUserData().equals("player1 bullet")) {
            return false;
        }
        else if(fixtureB.getUserData().equals("player1") &&
                fixtureA.getUserData().equals("player1 bullet")) {
            return false;
        }

        return true;
    }
}
