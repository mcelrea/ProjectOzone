package com.mcelrea;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Created by Tech on 3/6/2015.
 */
public class MyContactFilter implements ContactFilter {
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        /*
         * Player1 and Player 1 bullet collision
         */
        if(fixtureA.getUserData().equals("player1") &&
                fixtureB.getUserData().equals("player1 bullet")) {
            return false;
        }
        else if(fixtureB.getUserData().equals("player1") &&
                fixtureA.getUserData().equals("player1 bullet")) {
            return false;
        }

        /*
         * Player2 and Player 2 bullet collision
         */
        if(fixtureA.getUserData().equals("player2") &&
                fixtureB.getUserData().equals("player2 bullet")) {
            return false;
        }
        else if(fixtureB.getUserData().equals("player2") &&
                fixtureA.getUserData().equals("player2 bullet")) {
            return false;
        }

        /*
         * Bullets and Bullets collision
         */
        if(fixtureA.getUserData().equals("player1 bullet") &&
                fixtureB.getUserData().equals("player1 bullet")) {
            return false;
        }
        else if(fixtureA.getUserData().equals("player2 bullet") &&
                fixtureB.getUserData().equals("player2 bullet")) {
            return false;
        }
        else if(fixtureA.getUserData().equals("player1 bullet") &&
                fixtureB.getUserData().equals("player2 bullet")) {
            return false;
        }
        else if(fixtureA.getUserData().equals("player2 bullet") &&
                fixtureB.getUserData().equals("player1 bullet")) {
            return false;
        }

        /*
         * Bullets with Wall Collision
         */
        if(fixtureA.getUserData() instanceof Bullet &&
                fixtureB.getUserData().equals("wall")) {
            ((Bullet)fixtureA.getUserData()).alive = false;
        }
        else if(fixtureA.getUserData().equals("wall") &&
                fixtureB.getUserData() instanceof Bullet) {
            ((Bullet)fixtureB.getUserData()).alive = false;
        }


        //this is the last line of code!!!!
        return true;
    }
}
