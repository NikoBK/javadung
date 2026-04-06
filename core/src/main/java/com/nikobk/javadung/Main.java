package com.nikobk.javadung;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	
    private SpriteBatch batch;
    private GameSprite gs;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gs = new GameSprite(batch);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        gs.Draw();        
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
