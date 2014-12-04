#version 150


uniform mat3 cam;

in vec2 in_Position;
in vec2 in_TexCoord;

out vec2 pass_TexCoord;



void main(){
    gl_Position = vec4(cam * vec3(in_Position, 1.0), 1.0);
    pass_TexCoord = in_TexCoord;
}