package fi.kivibot.pallo2d.rendering.buffers;

import fi.kivibot.pallo2d.rendering.GLBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

/**
 *
 * @author Nicklas
 */
public class GLIntBuffer extends GLBuffer {

    private IntBuffer buff;

    public GLIntBuffer(int target, int length, int vecSize) {
        super(target, length, vecSize, Type.INT);
        buff = BufferUtils.createIntBuffer(0);
        updated = true;
        resized = true;
    }

    public void setData(int[] data) {
        if (data.length != buff.capacity()) {
            resized = true;
            buff = BufferUtils.createIntBuffer(data.length);
        }
        updated = true;
        buff.clear();
        buff.put(data);
        buff.flip();
    }

    public IntBuffer getBuffer() {
        return buff;
    }

}
