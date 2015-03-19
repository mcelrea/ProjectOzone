package com.mcelrea;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Tech on 3/19/2015.
 */
public class PlayerUserData {

    Player player;
    String name;

    public PlayerUserData(Player p, String n) {
        player = p;
        name = n;
    }
}
