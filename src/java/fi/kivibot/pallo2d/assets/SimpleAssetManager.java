package fi.kivibot.pallo2d.assets;

import fi.kivibot.pallo2d.math.Vector2f;
import fi.kivibot.pallo2d.rendering.Mesh;
import fi.kivibot.pallo2d.rendering.Shader;
import fi.kivibot.pallo2d.rendering.Texture;
import fi.kivibot.pallo2d.rendering.OGLBuffer;
import fi.kivibot.pallo2d.rendering.TileSet;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.opengl.GLContext;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Nicklas Ahlskog
 */
public class SimpleAssetManager {

    private final GLContext glContext;

    public SimpleAssetManager(GLContext glContext) {
        this.glContext = glContext;
    }

    public Texture loadTexture(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(SimpleAssetManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }

        buffer.flip();

        glEnable(GL_TEXTURE_2D);
        int tid = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, tid);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);

        //TODO: UPLOAD DATA
        return new Texture(tid);
    }

    public Shader loadShader(String vertexPath, String fragmentPath) {
        final StringBuilder vc = new StringBuilder(), fc = new StringBuilder();
        try {
            new BufferedReader(new FileReader(vertexPath)).lines().forEach((s) -> {
                vc.append(s).append("\n");
            });
            new BufferedReader(new FileReader(fragmentPath)).lines().forEach((s) -> {
                fc.append(s).append("\n");
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimpleAssetManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        int vid = compileShader(vc.toString(), GL_VERTEX_SHADER);
        int fid = compileShader(fc.toString(), GL_FRAGMENT_SHADER);

        int progID = glCreateProgram();
        glAttachShader(progID, vid);
        glAttachShader(progID, fid);

        glLinkProgram(progID);
        glValidateProgram(progID);

        //TODO: UPLOAD DATA
        return new Shader(progID);
    }

    private int compileShader(String code, int type) {
        int sid = glCreateShader(type);
        glShaderSource(sid, code);
        glCompileShader(sid);
        int err = glGetShaderi(sid, GL_COMPILE_STATUS);
        if (err == GL_FALSE) {
            int ls = glGetShaderi(sid, GL_INFO_LOG_LENGTH);
            String str = glGetShaderInfoLog(sid, ls);
            System.err.println(str);
            return -1;
        }
        return sid;
    }

    public Mesh loadMesh(String path) {
        Map data = (Map) loadYaml(path);
        List pointList = (List) data.get("points");
        List tcList = (List) data.get("texcoords");
        List indexList = (List) data.get("indices");
        float[] points = new float[pointList.size()];
        float[] tcs = new float[tcList.size()];
        int[] indices = new int[indexList.size()];

        for (int i = 0; i < pointList.size(); i++) {
            points[i] = (float) (double) pointList.get(i);
        }
        for (int i = 0; i < tcList.size(); i++) {
            tcs[i] = (float) (double) tcList.get(i);
        }
        for (int i = 0; i < indexList.size(); i++) {
            indices[i] = (int) indexList.get(i);
        }

        OGLBuffer pb = new OGLBuffer(glGenBuffers(), GL_ARRAY_BUFFER, points.length);
        OGLBuffer tb = new OGLBuffer(glGenBuffers(), GL_ARRAY_BUFFER, tcs.length);
        OGLBuffer ib = new OGLBuffer(glGenBuffers(), GL_ELEMENT_ARRAY_BUFFER, indices.length);

        FloatBuffer npb = BufferUtils.createFloatBuffer(points.length);
        FloatBuffer ntb = BufferUtils.createFloatBuffer(tcs.length);
        IntBuffer nib = BufferUtils.createIntBuffer(indices.length);

        npb.put(points);
        npb.flip();
        ntb.put(tcs);
        ntb.flip();
        nib.put(indices);
        nib.flip();

        glBindBuffer(pb.getTarget(), pb.getId());
        glBufferData(pb.getTarget(), npb, GL_DYNAMIC_DRAW);

        glBindBuffer(tb.getTarget(), tb.getId());
        glBufferData(tb.getTarget(), ntb, GL_DYNAMIC_DRAW);

        glBindBuffer(ib.getTarget(), ib.getId());
        glBufferData(ib.getTarget(), nib, GL_DYNAMIC_DRAW);

        ArrayList<OGLBuffer> buffers = new ArrayList<>();
        buffers.add(pb);
        buffers.add(tb);

        int vao = glGenVertexArrays();

        glBindVertexArray(vao);
        glBindBuffer(pb.getTarget(), pb.getId());
        glVertexAttribPointer(0, 2, GL_FLOAT, true, 0, 0);
        glBindBuffer(tb.getTarget(), tb.getId());
        glVertexAttribPointer(1, 2, GL_FLOAT, true, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        return new Mesh(vao, buffers, ib);
    }

    public TileSet loadTileSet(String path) {
        Map data = (Map) loadYaml(path);
        StringBuilder sb = new StringBuilder();
        String[] pts = path.split("/");
        for (int i = 0; i < pts.length - 1; i++) {
            sb.append(pts[i]).append("/");
        }
        sb.append((String) data.get("texture"));
        Texture t = loadTexture(sb.toString());
        List l = (List) data.get("size");
        return new TileSet(t, new Vector2f((int) l.get(0), (int) l.get(1)));
    }

    private Object loadYaml(String path) {
        try {
            return new Yaml().load(new FileInputStream(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimpleAssetManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
