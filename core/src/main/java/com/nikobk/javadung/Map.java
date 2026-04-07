package com.nikobk.javadung;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Map {
	
	private GameSprite gs;
	int[][] map;
    Texture grass, water, sand;
    ArrayList<GameObject> objects = new ArrayList<>();
    public GameObject door;
	
	public Map(GameSprite gs) {
		
		this.gs = gs;
		
		grass = AssetHandler.getTextureFromName("grass");
        water = AssetHandler.getTextureFromName("water");
        sand = 	AssetHandler.getTextureFromName("sand");
        
        String[] lines = Gdx.files.internal("maps/placeholder.txt").readString().split("\\n");
        map = new int[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            String[] nums = lines[i].split(" ");
            map[i] = new int[nums.length];
            for (int j = 0; j < nums.length; j++) {
            	String val = nums[j];
            	
            	switch(val) {
            	
            	// rock
            	case "R":
            		System.out.println("rock at " + i + ", " + j+ "!");
            		map[i][j] = 2; // sand underneath

            	    objects.add(new GameObject(
            	        j * gs.tileSize * gs.scale,
            	        i * gs.tileSize * gs.scale,
            	        "rock",
            	        true,
            	        false
            	    ));
            		break;
            		
            	// key
            	case "K":
            		System.out.println("key at " + i + ", " + j+ "!");
            		map[i][j] = 0; // grass underneath

            	    objects.add(new GameObject(
            	        j * gs.tileSize * gs.scale,
            	        i * gs.tileSize * gs.scale,
            	        "key",
            	        false,
            	        true
            	    ));
            		break;
            		
            	// tree
            	case "T":
            		map[i][j] = 0; // grass underneath

            	    objects.add(new GameObject(
            	        j * gs.tileSize * gs.scale,
            	        i * gs.tileSize * gs.scale,
            	        "tree",
            	        true,
            	        false
            	    ));
            		break;
            		
            	// bush
            	case "B":
            		map[i][j] = 0; // grass underneath

            	    objects.add(new GameObject(
            	        j * gs.tileSize * gs.scale,
            	        i * gs.tileSize * gs.scale,
            	        "bush",
            	        true,
            	        false
            	    ));
            		break;
            		
            	// wall
            	case "W":
            		map[i][j] = 0; // grass underneath

            	    objects.add(new GameObject(
            	        j * gs.tileSize * gs.scale,
            	        i * gs.tileSize * gs.scale,
            	        "wall",
            	        true,
            	        false
            	    ));
            		break;
            		
        		// treasure
            	case "G":
            		map[i][j] = 2; // sand underneath

            	    objects.add(new GameObject(
            	        j * gs.tileSize * gs.scale,
            	        i * gs.tileSize * gs.scale,
            	        "treasure",
            	        false,
            	        true
            	    ));
            		break;
            		
        		// door
            	case "D":
            		map[i][j] = 0; // grass underneath

            	    this.door = new GameObject(
            	        j * gs.tileSize * gs.scale,
            	        i * gs.tileSize * gs.scale,
            	        "door",
            	        true,
            	        false
            	    );
            	    objects.add(this.door);
            		break;
            		
            	default:
            		map[i][j] = Integer.parseInt(val);
            		break;
            	}
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
		
		for (GameObject obj : objects) {
		    if (!obj.enabled) continue;
		    float size = gs.tileSize * gs.scale;
		    obj.draw(gs, size, size);
		}
	}
	
	// simple AABB collision check
	public boolean isBlocked(float x, float y, float size) {
	    for (GameObject obj : objects) {
	        if (!obj.collision || !obj.enabled) continue;

	        if (x < obj.x + size &&
	            x + size > obj.x &&
	            y < obj.y + size &&
	            y + size > obj.y) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void checkTriggers(float x, float y, float size) {
	    for (GameObject obj : objects) {
	        if (!obj.trigger || !obj.enabled) continue;

	        if (x < obj.x + size &&
	            x + size > obj.x &&
	            y < obj.y + size &&
	            y + size > obj.y) {

	            onTrigger(obj);
	        }
	    }
	}
	
	private void onTrigger(GameObject obj) {
	    if (obj.name == "key") {
	    	gs.updateKeys();
		    obj.enabled = false;
	    }
	    else { // treasure
	    	System.out.println("Game over!");
	    	//TODO
	    }
	}
	
}
