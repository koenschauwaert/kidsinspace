/*===============================================================================
Copyright (c) 2016 PTC Inc. All Rights Reserved.

Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/

package nl.overnightprojects.kids_in_space.ImageTargets;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.vuforia.Device;
import com.vuforia.Matrix44F;
import com.vuforia.State;
import com.vuforia.Tool;
import com.vuforia.Trackable;
import com.vuforia.TrackableResult;
import com.vuforia.Vuforia;

import nl.overnightprojects.kids_in_space.Objects.Balloon;
import nl.overnightprojects.kids_in_space.Objects.Moonbase;
import nl.overnightprojects.kids_in_space.Objects.Planet;
import nl.overnightprojects.kids_in_space.Objects.Textbook;
import nl.overnightprojects.kids_in_space.Utils.AppRenderer;
import nl.overnightprojects.kids_in_space.Utils.AppRendererControl;
import nl.overnightprojects.kids_in_space.Utils.Application3DModel;
import nl.overnightprojects.kids_in_space.Utils.ApplicationSession;
import nl.overnightprojects.kids_in_space.Utils.CubeShaders;
import nl.overnightprojects.kids_in_space.Utils.LoadingDialogHandler;
import nl.overnightprojects.kids_in_space.Utils.Texture;
import nl.overnightprojects.kids_in_space.Utils.Utils;

// The renderer class for the ImageTargets sample. 
public class ImageTargetRenderer implements GLSurfaceView.Renderer, AppRendererControl
{
    private static final String LOGTAG = "ImageTargetRenderer";

    private int markerID;
    
    private ApplicationSession vuforiaAppSession;
    private ImageTargets mActivity;
    private AppRenderer mSampleAppRenderer;

    private Vector<Texture> mTextures;
    
    private int shaderProgramID;
    private int vertexHandle;
    private int textureCoordHandle;
    private int mvpMatrixHandle;
    private int texSampler2DHandle;

    private Moonbase mMoonbase;
    private Balloon mBalloon;
    private Textbook mTextbook;
    private Planet mPlanet;

    private float kBuildingScale = 0.012f;
    private Application3DModel mBuildingsModel;

    private boolean mIsActive = false;
    private boolean mModelIsLoaded = false;
    
    private static final float OBJECT_SCALE_FLOAT = 0.003f;
    
    public ImageTargetRenderer(ImageTargets activity, ApplicationSession session, int id)
    {
        mActivity = activity;
        vuforiaAppSession = session;
        markerID = id;
        // SampleAppRenderer used to encapsulate the use of RenderingPrimitives setting
        // the device mode AR/VR and stereo mode
        mSampleAppRenderer = new AppRenderer(this, mActivity, Device.MODE.MODE_AR, false, 0.01f , 5f);
    }
    
    
    // Called to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl)
    {
        if (!mIsActive)
            return;
        
        // Call our function to render content from SampleAppRenderer class
        mSampleAppRenderer.render();
    }
    

    public void setActive(boolean active)
    {
        mIsActive = active;

        if(mIsActive)
            mSampleAppRenderer.configureVideoBackground();
    }


    // Called when the surface is created or recreated.
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        Log.d(LOGTAG, "GLRenderer.onSurfaceCreated");
        
        // Call Vuforia function to (re)initialize rendering after first use
        // or after OpenGL ES context was lost (e.g. after onPause/onResume):
        vuforiaAppSession.onSurfaceCreated();

        mSampleAppRenderer.onSurfaceCreated();
    }
    
    
    // Called when the surface changed size.
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(LOGTAG, "GLRenderer.onSurfaceChanged");
        
        // Call Vuforia function to handle render surface size changes:
        vuforiaAppSession.onSurfaceChanged(width, height);

        // RenderingPrimitives to be updated when some rendering change is done
        mSampleAppRenderer.onConfigurationChanged(mIsActive);

        initRendering();
    }
    
    
    // Function for initializing the renderer.
    public void initRendering()
    {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, Vuforia.requiresAlpha() ? 0.0f
                : 1.0f);
        
        for (Texture t : mTextures)
        {
            GLES20.glGenTextures(1, t.mTextureID, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, t.mTextureID[0]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA,
                t.mWidth, t.mHeight, 0, GLES20.GL_RGBA,
                GLES20.GL_UNSIGNED_BYTE, t.mData);
        }
        
        shaderProgramID = Utils.createProgramFromShaderSrc(
            CubeShaders.CUBE_MESH_VERTEX_SHADER,
            CubeShaders.CUBE_MESH_FRAGMENT_SHADER);

        vertexHandle = GLES20.glGetAttribLocation(shaderProgramID,
            "vertexPosition");
        textureCoordHandle = GLES20.glGetAttribLocation(shaderProgramID,
            "vertexTexCoord");
        mvpMatrixHandle = GLES20.glGetUniformLocation(shaderProgramID,
            "modelViewProjectionMatrix");
        texSampler2DHandle = GLES20.glGetUniformLocation(shaderProgramID,
            "texSampler2D");

        // SWITCH CASE ACCORDING TO MARKER ID IN DATABASE. COMMENTED IS NOT USED: IMAGE SHOWN INSTEAD IN IMAGEACTIVITY
        if(!mModelIsLoaded) {
            Log.d("!mModelIsLoaded", "" + markerID);

            switch (markerID){
                case 0:
                    //
                    break;
                case 1:
                    mMoonbase = new Moonbase();
                    break;
                case 2:
                    //
                    break;
                case 3:
                    mBalloon = new Balloon();
                    break;
                case 4:
                    mTextbook = new Textbook();
                    break;
                case 5:
                    mPlanet = new Planet();
                    break;
                case 6:
                    //
                    break;
                case 7:
                    //
                    break;
            }

            try {
                mBuildingsModel = new Application3DModel();
                mBuildingsModel.loadModel(mActivity.getResources().getAssets(),
                        "ImageTargets/Buildings.txt");
                mModelIsLoaded = true;
            } catch (IOException e) {
                Log.e(LOGTAG, "Unable to load buildings");
            }

            // Hide the Loading Dialog
            mActivity.loadingDialogHandler
                    .sendEmptyMessage(LoadingDialogHandler.HIDE_LOADING_DIALOG);
        }

    }

    public void updateConfiguration()
    {
        mSampleAppRenderer.onConfigurationChanged(mIsActive);
    }

    // The render function called from SampleAppRendering by using RenderingPrimitives views.
    // The state is owned by SampleAppRenderer which is controlling it's lifecycle.
    // State should not be cached outside this method.
    public void renderFrame(State state, float[] projectionMatrix)
    {
        // Renders video background replacing Renderer.DrawVideoBackground()
        mSampleAppRenderer.renderVideoBackground();

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        // handle face culling, we need to detect if we are using reflection
        // to determine the direction of the culling
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_BACK);

        // Did we find any trackables this frame?
        for (int tIdx = 0; tIdx < state.getNumTrackableResults(); tIdx++) {
            TrackableResult result = state.getTrackableResult(tIdx);
            Trackable trackable = result.getTrackable();
            printUserData(trackable);
            Matrix44F modelViewMatrix_Vuforia = Tool
                    .convertPose2GLMatrix(result.getPose());
            float[] modelViewMatrix = modelViewMatrix_Vuforia.getData();

            // NO NEED TO TOUCH THIS - I WROTE IT MATTERS IN THE PROJECT REPORT, BUT IT DOESN'T
            int textureIndex = trackable.getName().equalsIgnoreCase("marker001") ? 0
                    : 1;

            // NO NEED TO USE THIS. MAYBE YOU CAN DELETE IT?
            // deal with the modelview and projection matrices
            float[] modelViewProjection = new float[16];

            if (!mActivity.isExtendedTrackingActive()) {
                Matrix.translateM(modelViewMatrix, 0, 0.0f, 0.0f,
                        OBJECT_SCALE_FLOAT);
                Matrix.scaleM(modelViewMatrix, 0, OBJECT_SCALE_FLOAT,
                        OBJECT_SCALE_FLOAT, OBJECT_SCALE_FLOAT);
            } else {
                Matrix.rotateM(modelViewMatrix, 0, 90.0f, 1.0f, 0, 0);
                Matrix.scaleM(modelViewMatrix, 0, kBuildingScale,
                        kBuildingScale, kBuildingScale);
            }

            // USE THIS (Z VALUE) TO FLOAT YOUR OBJECT
            // USE X AND Y TO POSITION ON MARKER
            Matrix.translateM(modelViewMatrix, 0, 40.0f, -30.0f,
                    50.0f);

            // USE THIS TO ROTATE. HAVE TO SEARCH ON HOW TO ROTATE WITH ANGLE, X, Y, Z: IT'S NOT JUST DEGREES
            // ROTATING ALSO MESSES UP THE POSITION ABOVE
            Matrix.rotateM(modelViewMatrix, 0, 90.0f, 90.0f, 70.0f, 90.0f);

            // IF MARKERID == MOONBASE || BALLOON: BECAUSE NO NEED TO SHOW ROTATED THAT MUCH
            // STILL NEEDS ROTATION, BECAUSE EXPORT FROM BLENDER TO .H VUFORIA IS ROTATED IN WRONG DIRECTION
            if(markerID == 1 || markerID == 3) {
                Matrix.translateM(modelViewMatrix, 0, 0.0f, 40.0f,
                        -20.0f);
                Matrix.rotateM(modelViewMatrix, 0, 30.0f, 10.0f, 10.0f, 10.0f);
            }
            Matrix.multiplyMM(modelViewProjection, 0, projectionMatrix, 0, modelViewMatrix, 0);

            // activate the shader program and bind the vertex/normal/tex coords
            GLES20.glUseProgram(shaderProgramID);

            // ONLY THE PARTS WHERE AN AR OBJECTS IS BEEN SHOWN ACCORDING TO THE DATABASE MARKER ID. REST IS COMMENTED OUT: NOT GONNA BE USED
            if (!mActivity.isExtendedTrackingActive()) {
                switch(markerID) {
                    case 0:
                        //
                        break;
                    case 1:
                        GLES20.glVertexAttribPointer(vertexHandle, 3, GLES20.GL_FLOAT,
                                false, 0, mMoonbase.getVertices());
                        GLES20.glVertexAttribPointer(textureCoordHandle, 2,
                                GLES20.GL_FLOAT, false, 0, mMoonbase.getTexCoords());
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        GLES20.glVertexAttribPointer(vertexHandle, 3, GLES20.GL_FLOAT,
                                false, 0, mBalloon.getVertices());
                        GLES20.glVertexAttribPointer(textureCoordHandle, 2,
                                GLES20.GL_FLOAT, false, 0, mBalloon.getTexCoords());
                        break;
                    case 4:
                        GLES20.glVertexAttribPointer(vertexHandle, 3, GLES20.GL_FLOAT,
                                false, 0, mTextbook.getVertices());
                        GLES20.glVertexAttribPointer(textureCoordHandle, 2,
                                GLES20.GL_FLOAT, false, 0, mTextbook.getTexCoords());
                        break;
                    case 5:
                        GLES20.glVertexAttribPointer(vertexHandle, 3, GLES20.GL_FLOAT,
                                false, 0, mPlanet.getVertices());
                        GLES20.glVertexAttribPointer(textureCoordHandle, 2,
                                GLES20.GL_FLOAT, false, 0, mPlanet.getTexCoords());
                        break;
                    case 6:
                        //
                        break;
                    case 7:
                        //
                        break;
                }

                GLES20.glEnableVertexAttribArray(vertexHandle);
                GLES20.glEnableVertexAttribArray(textureCoordHandle);

                // activate texture 0, bind it, and pass to shader
                GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                        mTextures.get(textureIndex).mTextureID[0]);
                GLES20.glUniform1i(texSampler2DHandle, 0);

                // pass the model view matrix to the shader
                GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false,
                        modelViewProjection, 0);

                // SAME HERE FOR SWITCH CASE ACCORDING TO MARKERID
                // finally draw the object
                switch(markerID){
                    case 0:
                        //
                        break;
                    case 1:
                        GLES20.glDrawElements(GLES20.GL_TRIANGLES,
                                mMoonbase.getNumObjectIndex(), GLES20.GL_UNSIGNED_SHORT,
                                mMoonbase.getIndices());
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        GLES20.glDrawElements(GLES20.GL_TRIANGLES,
                                mBalloon.getNumObjectIndex(), GLES20.GL_UNSIGNED_SHORT,
                                mBalloon.getIndices());
                        break;
                    case 4:
                        GLES20.glDrawElements(GLES20.GL_TRIANGLES,
                                mTextbook.getNumObjectIndex(), GLES20.GL_UNSIGNED_SHORT,
                                mTextbook.getIndices());
                        break;
                    case 5:
                        GLES20.glDrawElements(GLES20.GL_TRIANGLES,
                                mPlanet.getNumObjectIndex(), GLES20.GL_UNSIGNED_SHORT,
                                mPlanet.getIndices());
                        break;
                    case 6:
                        //
                        break;
                    case 7:
                        //
                        break;
                }

                // disable the enabled arrays
                GLES20.glDisableVertexAttribArray(vertexHandle);
                GLES20.glDisableVertexAttribArray(textureCoordHandle);


            } else {
                GLES20.glDisable(GLES20.GL_CULL_FACE);
                GLES20.glVertexAttribPointer(vertexHandle, 3, GLES20.GL_FLOAT,
                        false, 0, mBuildingsModel.getVertices());
                GLES20.glVertexAttribPointer(textureCoordHandle, 2,
                        GLES20.GL_FLOAT, false, 0, mBuildingsModel.getTexCoords());

                GLES20.glEnableVertexAttribArray(vertexHandle);
                GLES20.glEnableVertexAttribArray(textureCoordHandle);

                GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                        mTextures.get(3).mTextureID[0]);
                GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false,
                        modelViewProjection, 0);
                GLES20.glUniform1i(texSampler2DHandle, 0);
                GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0,
                        mBuildingsModel.getNumObjectVertex());

                Utils.checkGLError("Renderer DrawBuildings");
            }

            Utils.checkGLError("Render Frame");

        }

        GLES20.glDisable(GLES20.GL_DEPTH_TEST);

    }

    private void printUserData(Trackable trackable)
    {
        String userData = (String) trackable.getUserData();
        Log.d(LOGTAG, "UserData:Retreived User Data	\"" + userData + "\"");
    }
    
    
    public void setTextures(Vector<Texture> textures)
    {
        mTextures = textures;
        
    }
    
}
