package fi.kivibot.pallo2d.rendering;

/**
 *
 * @author Nicklas Ahlskog
 */
public abstract class OGLObject {

    private final int id;

    public OGLObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
}
