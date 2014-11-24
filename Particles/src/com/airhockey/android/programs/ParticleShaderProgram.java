package com.airhockey.android.programs;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1f;
import static android.opengl.GLES20.glUniformMatrix4fv;

import com.openGL.particles.R;

import android.content.Context;

public class ParticleShaderProgram extends ShaderProgram {

	private final int uMatrixLocation;
	private final int uTimeLocation;
	
	private final int aPositionLocation;
	private final int aColorLocation;
	private final int aDirectionVectorLocation;
	private final int aVrijemeNastankaLocation;
	
	
	public ParticleShaderProgram(Context context) {
		super(context, R.raw.particles_vertex_shader, R.raw.particle_fragment_shader);
		
		uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
		uTimeLocation = glGetUniformLocation(program, U_TIME);
		
		aPositionLocation = glGetAttribLocation(program, A_POSITION);
		aColorLocation = glGetAttribLocation(program, A_COLOR);
		aDirectionVectorLocation = glGetAttribLocation(program, A_DIRECTION_VECTOR);
		aVrijemeNastankaLocation = glGetAttribLocation(program, A_VRIJEME_NASTANKA);
	}
	
	public void setUniforms(float[] matrix, float protekloVrijeme) {

		glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
		glUniform1f(uTimeLocation, protekloVrijeme);
	}

	public int getPositionLocation() {
		return aPositionLocation;
	}

	public int getColorLocation() {
		return aColorLocation;
	}

	public int getDirectionVectorLocation() {
		return aDirectionVectorLocation;
	}

	public int getVrijemeNastankaLocation() {
		return aVrijemeNastankaLocation;
	}
	
	
	
}
