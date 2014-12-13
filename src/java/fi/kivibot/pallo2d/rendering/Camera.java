package fi.kivibot.pallo2d.rendering;

import fi.kivibot.pallo2d.math.Matrix3f;
import fi.kivibot.pallo2d.math.Vector2f;
import fi.kivibot.pallo2d.math.Vector3f;

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

    public Vector2f screenToWorld(Vector2f v) {
        v.sub(0.5f, 0.5f);
        Vector3f o = getMatrix().mul(new Matrix3f(new float[][]{
            new float[]{w, 0, 0},
            new float[]{0, h, 0},
            new float[]{0, 0, 1}})).mul(new Vector3f(v.getX(), -v.getY(), 1));
        v = new Vector2f(o.getX(), o.getY());
        v.mul(2);
        return v;
    }

}
