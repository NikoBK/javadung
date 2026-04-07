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
		AssetHandler.init();
		this.map = new Map(this);
		
		playerTexture = AssetHandler.getTextureFromName("testWizard");
	}
	
	public void draw() {
		
		// Player controls
		float newX = playerX;
		float newY = playerY;
		
        //TODO: normalize for diagonal vectors
        if (Gdx.input.isKeyPressed(Input.Keys.W)) newY += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.S)) newY -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) newX -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) newX += 200 * Gdx.graphics.getDeltaTime();
        
        if (!map.isBlocked(newX, playerY, spriteSize * scale)) {
            playerX = newX;
        }
        if (!map.isBlocked(playerX, newY, spriteSize * scale)) {
            playerY = newY;
        }
        
        // Update before map and view draws to not be 1 frame behind
        camera.update();
        
        map.draw();
        view.draw(playerTexture, playerX, playerY, spriteSize * scale, spriteSize * scale);
	}
}