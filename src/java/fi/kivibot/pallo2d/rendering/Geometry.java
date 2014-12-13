package fi.kivibot.pallo2d.rendering;

import java.util.List;

/**
 *
 * @author Nicklas Ahlskog
 */
public class Geometry extends Transformable {

    private List<Texture> textures;
    private Shader shader;
    private Mesh mesh;

    public Geometry(List<Texture> textures, Shader shader, Mesh mesh) {
        this.textures = textures;
        this.shader = shader;
        this.mesh = mesh;
    }

    public Geometry() {
    }

    public List<Texture> getTextures() {
        return textures;
    }

    public void setTextures(List<Texture> textures) {
        this.textures = textures;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

}
