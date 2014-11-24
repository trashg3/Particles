uniform mat4 u_Matrix;
uniform float u_Time;

attribute vec3 a_Position;
attribute vec3 a_Color;
attribute vec3 a_DirectionVector;
attribute float a_VrijemeNastanka;

varying vec3 v_Color;
varying float v_ProtekloVrijeme;

void main(){
	
	v_Color = a_Color;
	v_ProtekloVrijeme = u_Time - a_VrijemeNastanka;
	vec3 TrenutnaPozicija = a_Position + (a_DirectionVector * v_ProtekloVrijeme);
	gl_Position = u_Matrix * vec4(TrenutnaPozicija, 1.0);
	gl_PointSize = 10.0;
	
	}

	