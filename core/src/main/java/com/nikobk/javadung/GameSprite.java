package com.nikobk.javadung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nikobk.javadung.Main.GameState;

public class GameSprite
{
	public GameState state = GameState.MENU;
	
	protected SpriteBatch view;
	private Camera camera;
	protected Map map;
	
    // Test player
	protected Player player;
    
    // View
    protected float spriteSize = 8f; // Texture resolution for object assets (8x8)
    protected float scale = 8f; // Scale 8x8 textures on the gamesprite
    protected int tileSize = 8; // Texture resolution for tile assets (8x8)
    
    // Gameplay
    public int keysFound = 0;
	
	public GameSprite(SpriteBatch view) {
		this.view = view;
		this.camera = new Camera(this, 800, 600);
		AssetHandler.init();
		this.map = new Map(this);	
		this.player = new Player(this, 1400, 600, "testWizard", false, false);
	}
	
	public void draw() {
		
		player.update();          
        camera.update(); // Update before map and view draws to not be 1 frame behind  
        map.draw();
        player.draw(this, spriteSize * scale, spriteSize * scale);
        
	}
	
	public void updateKeys() {
		keysFound += 1;
		
		if (keysFound == 4) {
			System.out.println("all keys collected!");
			map.door.enabled = false;
		}
	}
	
	public void reset() {
	    player.x = 100;
	    player.y = 100;

	    // reset map + objects
	    map = new Map(this);

	    // reset any counters (like keys)
	    this.keysFound = 0;
	}
}