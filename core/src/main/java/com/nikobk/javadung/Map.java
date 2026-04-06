package com.nikobk.javadung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Map {
	
	private GameSprite gs;
	int[][] map;
    Texture grass, water, sand;
	
	public Map(GameSprite gs) {
		
		this.gs = gs;
		
		grass = new Texture("images/tiles/grass.png");
        water = new Texture("images/tiles/water.png");
        sand = new Texture("images/tiles/sand.png");
        
        String[] lines = Gdx.files.internal("maps/placeholder.txt").readString().split("\\n");
        map = new int[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            String[] nums = lines[i].split(" ");
            map[i] = new int[nums.length];
            for (int j = 0; j < nums.length; j++) {
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }
	}
	
	public void draw() {
		for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {

                Texture tex = grass;
                if (map[row][col] == 1) tex = water;
                if (map[row][col] == 2) tex = sand;

                gs.view.draw(tex,
                    col * gs.tileSize * gs.scale,
                    row * gs.tileSize * gs.scale,
                    gs.tileSize * gs.scale,
                    gs.tileSize * gs.scale
                );
            }
        }
	}
	
}
