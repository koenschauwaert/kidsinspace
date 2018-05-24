package nl.overnightprojects.kids_in_space.Objects;

import java.nio.Buffer;
import nl.overnightprojects.kids_in_space.Utils.MeshObject;

public class EmptyObjectClassToCopy extends MeshObject {
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    public EmptyObjectClassToCopy() {
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }

    private void setVerts() {
        double[] OBJECT_VERTS = {

        };
        mVertBuff = fillBuffer(OBJECT_VERTS);
        verticesNumber = OBJECT_VERTS.length / 3;
    }

    private void setTexCoords() {
        double[] OBJECT_TEX_COORDS = {

        };
        mTexCoordBuff = fillBuffer(OBJECT_TEX_COORDS);
    }

    private void setNorms() {
        double[] OBJECT_NORMS = {

        };
        mNormBuff = fillBuffer(OBJECT_NORMS);
    }

    private void setIndices() {
        short[] OBJECT_INDICES = {

        };
        mIndBuff = fillBuffer(OBJECT_INDICES);
        indicesNumber = OBJECT_INDICES.length;
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
