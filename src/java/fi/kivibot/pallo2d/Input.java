package fi.kivibot.pallo2d;

import fi.kivibot.pallo2d.math.Vector2f;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.lwjgl.BufferUtils;
import static org.lwjgl.system.glfw.GLFW.*;
import org.lwjgl.system.glfw.WindowCallback;
import org.lwjgl.system.glfw.WindowCallbackAdapter;

/**
 *
 * @author Nicklas
 */
public class Input {

    private final Map<String, Integer> keyBindMap = new HashMap<>();
    private final Map<Integer, String> rKeyBindMap = new HashMap<>();

    private final Map<String, Boolean> keyPressedMap = new HashMap<>();
    private final Map<String, Integer> keyStatusMap = new HashMap<>();

    private final long windowId;
    private final DoubleBuffer a, b;
    private final IntBuffer c, d;

    public Input(long windowId) {
        this.windowId = windowId;
        WindowCallback.set(windowId, new WindowCallbackAdapter() {
            @Override
            public void key(long window, int key, int scancode, int action, int mods) {
                String name = rKeyBindMap.get(key);
                if (name != null) {
                    if (action == GLFW_PRESS) {
                        keyPressedMap.put(name, true);
                    }
                    keyStatusMap.put(name, action);
                }
            }
        });
        a = BufferUtils.createDoubleBuffer(1);
        b = BufferUtils.createDoubleBuffer(1);
        c = BufferUtils.createIntBuffer(1);
        d = BufferUtils.createIntBuffer(1);
    }

    public void bindKey(String name, int key) {
        if (rKeyBindMap.containsKey(key)) {
            throw new RuntimeException("Key was already bound: " + key);
        }
        Integer oldKey = keyBindMap.get(name);
        if (oldKey != null) {
            rKeyBindMap.remove(oldKey);
        }
        keyBindMap.put(name, key);
        rKeyBindMap.put(key, name);
    }

    public boolean isPressed(String name) {
        /*
         null -> false
         false -> false
         true -> true
         */
        return Objects.equals(keyStatusMap.get(name), GLFW_PRESS) || Objects.equals(keyStatusMap.get(name), GLFW_REPEAT);
    }

    public boolean wasPressed(String name) {
        /*
         null -> false
         false -> false
         true -> true
         */
        return Boolean.TRUE.equals(keyPressedMap.get(name));
    }

    public void resetKeyPressedMap() {
        keyPressedMap.clear();
    }

    public Vector2f getCursorPosition() {
        a.clear();
        b.clear();
        glfwGetCursorPos(windowId, a, b);
        return new Vector2f((float) a.get(), (float) b.get());
    }

    public Vector2f getCursorPosition2() {
        a.clear();
        b.clear();
        c.clear();
        d.clear();
        glfwGetCursorPos(windowId, a, b);
        glfwGetWindowSize(windowId, c, d);
        return new Vector2f((float) a.get() / (float) c.get(), (float) b.get() / (float) d.get());
    }

}
