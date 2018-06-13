package nl.overnightprojects.kids_in_space.Objects;

import java.nio.Buffer;

import nl.overnightprojects.kids_in_space.Utils.MeshObject;

public class Textbook extends MeshObject {
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    public Textbook() {
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }

    private void setVerts() {
        double[] OBJECT_VERTS = {
                -0.194024f, 129.017259f, -39.585463f, -0.194024f, 129.017259f, -44.862837f, -10.044120f, 129.017259f, -44.862837f, -10.044120f, 129.017259f, -39.585463f, -0.194024f, 129.017259f, -44.862837f, -0.194024f, 1.017259f, -44.862837f, -10.044120f, 1.017259f, -44.862837f, -10.044120f, 129.017259f, -44.862837f, -0.194024f, 1.017259f, -34.249060f, -0.194024f, 1.017259f, 42.302749f, -10.044120f, 1.017259f, 42.302749f, -10.044120f, 1.017259f, -34.249060f, -0.194024f, 1.017259f, 42.302749f, -0.194024f, 129.017259f, 42.302749f, -10.044120f, 129.017259f, 42.302749f, -10.044120f, 1.017259f, 42.302749f, -0.194024f, 129.017259f, -34.249060f, -0.194024f, 129.017259f, 42.302749f, -0.194024f, 1.017259f, 42.302749f, -0.194024f, 1.017259f, -34.249060f, -10.044120f, 1.017259f, -34.249060f, -10.044120f, 1.017259f, 42.302749f, -10.044120f, 129.017259f, 42.302749f, -10.044120f, 129.017259f, -34.249060f, -8.427321f, 1.017259f, -38.218770f, -8.427321f, 1.017259f, -36.002328f, -8.427321f, 129.017259f, -36.002328f, -8.427321f, 129.017259f, -38.218770f, -1.810822f, 129.017259f, -38.218770f, -1.810822f, 129.017259f, -36.002328f, -1.810822f, 1.017259f, -36.002328f, -1.810822f, 1.017259f, -38.218770f, -1.810822f, 1.017259f, -38.218770f, -1.810822f, 1.017259f, -36.002328f, -8.427321f, 1.017259f, -36.002328f, -8.427321f, 1.017259f, -38.218770f, -0.194024f, 129.017259f, -34.249060f, -1.810822f, 129.017259f, -36.002328f, -8.427321f, 129.017259f, -36.002328f, -10.044120f, 129.017259f, -34.249060f, -0.194024f, 1.017259f, -39.585463f, -10.044120f, 1.017259f, -39.585463f, -0.194024f, 129.017259f, -39.585463f, -1.810822f, 129.017259f, -38.218770f, -1.810822f, 1.017259f, -38.218770f, -0.194024f, 1.017259f, -39.585463f, -10.044120f, 1.017259f, -39.585463f, -8.427321f, 1.017259f, -38.218770f, -8.427321f, 129.017259f, -38.218770f, -10.044120f, 129.017259f, -39.585463f, -1.810822f, 129.017259f, -38.218770f, -8.427321f, 129.017259f, -38.218770f, -0.194024f, 129.017259f, 42.302749f, -10.044120f, 129.017259f, 42.302749f, -8.427321f, 1.017259f, -36.002328f, -10.044120f, 1.017259f, -34.249060f, -10.044120f, 129.017259f, -34.249060f, -8.427321f, 129.017259f, -36.002328f, -1.810822f, 129.017259f, -36.002328f, -0.194024f, 129.017259f, -34.249060f, -0.194024f, 1.017259f, -34.249060f, -1.810822f, 1.017259f, -36.002328f, -10.044120f, 1.017259f, -44.862837f, -10.044120f, 1.017259f, -39.585463f, -10.044120f, 129.017259f, -39.585463f, -10.044120f, 129.017259f, -44.862837f, -0.194024f, 129.017259f, -44.862837f, -0.194024f, 129.017259f, -39.585463f, -0.194024f, 1.017259f, -39.585463f, -0.194024f, 1.017259f, -44.862837f, -0.194024f, 1.017259f, -44.862837f, -10.044120f, 1.017259f, -44.862837f,
        };
        mVertBuff = fillBuffer(OBJECT_VERTS);
        verticesNumber = OBJECT_VERTS.length / 3;
    }

    private void setTexCoords() {
        double[] OBJECT_TEX_COORDS = {
                0.495636f, 0.927488f, 0.495636f, 0.947746f, 0.690075f, 0.947746f, 0.690075f, 0.927488f, 0.247409f, 0.590217f, 0.247409f, 0.202893f, 0.094134f, 0.202893f, 0.094135f, 0.590217f, 0.908302f, 0.908267f, 0.908302f, 0.614413f, 0.713864f, 0.614413f, 0.713864f, 0.908267f, 0.278916f, 0.201516f, 0.278916f, 0.588840f, 0.432191f, 0.588840f, 0.432191f, 0.201516f, 0.716640f, 0.592756f, 0.926368f, 0.592756f, 0.926368f, 0.205432f, 0.716640f, 0.205432f, 0.675075f, 0.205432f, 0.465347f, 0.205432f, 0.465347f, 0.592756f, 0.675075f, 0.592756f, 1.368109f, 0.823341f, 1.365660f, 0.823341f, 1.365660f, 0.979513f, 1.368109f, 0.979513f, 0.461523f, 0.687070f, 0.461523f, 0.659038f, 0.075843f, 0.659038f, 0.075843f, 0.687071f, 0.876387f, 0.923505f, 0.876387f, 0.914997f, 0.745779f, 0.914997f, 0.745779f, 0.923505f, 0.495636f, 0.907003f, 0.527551f, 0.913734f, 0.658159f, 0.913734f, 0.690075f, 0.907003f, 0.908302f, 0.928751f, 0.713864f, 0.928752f, 0.081769f, 0.881459f, 0.081769f, 0.904748f, 0.473490f, 0.904748f, 0.473490f, 0.881459f, 0.460284f, 0.794289f, 0.460284f, 0.771000f, 0.068564f, 0.771000f, 0.068564f, 0.794289f, 0.527551f, 0.922242f, 0.658159f, 0.922242f, 0.495636f, 0.613150f, 0.690075f, 0.613150f, 0.093955f, 0.044916f, 0.093955f, 0.092197f, 0.821854f, 0.092197f, 0.821854f, 0.044916f, 0.093724f, 0.122421f, 0.093724f, 0.169702f, 0.821622f, 0.169702f, 0.821622f, 0.122420f, 0.067012f, 0.719974f, 0.067012f, 0.740732f, 0.464693f, 0.740732f, 0.464693f, 0.719974f, 0.473490f, 0.855712f, 0.473490f, 0.842328f, 0.081769f, 0.842328f, 0.081769f, 0.855712f, 0.908302f, 0.949009f, 0.713864f, 0.949009f,
        };
        mTexCoordBuff = fillBuffer(OBJECT_TEX_COORDS);
    }

    private void setNorms() {
        double[] OBJECT_NORMS = {
                0.709159f, 0.623493f, 0.329112f, 0.577349f, 0.577349f, -0.577349f, -0.577349f, 0.577349f, -0.577349f, -0.709159f, 0.623493f, 0.329112f, 0.577349f, 0.577349f, -0.577349f, 0.577349f, -0.577349f, -0.577349f, -0.577349f, -0.577349f, -0.577349f, -0.577349f, 0.577349f, -0.577349f, 0.720573f, -0.633625f, -0.281503f, 0.577349f, -0.577349f, 0.577349f, -0.577349f, -0.577349f, 0.577349f, -0.720573f, -0.633625f, -0.281503f, 0.577349f, -0.577349f, 0.577349f, 0.577349f, 0.577349f, 0.577349f, -0.577349f, 0.577349f, 0.577349f, -0.577349f, -0.577349f, 0.577349f, 0.720573f, 0.633625f, -0.281533f, 0.577349f, 0.577349f, 0.577349f, 0.577349f, -0.577349f, 0.577349f, 0.720573f, -0.633625f, -0.281503f, -0.720573f, -0.633625f, -0.281503f, -0.577349f, -0.577349f, 0.577349f, -0.577349f, 0.577349f, 0.577349f, -0.720573f, 0.633625f, -0.281533f, -0.525376f, -0.815180f, 0.243812f, -0.560228f, -0.798883f, -0.218879f, -0.560228f, 0.798883f, -0.218879f, -0.525376f, 0.815149f, 0.243812f, 0.525376f, 0.815149f, 0.243812f, 0.560228f, 0.798883f, -0.218879f, 0.560228f, -0.798883f, -0.218879f, 0.525376f, -0.815180f, 0.243812f, 0.525376f, -0.815180f, 0.243812f, 0.560228f, -0.798883f, -0.218879f, -0.560228f, -0.798883f, -0.218879f, -0.525376f, -0.815180f, 0.243812f, 0.720573f, 0.633625f, -0.281533f, 0.560228f, 0.798883f, -0.218879f, -0.560228f, 0.798883f, -0.218879f, -0.720573f, 0.633625f, -0.281533f, 0.709159f, -0.623493f, 0.329112f, -0.709159f, -0.623493f, 0.329112f, 0.709159f, 0.623493f, 0.329112f, 0.525376f, 0.815149f, 0.243812f, 0.525376f, -0.815180f, 0.243812f, 0.709159f, -0.623493f, 0.329112f, -0.709159f, -0.623493f, 0.329112f, -0.525376f, -0.815180f, 0.243812f, -0.525376f, 0.815149f, 0.243812f, -0.709159f, 0.623493f, 0.329112f, 0.525376f, 0.815149f, 0.243812f, -0.525376f, 0.815149f, 0.243812f, 0.577349f, 0.577349f, 0.577349f, -0.577349f, 0.577349f, 0.577349f, -0.560228f, -0.798883f, -0.218879f, -0.720573f, -0.633625f, -0.281503f, -0.720573f, 0.633625f, -0.281533f, -0.560228f, 0.798883f, -0.218879f, 0.560228f, 0.798883f, -0.218879f, 0.720573f, 0.633625f, -0.281533f, 0.720573f, -0.633625f, -0.281503f, 0.560228f, -0.798883f, -0.218879f, -0.577349f, -0.577349f, -0.577349f, -0.709159f, -0.623493f, 0.329112f, -0.709159f, 0.623493f, 0.329112f, -0.577349f, 0.577349f, -0.577349f, 0.577349f, 0.577349f, -0.577349f, 0.709159f, 0.623493f, 0.329112f, 0.709159f, -0.623493f, 0.329112f, 0.577349f, -0.577349f, -0.577349f, 0.577349f, -0.577349f, -0.577349f, -0.577349f, -0.577349f, -0.577349f,
        };
        mNormBuff = fillBuffer(OBJECT_NORMS);
    }

    private void setIndices() {
        short[] OBJECT_INDICES = {
                0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15, 16, 17, 18, 16, 18, 19, 20, 21, 22, 20, 22, 23, 24, 25, 26, 24, 26, 27, 28, 29, 30, 28, 30, 31, 32, 33, 34, 32, 34, 35, 36, 37, 38, 36, 38, 39, 40, 32, 35, 40, 35, 41, 42, 43, 44, 42, 44, 45, 46, 47, 48, 46, 48, 49, 37, 50, 51, 37, 51, 38, 52, 36, 39, 52, 39, 53, 54, 55, 56, 54, 56, 57, 58, 59, 60, 58, 60, 61, 33, 8, 11, 33, 11, 34, 62, 63, 64, 62, 64, 65, 66, 67, 68, 66, 68, 69, 70, 40, 41, 70, 41, 71, 50, 0, 3, 50, 3, 51,
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