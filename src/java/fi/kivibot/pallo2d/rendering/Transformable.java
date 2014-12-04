package fi.kivibot.pallo2d.rendering;

import fi.kivibot.pallo2d.math.Matrix3f;
import fi.kivibot.pallo2d.math.Vector2f;

/**
 *
 * @author Nicklas
 */
public class Transformable {

    private final Matrix3f m = new Matrix3f(new float[][]{
        new float[]{1, 0, 0},
        new float[]{0, 1, 0},
        new float[]{0, 0, 1}});

    private final Vector2f pos = new Vector2f();

    public void setPosition(float x, float y) {
        pos.set(x, y);
        m.set(0, 2, x);
        m.set(1, 2, y);
    }

    public void setPosition(Vector2f v) {
        setPosition(v.getX(), v.getY());
    }

    public void translate(float x, float y) {
        pos.add(x, y);
        m.set(0, 2, pos.getX());
        m.set(1, 2, pos.getY());
    }

    public void translate(Vector2f v) {
        translate(v.getX(), v.getY());
    }

    public Matrix3f getMatrix() {
        return m;
    }

}
