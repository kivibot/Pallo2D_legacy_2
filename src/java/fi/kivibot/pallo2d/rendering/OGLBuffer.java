package fi.kivibot.pallo2d.rendering;

/**
 *
 * @author Nicklas Ahlskog
 */
public class OGLBuffer extends OGLObject {

    private final int target;

    public OGLBuffer(int id, int target) {
        super(id);
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
    
    

}
