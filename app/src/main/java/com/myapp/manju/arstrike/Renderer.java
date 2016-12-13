package com.myapp.manju.arstrike;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
/**
 * Created by manju on 10/27/2016.
 */
import com.myapp.manju.arstrike.MyGLSurfaceView;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Matrix;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;

import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Renderer  {

    private GLSurfaceView mGLView;
    private GLSurfaceView glview;
    private World world;
    private FrameBuffer fb;
    private Light light;
    private Object3D cube;
public Renderer()
{


}

    public void Init()
    {
        world = new World();

        Texture t1= new Texture(64,64, RGBColor.BLUE);
        TextureManager.getInstance().addTexture("t1",t1);

        Texture t2= new Texture(64,64, RGBColor.RED);
        TextureManager.getInstance().addTexture("t2",t2);

        Object3D sphere= Primitives.getSphere(0.9f);
        sphere.translate(0,0.45f,0);
        sphere.setTexture("t2");

        cube = Primitives.getCube(1);
        cube.translate(5,0,10);
        cube.setTexture("t1");
        world.addObject(sphere);
        cube.addChild(sphere);
        world.addObject(cube);

        light = new Light(world);
        light.setIntensity(128,128,128);
        InputStream is;
        Object3D test;
        try{

            //is=getAssets().open("altair.3ds");
            //int size=is.available();
            //byte[] buffer= new byte[size];

            //test=loadModel(is,0.1f);

            //test.translate(2,2,2);
            //test.setTexture("t1");
            //world.addObject(test);

            //is.read(buffer);
            //is.close();
            // text=new String(buffer);
        }
        catch (Exception ex)
        {

        }

    }

    private Object3D loadModel(InputStream file, float scale) {
        Object3D[] model = Loader.load3DS(file,scale);
        Object3D o3d = new Object3D(0);
        Object3D temp = null;
        for (int i = 0; i < model.length; i++) {
            temp = model[i];
            temp.setCenter(SimpleVector.ORIGIN);
            temp.rotateX((float)( -.5*Math.PI));
            temp.rotateMesh();
            temp.setRotationMatrix(new Matrix());
            o3d = Object3D.mergeObjects(o3d, temp);
            o3d.build();
        }
        return o3d;
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        fb= new FrameBuffer(width, height);
    }

    public void onDrawFrame(GL10 gl) {
        fb.clear();
        cube.rotateY(0.1f);
        world.renderScene(fb);
        world.draw(fb);
        //world.drawWireframe(fb,RGBColor.WHITE,1,false);

        fb.display();
    }

}

