package fi.kivibot.pallo2d.math;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

/**
 * 00 01 02 10 11 12 20 21 22
 *
 * @author Nicklas Ahlskog
 */
public class Matrix3f {

    private float[][] matrix = new float[3][3];

    public Matrix3f(float[][] f) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = f[i][j];
            }
        }
    }

    public Matrix3f mul(Matrix3f m) {
        float[][] f = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    f[i][j] += matrix[i][k] * m.get(k, j);
                }
            }
        }
        return new Matrix3f(f);
    }

    public Vector3f mul(Vector3f v) {
        return new Vector3f(
                matrix[0][0] * v.getX() + matrix[0][1] * v.getY() + matrix[0][2] * v.getZ(),
                matrix[1][0] * v.getX() + matrix[1][1] * v.getY() + matrix[1][2] * v.getZ(),
                matrix[2][0] * v.getX() + matrix[2][1] * v.getY() + matrix[2][2] * v.getZ());
    }

    public void set(int i, int j, float f) {
        matrix[i][j] = f;
    }

    public float get(int i, int j) {
        return matrix[i][j];
    }

    public void toBuffer(FloatBuffer fb) {
        fb.clear();
        fb.put(matrix[0]);
        fb.put(matrix[1]);
        fb.put(matrix[2]);
        fb.flip();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix3f{\n");
        for (float[] fa : matrix) {
            sb.append("\t");
            for (float f : fa) {
                sb.append(f + ", ");
            }
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
