package fi.kivibot.pallo2d.rendering;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;

/**
 *
 * @author Nicklas Ahlskog
 */
public class GLBuffer extends GLObject {

    public static enum Type {

        INT, FLOAT, UNDEF
    }

    private final int target;
    private final int length;
    private final int vecSize;
    private final Type type;
    protected boolean updated = false, resized = false;

    public GLBuffer(int id, int target, int length, int vecSize, Type type) {
        super(id);
        this.target = target;
        this.length = length;
        this.vecSize = vecSize;
        this.type = type;
    }

    protected GLBuffer(int target, int length, int vecSize, Type type) {
        this.target = target;
        this.length = length;
        this.vecSize = vecSize;
        this.type = type;
    }

    public int getTarget() {
        return target;
    }

    public int getLength() {
        return length;
    }

    public int getVecSize() {
        return vecSize;
    }

    public Type getType() {
        return type;
    }

    public boolean isUpdated() {
        return updated;
    }

    public boolean isResized() {
        return resized;
    }

    public void updated() {
        resized = false;
        updated = false;
    }

}
