package com.mcelrea;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
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
        if(fixtureA.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureA.getUserData()).name.equals("player1") &&
                fixtureB.getUserData() instanceof BulletName &&
                ((BulletName)fixtureB.getUserData()).name.equals("player1 bullet")) {
            return false;
        }
        else if(fixtureB.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureB.getUserData()).name.equals("player1") &&
                fixtureA.getUserData() instanceof BulletName &&
                ((BulletName)fixtureA.getUserData()).name.equals("player1 bullet")) {
            return false;
        }

        /*
         * Player2 and Player 2 bullet collision
         */
        if(fixtureA.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureA.getUserData()).name.equals("player2") &&
                fixtureB.getUserData() instanceof BulletName &&
                ((BulletName)fixtureB.getUserData()).name.equals("player2 bullet")) {
            return false;
        }
        else if(fixtureB.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureB.getUserData()).name.equals("player2") &&
                fixtureA.getUserData() instanceof BulletName &&
                ((BulletName)fixtureA.getUserData()).name.equals("player2 bullet")) {
            return false;
        }

        /*
         * Bullets and Bullets collision
         */
        if(fixtureA.getUserData() instanceof BulletName &&
                fixtureB.getUserData() instanceof  BulletName) {
            return false;
        }


        /*
         * Bullets with Wall Collision
         */
        if(fixtureA.getUserData() instanceof BulletName &&
                fixtureB.getUserData().equals("wall")) {
            ((BulletName)fixtureA.getUserData()).bullet.alive = false;
        }
        else if(fixtureA.getUserData().equals("wall") &&
                fixtureB.getUserData() instanceof BulletName) {
            ((BulletName)fixtureB.getUserData()).bullet.alive = false;
        }

        /*
         * Bullets with DomPoints
         */
        if(fixtureA.getUserData() instanceof DomPoint &&
                fixtureB.getUserData() instanceof BulletName) {
            return false;
        }
        else if(fixtureB.getUserData() instanceof DomPoint &&
                fixtureA.getUserData() instanceof BulletName) {
            return false;
        }

        /*
         * Players with DomPoints
         */
        if(fixtureA.getUserData() instanceof DomPoint &&
                fixtureB.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureB.getUserData()).name.equals("player1")) {
            String temp = ((DomPoint)fixtureA.getUserData()).owner;
            if(!temp.equals("player1")) {
                ((DomPoint) fixtureA.getUserData()).owner = "player1";
                GamePlayScreen.domPointCaptured.play();
            }
            return false;
        }
        else if(fixtureA.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureA.getUserData()).name.equals("player1") &&
                fixtureB.getUserData() instanceof DomPoint) {
            String temp = ((DomPoint)fixtureB.getUserData()).owner;
            if(!temp.equals("player1")) {
                ((DomPoint) fixtureB.getUserData()).owner = "player1";
                GamePlayScreen.domPointCaptured.play();
            }
            return false;
        }
        if(fixtureA.getUserData() instanceof DomPoint &&
                fixtureB.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureB.getUserData()).name.equals("player2")) {
            String temp = ((DomPoint)fixtureA.getUserData()).owner;
            if(!temp.equals("player2")) {
                ((DomPoint) fixtureA.getUserData()).owner = "player2";
                GamePlayScreen.domPointCaptured.play();
            }
            return false;
        }
        else if(fixtureA.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureA.getUserData()).name.equals("player2") &&
                fixtureB.getUserData() instanceof DomPoint) {
            String temp = ((DomPoint)fixtureB.getUserData()).owner;
            if(!temp.equals("player2")) {
                ((DomPoint) fixtureB.getUserData()).owner = "player2";
                GamePlayScreen.domPointCaptured.play();
            }
            return false;
        }

        /*
         * Bullet with Player Collision
         */
        if(fixtureA.getUserData() instanceof BulletName &&
                ((BulletName)fixtureA.getUserData()).name.equals("player1 bullet")&&
                fixtureB.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureB.getUserData()).name.equals("player2")) {
            Player p = ((PlayerUserData)fixtureB.getUserData()).player;
            p.reset = true;
            ((BulletName)fixtureA.getUserData()).bullet.alive = false;
        }
        else if(fixtureB.getUserData() instanceof BulletName &&
                ((BulletName)fixtureB.getUserData()).name.equals("player1 bullet")&&
                fixtureA.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureA.getUserData()).name.equals("player2")) {
            Player p = ((PlayerUserData)fixtureA.getUserData()).player;
            p.reset = true;
            ((BulletName)fixtureB.getUserData()).bullet.alive = false;
        }
        if(fixtureA.getUserData() instanceof BulletName &&
                ((BulletName)fixtureA.getUserData()).name.equals("player2 bullet")&&
                fixtureB.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureB.getUserData()).name.equals("player1")) {
            Player p = ((PlayerUserData)fixtureB.getUserData()).player;
            p.reset = true;
            ((BulletName)fixtureA.getUserData()).bullet.alive = false;
        }
        else if(fixtureB.getUserData() instanceof BulletName &&
                ((BulletName)fixtureB.getUserData()).name.equals("player2 bullet")&&
                fixtureA.getUserData() instanceof PlayerUserData &&
                ((PlayerUserData)fixtureA.getUserData()).name.equals("player1")) {
            Player p = ((PlayerUserData)fixtureA.getUserData()).player;
            p.reset = true;
            ((BulletName)fixtureB.getUserData()).bullet.alive = false;
        }


        //this is the last line of code!!!!
        return true;
    }
}
