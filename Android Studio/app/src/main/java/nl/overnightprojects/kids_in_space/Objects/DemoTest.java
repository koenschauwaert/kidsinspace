package nl.overnightprojects.kids_in_space.Objects;

import java.nio.Buffer;

import nl.overnightprojects.kids_in_space.Utils.MeshObject;

public class DemoTest extends MeshObject {
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    public DemoTest() {
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }

    private void setVerts() {
        double[] DEMOTEST_VERTS = {



        };
        mVertBuff = fillBuffer(DEMOTEST_VERTS);
        verticesNumber = DEMOTEST_VERTS.length / 3;
    }

    private void setTexCoords() {
        double[] DEMOTEST_TEX_COORDS = {



        };
        mTexCoordBuff = fillBuffer(DEMOTEST_TEX_COORDS);
    }

    private void setNorms() {
        double[] DEMOTEST_NORMS = {



        };
        mNormBuff = fillBuffer(DEMOTEST_NORMS);
    }

    private void setIndices() {
        short[] DEMOTEST_INDICES = {



        };
        mIndBuff = fillBuffer(DEMOTEST_INDICES);
        indicesNumber = DEMOTEST_INDICES.length;
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
