/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
 ***/
package com.airhockey.android.programs;

import static android.opengl.GLES20.glUseProgram;
import android.content.Context;

import com.airhockey.android.util.ShaderHelper;
import com.airhockey.android.util.TextResourceReader;

abstract class ShaderProgram {
	// Uniform constants
	protected static final String U_MATRIX = "u_Matrix";
	protected static final String U_TIME = "u_Time";

	// Attribute constants
	protected static final String A_POSITION = "a_Position";
	protected static final String A_COLOR = "a_Color";
	protected static final String A_DIRECTION_VECTOR = "a_DirectionVector";
	protected static final String A_VRIJEME_NASTANKA = "a_VrijemeNastanka";

	// Shader program
	protected final int program;

	protected ShaderProgram(Context context, int vertexShaderResourceId,
			int fragmentShaderResourceId) {
		// Compile the shaders and link the program.
		program = ShaderHelper.buildProgram(TextResourceReader
				.readTextFileFromResource(context, vertexShaderResourceId),
				TextResourceReader.readTextFileFromResource(context,
						fragmentShaderResourceId));
	}

	public void useProgram() {
		// Set the current OpenGL shader program to this program.
		glUseProgram(program);
	}
}
