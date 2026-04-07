package com.nikobk.javadung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends GameObject {
	
	private GameSprite gs;

	public Player(GameSprite gs, float x, float y, String name, boolean collision, boolean trigger) {
		super(x, y, name, collision, trigger);
		this.gs = gs;
	}

	public void update() {
		float newX = x;
		float newY = y;
		
        //TODO: normalize for diagonal vectors
        if (Gdx.input.isKeyPressed(Input.Keys.W)) newY += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.S)) newY -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) newX -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) newX += 200 * Gdx.graphics.getDeltaTime();
        
        if (!gs.map.isBlocked(newX, y, gs.spriteSize * gs.scale)) {
            x = newX;
        }
        if (!gs.map.isBlocked(x, newY, gs.spriteSize * gs.scale)) {
            y = newY;
        }
        
        gs.map.checkTriggers(x, y, gs.spriteSize * gs.scale);
	}
}
