package com.nikobk.javadung;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    
    // Player texture
    Texture playerTexture;
    
    // Gamesprite (rendering)
    float spriteSize = 8f; // Texture resolution for object assets (8x8)
    float scale = 8f; // Scale 8x8 textures on the gamesprite
    int tileSize = 8; // Texture resolution for tile assets (8x8)
    
    // Player props
    float x = 100;
    float y = 100;
    
    // Map
    int[][] map;
    Texture grass, water, sand;
    
    // Camera
    OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        
        // Initialize camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        
        // Initialize map
        grass = new Texture("grass.png");
        water = new Texture("water.png");
        sand = new Texture("sand.png");

        String[] lines = Gdx.files.internal("map.txt").readString().split("\\n");
        map = new int[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            String[] nums = lines[i].split(" ");
            map[i] = new int[nums.length];
            for (int j = 0; j < nums.length; j++) {
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }
        
        // Initialize player
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
        
        // Create the gamesprite
        batch.begin();
        
        // Setup the camera
        camera.position.set(x, y, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        // Render the in-game map
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {

                Texture tex = grass;
                if (map[row][col] == 1) tex = water;
                if (map[row][col] == 2) tex = sand;

                batch.draw(tex,
                    col * tileSize * scale,
                    row * tileSize * scale,
                    tileSize * scale,
                    tileSize * scale
                );
            }
        }
        
        // Render player
        batch.draw(playerTexture, x, y, spriteSize * scale, spriteSize * scale);
        
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        // image.dispose();
    }
}
