package fi.kivibot.pallo2d;

import fi.kivibot.pallo2d.assets.SimpleAssetManager;
import fi.kivibot.pallo2d.rendering.Renderer;
import java.nio.ByteBuffer;
import org.lwjgl.Sys;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GLContext;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.system.glfw.ErrorCallback;
import static org.lwjgl.system.glfw.GLFW.*;
import org.lwjgl.system.glfw.GLFWvidmode;
import org.lwjgl.system.glfw.WindowCallback;
import org.lwjgl.system.glfw.WindowCallbackAdapter;

/**
 *
 * @author Nicklas Ahlskog
 */
public abstract class Application {

    private final String name;
    private long windowId;
    private GLContext glContext;
    private SimpleAssetManager assetManager;
    private Renderer renderer;

    protected SimpleAssetManager getAssetManager() {
        return assetManager;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Application(String name) {
        this.name = name;
    }

    public void start() {
        new Thread(() -> {
            this.run();
        }).start();
    }

    private void run() {
        init();
        loop();
        clean();
    }

    private void init() {
        Sys.touch(); //UGLY HACK
        glfwSetErrorCallback(ErrorCallback.Util.getDefault());

        if (glfwInit() != GL_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        int WIDTH = 1280;
        int HEIGHT = 720;

        windowId = glfwCreateWindow(WIDTH, HEIGHT, name, NULL, NULL);
        if (windowId == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        WindowCallback.set(windowId, new WindowCallbackAdapter() {
            @Override
            public void key(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, GL_TRUE);
                }
            }
        });

        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                windowId,
                (GLFWvidmode.width(vidmode) - WIDTH) / 2,
                (GLFWvidmode.height(vidmode) - HEIGHT) / 2
        );

        glfwMakeContextCurrent(windowId);
        glfwSwapInterval(1);

        glfwShowWindow(windowId);
        glContext = GLContext.createFromCurrent();

        assetManager = new SimpleAssetManager(glContext);
        renderer = new Renderer(glContext);
        onInit();
    }

    private void loop() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        while (glfwWindowShouldClose(windowId) == GL_FALSE) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            onLoop();

            glfwSwapBuffers(windowId);
            glfwPollEvents();
        }
    }

    private void clean() {
        glContext.destroy();
        glfwDestroyWindow(windowId);
    }

    protected abstract void onInit();

    protected abstract void onLoop();

}
