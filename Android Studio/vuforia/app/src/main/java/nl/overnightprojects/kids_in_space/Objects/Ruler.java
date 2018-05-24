package nl.overnightprojects.kids_in_space.Objects;

import java.nio.Buffer;

import nl.overnightprojects.kids_in_space.Utils.MeshObject;

public class Ruler extends MeshObject {
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    public Ruler() {
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }

    private void setVerts() {
        double[] RULER_VERTS = {
// f 1//1 2//1 3//1 4//1
                0.5, 0.0175156564017084, -0.0946291338553958,
                0.5, 0.0175156564017084, 0.0946291338553958,
                0.5, -0.0175156564017084, 0.0946291338553958,
                // f 1//1 2//1 3//1 4//1
                0.5, 0.0175156564017084, -0.0946291338553958,
                0.5, -0.0175156564017084, -0.0946291338553958,
                0.5, -0.0175156564017084, 0.0946291338553958,
                // f 2//2 5//2 6//2 3//2
                0.5, 0.0175156564017084, 0.0946291338553958,
                -0.5, 0.0175156564017084, 0.0946291338553958,
                -0.5, -0.0175156564017084, 0.0946291338553958,
                // f 2//2 5//2 6//2 3//2
                0.5, 0.0175156564017084, 0.0946291338553958,
                0.5, -0.0175156564017084, 0.0946291338553958,
                -0.5, -0.0175156564017084, 0.0946291338553958,
                // f 5//3 7//3 8//3 6//3
                -0.5, 0.0175156564017084, 0.0946291338553958,
                -0.5, 0.0175156564017084, -0.0946291338553958,
                -0.5, -0.0175156564017084, -0.0946291338553958,
                // f 5//3 7//3 8//3 6//3
                -0.5, 0.0175156564017084, 0.0946291338553958,
                -0.5, -0.0175156564017084, 0.0946291338553958,
                -0.5, -0.0175156564017084, -0.0946291338553958,
                // f 7//4 1//4 4//4 8//4
                -0.5, 0.0175156564017084, -0.0946291338553958,
                0.5, 0.0175156564017084, -0.0946291338553958,
                0.5, -0.0175156564017084, -0.0946291338553958,
                // f 7//4 1//4 4//4 8//4
                -0.5, 0.0175156564017084, -0.0946291338553958,
                -0.5, -0.0175156564017084, -0.0946291338553958,
                0.5, -0.0175156564017084, -0.0946291338553958,
                // f 2//5 1//5 7//5 5//5
                0.5, 0.0175156564017084, 0.0946291338553958,
                0.5, 0.0175156564017084, -0.0946291338553958,
                -0.5, 0.0175156564017084, -0.0946291338553958,
                // f 2//5 1//5 7//5 5//5
                0.5, 0.0175156564017084, 0.0946291338553958,
                -0.5, 0.0175156564017084, 0.0946291338553958,
                -0.5, 0.0175156564017084, -0.0946291338553958,
                // f 6//6 8//6 4//6 3//6
                -0.5, -0.0175156564017084, 0.0946291338553958,
                -0.5, -0.0175156564017084, -0.0946291338553958,
                0.5, -0.0175156564017084, -0.0946291338553958,
                // f 6//6 8//6 4//6 3//6
                -0.5, -0.0175156564017084, 0.0946291338553958,
                0.5, -0.0175156564017084, 0.0946291338553958,
                0.5, -0.0175156564017084, -0.0946291338553958,
                         };
        mVertBuff = fillBuffer(RULER_VERTS);
        verticesNumber = RULER_VERTS.length / 3;
    }

    private void setTexCoords() {
        double[] RULER_TEX_COORDS = {

        };
        mTexCoordBuff = fillBuffer(RULER_TEX_COORDS);
    }

    private void setNorms() {
        double[] RULER_NORMS = {
// f 1//1 2//1 3//1 4//1
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,
                // f 1//1 2//1 3//1 4//1
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,
                // f 2//2 5//2 6//2 3//2
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                // f 2//2 5//2 6//2 3//2
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                // f 5//3 7//3 8//3 6//3
                -1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,
                // f 5//3 7//3 8//3 6//3
                -1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,
                // f 7//4 1//4 4//4 8//4
                0, 0, -1,
                0, 0, -1,
                0, 0, -1,
                // f 7//4 1//4 4//4 8//4
                0, 0, -1,
                0, 0, -1,
                0, 0, -1,
                // f 2//5 1//5 7//5 5//5
                0, 1, 0,
                0, 1, 0,
                0, 1, 0,
                // f 2//5 1//5 7//5 5//5
                0, 1, 0,
                0, 1, 0,
                0, 1, 0,
                // f 6//6 8//6 4//6 3//6
                0, -1, 0,
                0, -1, 0,
                0, -1, 0,
                // f 6//6 8//6 4//6 3//6
                0, -1, 0,
                0, -1, 0,
                0, -1, 0,
        };
        mNormBuff = fillBuffer(RULER_NORMS);
    }

    private void setIndices() {
        short[] RULER_INDICES = {

        };
        mIndBuff = fillBuffer(RULER_INDICES);
        indicesNumber = RULER_INDICES.length;
    }

    public int getNumObjectIndex() {
        return indicesNumber;
    }

    @Override
    public int getNumObjectVertex() {
        return verticesNumber;
    }

    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType) {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                break;
            case BUFFER_TYPE_INDICES:
                result = mIndBuff;
            default:
                break;
        }
        return result;
    }
}
