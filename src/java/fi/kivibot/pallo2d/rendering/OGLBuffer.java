package fi.kivibot.pallo2d.rendering;

/**
 *
 * @author Nicklas Ahlskog
 */
public class OGLBuffer extends OGLObject {

    private final int target;
    private final int size;

    public OGLBuffer(int id, int target, int size) {
        super(id);
        this.target = target;
        this.size = size;
    }

    public int getTarget() {
        return target;
    }

    public int getSize() {
        return size;
    }

}
