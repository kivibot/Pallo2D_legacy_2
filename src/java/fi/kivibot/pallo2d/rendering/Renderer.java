package fi.kivibot.pallo2d.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

/**
 *
 * @author Nicklas Ahlskog
 */
public class Renderer {

    private final GLContext glContext;

    public Renderer(GLContext glContext) {
        this.glContext = glContext;
    }

    public void render(Geometry g) {
        glBindVertexArray(g.getMesh().getId());
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, g.getMesh().getIndices().getId());
        glUseProgram(g.getShader().getId());
        for (int i = 0; i < g.getTextures().size(); i++) {
            glActiveTexture(0);
            glBindTexture(GL11.GL_TEXTURE_2D, g.getTextures().get(i).getId());
        }
        for (int i = 0; i < g.getMesh().getBuffers().size(); i++) {
            glEnableVertexAttribArray(i);
        }

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);

        for (int i = 0; i < g.getMesh().getBuffers().size(); i++) {
            glDisableVertexAttribArray(i);
        }
        for (int i = 0; i < g.getTextures().size(); i++) {
            glActiveTexture(0);
            glBindTexture(GL11.GL_TEXTURE_2D, 0);
        }
        glUseProgram(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

}
