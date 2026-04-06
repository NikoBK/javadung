package com.nikobk.javadung;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class AssetHandler {

	static protected Texture defaultTexture = new Texture("images/default.png");
	
	private static HashMap<String, Texture> tileTextures = new HashMap<>();
	private static HashMap<String, Texture> charTextures = new HashMap<>();
	private static HashMap<String, Texture> objTextures = new HashMap<>();
	
	public static void init() {
		System.out.println("initializing asset handler...");
		
		//TODO: The current setup treats the Gdx internal files as (lwjgl3/assets) rather than (javadung/assets) like I want.
		loadTexturesTest();
		/*
		loadTileTextures();
		loadCharTextures();
		loadObjTextures();
		*/
	}
	
	//TODO: Hack-ish workaround for gdx internal files for now - should eventually be swapped out with recursive code
	private static void loadTexturesTest() {
		// tiles
		tileTextures.put("grass", new Texture("images/tiles/grass.png"));
		tileTextures.put("sand", new Texture("images/tiles/sand.png"));
		tileTextures.put("water", new Texture("images/tiles/water.png"));
		
		// characters
		charTextures.put("testWizard", new Texture("images/characters/testWizard.png"));
		
		// objects
		objTextures.put("rock", new Texture("images/objects/rock.png"));
	}
	
	private static void loadTileTextures() {
		System.out.println("Loading tile textures...");
		FileHandle dir = Gdx.files.internal("images/tiles");
		
		System.out.println("Exists: " + dir.exists());

		for (FileHandle file : dir.list()) {
			System.out.println("parsing file: " + file.name());
		    if (file.extension().equals("png")) {
		        Texture tex = new Texture(file);
		        tileTextures.put(file.nameWithoutExtension(), tex);
		        System.out.println("added texture: " + file.nameWithoutExtension() + " to tiles map");
		    }
		}
	}
	
	private static void loadCharTextures() {
		FileHandle dir = Gdx.files.internal("images/characters");

		for (FileHandle file : dir.list()) {
		    if (file.extension().equals("png")) {
		        Texture tex = new Texture(file);
		        charTextures.put(file.nameWithoutExtension(), tex);
		        System.out.println("added texture: " + file.nameWithoutExtension() + " to chars map");
		    }
		}
	}
	
	private static void loadObjTextures() {
		FileHandle dir = Gdx.files.internal("images/objects");

		for (FileHandle file : dir.list()) {
		    if (file.extension().equals("png")) {
		        Texture tex = new Texture(file);
		        objTextures.put(file.nameWithoutExtension(), tex);
		        System.out.println("added texture: " + file.nameWithoutExtension() + " to objs map");
		    }
		}
	}
	
	// Returns a texture if it exists in any of the hashmaps, otherwise returns default texture.
	public static Texture getTextureFromName(String name) {
		Texture tex;

		if ((tex = tileTextures.get(name)) != null) { return tex; }
		if ((tex = charTextures.get(name)) != null) { return tex; }
		if ((tex = objTextures.get(name)) != null) { return tex; }

		return defaultTexture;
	}
}
