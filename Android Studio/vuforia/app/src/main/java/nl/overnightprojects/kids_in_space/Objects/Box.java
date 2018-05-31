package nl.overnightprojects.kids_in_space.Objects;

import java.nio.Buffer;

import nl.overnightprojects.kids_in_space.Utils.MeshObject;

public class Box extends MeshObject {
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    public Box() {
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }

    private void setVerts() {
        double[] BOX_VERTS = {
                63.999968f, 63.999968f, -63.999968f, 63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, 63.999968f, -63.999968f, 63.999968f, 63.999904f, 63.999968f, -63.999968f, 63.999968f, 63.999968f, -63.999968f, -63.999968f, 63.999968f, 63.999904f, -64.000032f, 63.999968f, 63.999968f, 63.999968f, -63.999968f, 63.999968f, 63.999904f, 63.999968f, 63.999904f, -64.000032f, 63.999968f, 63.999968f, -63.999968f, -63.999968f, 63.999968f, -63.999968f, -63.999968f, 63.999904f, -64.000032f, 63.999968f, -63.999968f, -63.999968f, 63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, -63.999968f, 63.999968f, -63.999968f, 63.999968f, 63.999968f, -63.999968f, 63.999968f, -63.999968f, 63.999968f, 63.999904f, 63.999968f, 63.999968f, 63.999968f, -63.999968f, -63.999968f, 63.999968f, -63.999968f, -63.999968f, 63.999968f, 63.999968f,

        };
        mVertBuff = fillBuffer(BOX_VERTS);
        verticesNumber = BOX_VERTS.length / 3;
    }

    private void setTexCoords() {
        double[] BOX_TEX_COORDS = {
                0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f,

        };
        mTexCoordBuff = fillBuffer(BOX_TEX_COORDS);
    }

    private void setNorms() {
        double[] BOX_NORMS = {
                0.000000f, 0.000000f, -1.000000f, 0.000000f, 0.000000f, -1.000000f, 0.000000f, 0.000000f, -1.000000f, 0.000000f, 0.000000f, -1.000000f, 0.000000f, -0.000000f, 1.000000f, 0.000000f, -0.000000f, 1.000000f, 0.000000f, -0.000000f, 1.000000f, 0.000000f, -0.000000f, 1.000000f, 1.000000f, -0.000000f, 0.000000f, 1.000000f, -0.000000f, 0.000000f, 1.000000f, -0.000000f, 0.000000f, 1.000000f, -0.000000f, 0.000000f, -0.000000f, -1.000000f, -0.000000f, -0.000000f, -1.000000f, -0.000000f, -0.000000f, -1.000000f, -0.000000f, -0.000000f, -1.000000f, -0.000000f, -1.000000f, 0.000000f, -0.000000f, -1.000000f, 0.000000f, -0.000000f, -1.000000f, 0.000000f, -0.000000f, -1.000000f, 0.000000f, -0.000000f, 0.000000f, 1.000000f, 0.000000f, 0.000000f, 1.000000f, 0.000000f, 0.000000f, 1.000000f, 0.000000f, 0.000000f, 1.000000f, 0.000000f,

        };
        mNormBuff = fillBuffer(BOX_NORMS);
    }

    private void setIndices() {
        short[] BOX_INDICES = {
                0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15, 16, 17, 18, 16, 18, 19, 20, 21, 22, 20, 22, 23,

        };
        mIndBuff = fillBuffer(BOX_INDICES);
        indicesNumber = BOX_INDICES.length;
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
