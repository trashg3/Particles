/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
 ***/
package com.airhockey.android;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.airhockey.android.objects.ParticleShooter;
import com.airhockey.android.objects.ParticlesSystem;
import com.airhockey.android.programs.ParticleShaderProgram;
import com.airhockey.android.util.MatrixHelper;
import com.airhockey.android.util.Geometry.Point;
import com.airhockey.android.util.Geometry.Vector;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

public class ParticlesRenderer implements Renderer {

	private final Context context;
	private ParticleShaderProgram particleShaderProgram;
	private ParticlesSystem particlesSystem;
	private ParticleShooter redParticleShooter;
	private ParticleShooter greenParticleShooter;
	private ParticleShooter bluepaParticleShooter;
	private long globalStartTime;

	private final float[] projectionMatrix = new float[16];
	private final float[] viewMatrix = new float[16];
	private final float[] viewProjectionMatrix = new float[16];

	public ParticlesRenderer(Context context) {

		this.context = context;
	}

	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		particleShaderProgram = new ParticleShaderProgram(context);
		particlesSystem = new ParticlesSystem(10000);
		globalStartTime = System.nanoTime();

		final Vector particleDirection = new Vector(0f, 0.5f, 0f);

		redParticleShooter = new ParticleShooter(new Point(-1f, 0f, 0f),
				particleDirection, Color.rgb(25, 255, 25));

		greenParticleShooter = new ParticleShooter(new Point(0f, 0f, 0f),
				particleDirection, Color.rgb(25, 255, 25));

		bluepaParticleShooter = new ParticleShooter(new Point(1f, 0f, 0f),
				particleDirection, Color.rgb(50, 50, 255));
	}

	@Override
	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		// Set the OpenGL viewport to fill the entire surface.
		glViewport(0, 0, width, height);

		MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width
				/ (float) height, 1f, 10f);
		Matrix.setIdentityM(viewMatrix, 0);
		Matrix.translateM(viewMatrix, 0, 0f, -1.5f, -5f);
		Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0,
				viewMatrix, 0);
	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		// Clear the rendering surface.
		glClear(GL_COLOR_BUFFER_BIT);

		float currentTime = (System.nanoTime() - globalStartTime) / 1000000000;

		redParticleShooter.addParticle(particlesSystem, currentTime, 5);
		greenParticleShooter.addParticle(particlesSystem, currentTime, 5);
		bluepaParticleShooter.addParticle(particlesSystem, currentTime, 5);

		particleShaderProgram.useProgram();
		particleShaderProgram.setUniforms(viewProjectionMatrix, currentTime);
		particlesSystem.bindData(particleShaderProgram);
		particlesSystem.draw();

		
	}
}