package fi.kivibot.pallo2d.rendering.buffers;

import fi.kivibot.pallo2d.rendering.GLBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

/**
 *
 * @author Nicklas
 */
public class GLFloatBuffer extends GLBuffer {

    private FloatBuffer buff;

    public GLFloatBuffer(int target, int length, int vecSize) {
        super(target, length, vecSize, GLBuffer.Type.FLOAT);
        buff = BufferUtils.createFloatBuffer(0);
        updated = true;
        resized = true;
    }

    public void setData(float[] data) {
        if (data.length != buff.capacity()) {
            resized = true;
            buff = BufferUtils.createFloatBuffer(data.length);
        }
        updated = true;
        buff.clear();
        buff.put(data);
        buff.flip();
    }

    public FloatBuffer getBuffer() {
        return buff;
    }

}
