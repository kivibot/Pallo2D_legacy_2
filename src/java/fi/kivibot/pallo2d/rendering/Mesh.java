package fi.kivibot.pallo2d.rendering;

import java.util.List;

/**
 *
 * @author Nicklas Ahlskog
 */
public class Mesh extends GLObject {

    private final List<GLBuffer> buffers;
    private final GLBuffer indices;

    public Mesh(int id, List<GLBuffer> buffers, GLBuffer indices) {
        super(id);
        this.buffers = buffers;
        this.indices = indices;
    }

    public Mesh(List<GLBuffer> buffers, GLBuffer indices) {
        this.buffers = buffers;
        this.indices = indices;
    }

    public List<GLBuffer> getBuffers() {
        return buffers;
    }

    public GLBuffer getIndices() {
        return indices;
    }

}
