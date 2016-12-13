package com.myapp.manju.arstrike;

import android.app.Activity;
import android.graphics.drawable.VectorDrawable;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.threed.jpct.Camera;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Vector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class EngineActivity extends Activity implements GLSurfaceView.Renderer {

    private World world;
    private FrameBuffer fb;
    private Light light;
    private Object3D cube;


    public boolean onCreateOptionMenu(Menu menu)
    {
        return true;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        world = new World();

        Texture t1= new Texture(64,64, RGBColor.BLUE);
        TextureManager.getInstance().addTexture("t1",t1);

        Texture t2= new Texture(64,64, RGBColor.RED);
        TextureManager.getInstance().addTexture("t2",t2);

        Object3D sphere=Primitives.getSphere(2.0f);
        sphere.translate(-5,10,15);
        sphere.setTexture("t2");

        cube = Primitives.getCube(1);
        cube.translate(5,0,10);
        cube.setTexture("t1");
        world.addObject(sphere);
        //cube.addChild(sphere);
        world.addObject(cube);
        world.addObject(sphere);

        light = new Light(world);
        light.setIntensity(128,128,128);

    }

    public void Add3dModels()
    {
        InputStream is;
        Object3D test;
        try{
            //is= getAssets().open("altair.3ds");
            is=getAssets().open("test.txt");
            int size=is.available();
            byte[] buffer= new byte[size];

            test=loadModel(is,1.0f);

            test.translate(5,0,10);
            test.setTexture("t1");
            world.addObject(test);

            is.read(buffer);
            is.close();
            // text=new String(buffer);
        }
        catch (Exception ex)
        {
            if(ex== null)
            {

            }
        }

    }

    public void AddModeltoScene(InputStream is, SimpleVector position,float scale)
    {
        Object3D newModel=loadModel(is,scale);
        newModel.translate(position.x,position.y,position.z);
        newModel.translate(5,0,10);
        newModel.setTexture("t1");
        world.addObject(newModel);
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

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        fb= new FrameBuffer(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        fb.clear();
        cube.rotateY(1.0f);
        cube.rotateX(1.0f);
        world.renderScene(fb);
        world.draw(fb);
        //world.drawWireframe(fb,RGBColor.WHITE,1,false);
        CameraMovements();

        fb.display();
    }

    public void CameraMovements() {
        Camera cam = world.getCamera();
        cam.moveCamera(Camera.CAMERA_MOVELEFT, 2);
    }

}
