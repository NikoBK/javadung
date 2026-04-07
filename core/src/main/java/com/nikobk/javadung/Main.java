package com.nikobk.javadung;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	
	enum GameState {
	    MENU,
	    PLAYING,
	    GAMEOVER
	}
	
	// UI
	BitmapFont font;
	Rectangle playButton = new Rectangle(300, 250, 200, 60);
	
    private SpriteBatch batch;
    private GameSprite gs;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        gs = new GameSprite(batch);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        
        if (gs == null) {
        	return;
        }
        
        font.getData().setScale(3f);
        if (Gdx.input.justTouched()) {
            float mx = Gdx.input.getX();
            float my = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (gs.state == GameState.MENU && playButton.contains(mx, my)) {
            	if (gs.keysFound > 0) {
            		gs.reset();
            	}
            	gs.state = GameState.PLAYING;
            }

            if (gs.state == GameState.GAMEOVER && playButton.contains(mx, my)) {
            	gs.state = GameState.MENU;
            }
        }
        
        if (gs.state == GameState.MENU) {
            font.draw(batch, "Java LibGDX Demo", 130, 400);
            font.draw(batch, "[ PLAY ]", 260, 280);

        } else if (gs.state == GameState.PLAYING) {
            gs.draw();

        } else if (gs.state == GameState.GAMEOVER) {
        	resetProjection();
            font.draw(batch, "GAME OVER", 200, 400);
            font.draw(batch, "[ MENU ]", 240, 280);
        }
        
        batch.end();
    }
    
    public void resetProjection() {
    	batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, 
    		    Gdx.graphics.getWidth(), 
    		    Gdx.graphics.getHeight()));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
