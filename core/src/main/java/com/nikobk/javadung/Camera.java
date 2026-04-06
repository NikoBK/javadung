package com.nikobk.javadung;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {
	
	private GameSprite gs;
	private OrthographicCamera camera;

	public Camera(GameSprite gs, float viewportWidth, float viewportHeight) {
		this.gs = gs;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, viewportWidth, viewportHeight);
	}
	
	public void Update() {
		camera.position.set(gs.playerX, gs.playerY, 0);
        camera.update();
        gs.view.setProjectionMatrix(camera.combined);
	}
	
}
