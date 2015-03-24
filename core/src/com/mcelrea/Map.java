package com.mcelrea;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Tech on 3/5/2015.
 */
public interface Map {

    public void update(float delta);
    public void paint(SpriteBatch batch, OrthographicCamera camera);
    public void paintLoadScreen(SpriteBatch batch, OrthographicCamera camera);
    public void paintEndRoundScreen(SpriteBatch batch, OrthographicCamera camera);
}
