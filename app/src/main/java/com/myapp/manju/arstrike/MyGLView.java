package com.myapp.manju.arstrike;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.myapp.manju.arstrike.EngineActivity;
import com.threed.jpct.SimpleVector;

import java.io.InputStream;

/**
 * Created by manju on 12/3/2016.
 */

public class MyGLView extends GLSurfaceView {

    public EngineActivity mRenderer;

    public void CreateRenderer()
    {
        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new EngineActivity();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }

    public MyGLView(Context context) {
        super(context);
    }

    public MyGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void AddModeltoScene(InputStream is, SimpleVector position, float scale)
    {
        mRenderer.AddModeltoScene(is,position,scale);
    }
}
