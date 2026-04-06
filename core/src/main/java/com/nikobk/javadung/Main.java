package com.nikobk.javadung;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    // private Texture image;
    private BitmapFont font;
    
    // Player texture
    Texture playerTexture;
    
    // Player props
    float x = 100;
    float y = 100;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // image = new Texture("libgdx.png");
        // font = new BitmapFont();
        
        // Player init
        playerTexture = new Texture("player.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        
        // Player controls
        //TODO: normalize for diagonal vectors
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += 200 * Gdx.graphics.getDeltaTime();

        batch.begin();
        batch.draw(playerTexture, x, y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        // image.dispose();
    }
}
