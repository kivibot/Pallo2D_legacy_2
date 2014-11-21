#version 150

uniform sampler2D samp[1];

in vec2 pass_TexCoord;

out vec4 out_Color;

void main(){
    out_Color = texture2D(samp[0], pass_TexCoord);
}