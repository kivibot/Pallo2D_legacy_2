package fi.kivibot.pallo2d.rendering;

import java.util.List;

/**
 *
 * @author Nicklas
 */
public class TileMap extends Geometry {

    private TileSet tileSet;

    public TileMap(List<Texture> textures, Shader shader, Mesh mesh, TileSet tileSet) {
        super(textures, shader, mesh);
        this.tileSet = tileSet;
    }

}
