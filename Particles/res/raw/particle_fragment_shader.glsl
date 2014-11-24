precision mediump float;

varying vec3 v_Color;
varying float v_ProtekloVrijeme;

void main(){

	gl_FragColor = vec4(v_Color / v_ProtekloVrijeme, 1.0);
	
	}