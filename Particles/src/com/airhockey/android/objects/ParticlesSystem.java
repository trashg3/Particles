package com.airhockey.android.objects;

import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.glDrawArrays;
import android.graphics.Color;

import com.airhockey.android.Constants;
import com.airhockey.android.data.VertexArray;
import com.airhockey.android.programs.ParticleShaderProgram;
import com.airhockey.android.util.Geometry.Point;
import com.airhockey.android.util.Geometry.Vector;

public class ParticlesSystem {

	private static final int BROJ_KORDINATA = 3;
	private static final int BROJ_BOJA = 3;
	private static final int VECTOR_BROJ = 3;
	private static final int VRIJEME_POCETKA = 1;
	
	private static final int TOTAL_COMPONENT_COUNT = 
			(BROJ_KORDINATA
		   + BROJ_BOJA
		   + VECTOR_BROJ
		   + VRIJEME_POCETKA);
	
	private static final int STRIDE = TOTAL_COMPONENT_COUNT * Constants.BYTES_PER_FLOAT; 
	
	private final float particles [];
	private final int maxParticleCount;
	private final VertexArray vertexArray;
	
	private int currentParticleCount;
	private int nextParticle;
	
	
	public ParticlesSystem(int maxParticleCount) {
		particles = new float [maxParticleCount * TOTAL_COMPONENT_COUNT];
		vertexArray  = new VertexArray(particles);
		this.maxParticleCount = maxParticleCount;
	}
	
	public void addParticles(Point position, int color, Vector direction, float currentTime) {
		
		final int particleOffset = nextParticle * TOTAL_COMPONENT_COUNT;
		
		int currentOffset = particleOffset;
		nextParticle++;
		
		if (currentParticleCount < maxParticleCount) {
			currentParticleCount++;
		}
		
		if (nextParticle == maxParticleCount) {
			nextParticle = 0;
		}
		
		particles[currentOffset++] = position.x;
		particles[currentOffset++] = position.y;
		particles[currentOffset++] = position.z;
		
		particles[currentOffset++] = Color.red(color) / 255f;
		particles[currentOffset++] = Color.green(color) / 255f;
		particles[currentOffset++] = Color.blue(color) / 255f;
		
		particles[currentOffset++] = direction.x;
		particles[currentOffset++] = direction.y;
		particles[currentOffset++] = direction.z;
		
		particles[currentOffset++] = currentTime;
		
		vertexArray.updateBuffer(particles, particleOffset, TOTAL_COMPONENT_COUNT);
	}
	
	public void bindData(ParticleShaderProgram particleShaderProgram) {

		int dataOffset = 0;
		vertexArray.setVertexAttribPointer(dataOffset,
				particleShaderProgram.getPositionLocation(),
				BROJ_KORDINATA, STRIDE);
		dataOffset += BROJ_KORDINATA;

		vertexArray.setVertexAttribPointer(dataOffset,
				particleShaderProgram.getColorLocation(), BROJ_BOJA, STRIDE);
		dataOffset += BROJ_BOJA;

		vertexArray.setVertexAttribPointer(dataOffset,
				particleShaderProgram.getDirectionVectorLocation(),
				VECTOR_BROJ, STRIDE);
		dataOffset += VECTOR_BROJ;

		vertexArray.setVertexAttribPointer(dataOffset,
				particleShaderProgram.getVrijemeNastankaLocation(),
				VRIJEME_POCETKA, STRIDE);
		
	}
	
	public void draw() {
		glDrawArrays(GL_POINTS, 0, currentParticleCount);
	}
}
