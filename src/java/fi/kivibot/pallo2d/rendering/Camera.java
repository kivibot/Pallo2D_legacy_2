package fi.kivibot.pallo2d.rendering;

import fi.kivibot.pallo2d.math.Matrix3f;

/**
 *
 * @author Nicklas
 */
public class Camera extends Transformable {

    private final float w, h;

    public Camera(float w, float h) {
        this.w = w;
        this.h = h;
    }

    public Matrix3f getProjectionMatrix() {
        return new Matrix3f(new float[][]{
            new float[]{1f / w, 0, 0},
            new float[]{0, 1f / h, 0},
            new float[]{0, 0, 1}});
    }

}
