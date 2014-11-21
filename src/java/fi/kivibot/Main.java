package fi.kivibot;

import fi.kivibot.pallo2d.Application;
import fi.kivibot.pallo2d.rendering.Geometry;
import fi.kivibot.pallo2d.rendering.Mesh;
import fi.kivibot.pallo2d.rendering.Shader;
import fi.kivibot.pallo2d.rendering.Texture;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
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
            }

            @Override
            protected void onLoop() {
                getRenderer().render(g);
            }
        }.start();
    }
}
