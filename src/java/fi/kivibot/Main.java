package fi.kivibot;

import fi.kivibot.pallo2d.Application;
import fi.kivibot.pallo2d.math.Vector2f;
import fi.kivibot.pallo2d.rendering.Camera;
import fi.kivibot.pallo2d.rendering.Geometry;
import fi.kivibot.pallo2d.rendering.Mesh;
import fi.kivibot.pallo2d.rendering.Shader;
import fi.kivibot.pallo2d.rendering.Texture;
import fi.kivibot.pallo2d.rendering.TileSet;
import fi.kivibot.pallo2d.rendering.buffers.GLIntBuffer;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.glfw.GLFW;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Nicklas Ahlskog
 */
public class Main {

    public static void main(String[] args) {
        new Application("Mui.") {

            private Geometry g;

            @Override
            protected void onInit() {
                Mesh m = getAssetManager().loadMesh("src/resources/mesh.yaml");
                Shader s = getAssetManager().loadShader("src/resources/vert.glsl",
                        "src/resources/frag.glsl");
                ArrayList<Texture> tl = new ArrayList<>();
                tl.add(getAssetManager().loadTexture("src/resources/img00.png"));
                g = new Geometry(tl, s, m);
                g.setScale(1, 1);
                getInput().bindKey("foo", GLFW.GLFW_KEY_LEFT);
                getInput().bindKey("bar", GLFW.GLFW_KEY_RIGHT);
            
                TileSet ts = getAssetManager().loadTileSet("src/resources/tileset.yaml");
            }

            @Override
            protected void onLoop(float delta) {
                
            }
        }.start();
    }
}
