package com.airhockey.android.objects;

import com.airhockey.android.util.Geometry.Point;
import com.airhockey.android.util.Geometry.Vector;

public class ParticleShooter {

	private final Point position;
	private final Vector direction;
	private final int color;

	public ParticleShooter(Point position, Vector direction, int color) {

		this.position = position;
		this.direction = direction;
		this.color = color;
	}
	
	public void addParticle(ParticlesSystem particlesSystem, float currentTime, int count) {
		
		for (int i = 0; i < count; i++) {
			particlesSystem.addParticles(position, color, direction, currentTime);
		}
	}
}
