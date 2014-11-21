package fi.kivibot.pallo2d.rendering;

import java.util.List;

/**
 *
 * @author Nicklas Ahlskog
 */
public class Mesh extends OGLObject {

    private final List<OGLBuffer> buffers;
    private final OGLBuffer indices;

    public Mesh(int id, List<OGLBuffer> buffers, OGLBuffer indices) {
        super(id);
        this.buffers = buffers;
        this.indices = indices;
    }

    public List<OGLBuffer> getBuffers() {
        return buffers;
    }

    public OGLBuffer getIndices() {
        return indices;
    }

}
