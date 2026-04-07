package com.nikobk.javadung;

import com.badlogic.gdx.graphics.Texture;

public class GameObject {
	
	public float x;
	public float y;
	public String name;
	private Texture tex;
	
	public GameObject(float x, float y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.tex = AssetHandler.getTextureFromName(name);
	}
	
	public void draw(GameSprite gs, float width, float height) {
		gs.view.draw(tex, x, y, width, height);
	}
}
