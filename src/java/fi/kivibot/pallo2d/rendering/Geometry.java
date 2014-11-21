package fi.kivibot.pallo2d.rendering;

import java.util.List;

/**
 *
 * @author Nicklas Ahlskog
 */
public class Geometry {

    private final List<Texture> textures;
    private final Shader shader;
    private final Mesh mesh;

    public Geometry(List<Texture> textures, Shader shader, Mesh mesh) {
        this.textures = textures;
        this.shader = shader;
        this.mesh = mesh;
    }

    public List<Texture> getTextures() {
        return textures;
    }

    public Shader getShader() {
        return shader;
    }

    public Mesh getMesh() {
        return mesh;
    }

}
