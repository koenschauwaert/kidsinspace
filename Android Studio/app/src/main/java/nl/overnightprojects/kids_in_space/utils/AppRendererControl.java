package nl.overnightprojects.kids_in_space.utils;

/**
 * Created by koen_ on 2-3-2018.
 */

import com.vuforia.State;

public interface AppRendererControl {

    // This method has to be implemented by the Renderer class which handles the content rendering
    // of the sample, this one is called from SampleAppRendering class for each view inside a loop
    void renderFrame(State state, float[] projectionMatrix);

}
