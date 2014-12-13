package fi.kivibot.pallo2d.math;

/**
 *
 * @author Nicklas Ahlskog
 */
public class Vector2f {

    private float x, y;

    public Vector2f() {
        x = 0;
        y = 0;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v) {
        this(v.x, v.y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2f v) {
        set(v.x, v.y);
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void add(Vector2f v) {
        add(v.x, v.y);
    }

    public void sub(float x, float y) {
        this.x -= x;
        this.y -= y;
    }

    public void sub(Vector2f v) {
        sub(v.x, v.y);
    }

    public void mul(float f) {
        x *= f;
        y *= f;
    }

    public void div(float f) {
        x /= f;
        y /= f;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(float bx, float by) {
        return x * bx + y * by;
    }

    public float dot(Vector2f v) {
        return dot(v.x, v.y);
    }

    public void normalize() {
        this.div(length());
    }

    public Vector2f copy() {
        return new Vector2f(x, y);
    }

    @Override
    public String toString() {
        return "Vector2f{" + "x=" + x + ", y=" + y + '}';
    }

}
