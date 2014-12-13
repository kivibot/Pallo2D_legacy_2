package fi.kivibot.pallo2d.rendering;

import fi.kivibot.pallo2d.math.Vector2f;

/**
 *
 * @author Nicklas
 */
public class TileSet {

    private final Texture texture;
    private final Vector2f size;

    public TileSet(Texture texture, Vector2f size) {
        this.texture = texture;
        this.size = size;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2f getSize() {
        return size;
    }

}
