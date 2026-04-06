package com.nikobk.javadung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameSprite
{
	protected SpriteBatch view;
	private Camera camera;
	private Map map;
	
    // Test player
	private Texture playerTexture;
    float playerX = 100;
    float playerY = 100;
    
    // View
    protected float spriteSize = 8f; // Texture resolution for object assets (8x8)
    protected float scale = 8f; // Scale 8x8 textures on the gamesprite
    protected int tileSize = 8; // Texture resolution for tile assets (8x8)
	
	public GameSprite(SpriteBatch view) {
		this.view = view;
		this.camera = new Camera(this, 800, 600);
		this.map = new Map(this);
		
		playerTexture = new Texture("images/characters/player.png");
	}
	
	public void draw() {
		
		// Player controls
        //TODO: normalize for diagonal vectors
        if (Gdx.input.isKeyPressed(Input.Keys.W)) playerY += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.S)) playerY -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) playerX -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) playerX += 200 * Gdx.graphics.getDeltaTime();
        
        // Update before map and view draws to not be 1 frame behind
        camera.update();
        
        map.draw();
        view.draw(playerTexture, playerX, playerY, spriteSize * scale, spriteSize * scale);
	}
}