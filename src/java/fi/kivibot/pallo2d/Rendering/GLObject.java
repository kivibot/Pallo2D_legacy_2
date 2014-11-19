package fi.kivibot.pallo2d.Rendering;

/**
 *
 * @author Nicklas Ahlskog
 */
public abstract class GLObject {

    private final int id;

    public GLObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
}
