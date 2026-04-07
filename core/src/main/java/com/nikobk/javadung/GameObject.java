package com.nikobk.javadung;

import com.badlogic.gdx.graphics.Texture;

public class GameObject {
	
	public float x;
	public float y;
	public String name;
	private Texture tex;
	public boolean collision = false;
	public boolean trigger = false;
	public boolean enabled = true;
	
	public GameObject(float x, float y, String name, boolean collision, boolean trigger) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.tex = AssetHandler.getTextureFromName(name);
		this.collision = collision;
		this.trigger = trigger;
	}
	
	public void draw(GameSprite gs, float width, float height) {
		gs.view.draw(tex, x, y, width, height);
	}
}
