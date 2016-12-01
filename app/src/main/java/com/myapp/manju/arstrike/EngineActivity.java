package com.myapp.manju.arstrike;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class EngineActivity extends Activity implements GLSurfaceView.Renderer {

    private GLSurfaceView glview;
    private World world;
    private FrameBuffer fb;
    private Light light;
    private Object3D cube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_engine);
        glview= new GLSurfaceView(this);
        glview.setEGLContextClientVersion(2);
        glview.setRenderer(this);
        setContentView(glview);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        glview.onResume();
    }

    @Override
    protected void onPause()
    {
        glview.onPause();
        super.onPause();
    }

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

        Object3D sphere=Primitives.getSphere(0.9f);
        sphere.translate(0,0.45f,0);
        sphere.setTexture("t2");

        /*
        Object3D thing;
        thing = loadModel("resources/3dObj" + "IronMan" + ".obj","resources/3dObj" + "IronMan" + ".mtl", 1);
        thing.translate(0,0,0);
        thing.setTexture("t1");
        thing.build();
        world.addObject(thing); */

        Object3D thing=null;
        try {
            thing = loadModel("assets/" + "altair" + ".3ds", 1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        thing.translate(0,0,0);
        thing.build();
        thing.setTexture("t1");
        world.addObject(thing);

        /*
        try {
            cube=Object3D.mergeAll(Loader.load3DS(getResources().getAssets().open("altair.3ds", 1));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        cube = Primitives.getCube(1);
        cube.translate(5,0,10);
        cube.setTexture("t1");
        world.addObject(sphere);
        cube.addChild(sphere);
        world.addObject(cube);

        light = new Light(world);
        light.setIntensity(128,128,128);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        fb= new FrameBuffer(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        fb.clear();
        cube.rotateY(0.1f);
        world.renderScene(fb);
        world.draw(fb);
        //world.drawWireframe(fb,RGBColor.WHITE,1,false);

        fb.display();
    }


    private Object3D loadModel(String objName,String mtlName, float scale)  {

        InputStream obj = new ByteArrayInputStream( objName.getBytes() );
        InputStream mtl = new ByteArrayInputStream( mtlName.getBytes() );

        Object3D[] model = Loader.loadOBJ(obj,mtl,scale);
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

    private Object3D loadModel(String filename, float scale) throws UnsupportedEncodingException {
        InputStream stream=null;
        try {
             stream = this.getAssets().open("test.3ds");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //InputStream stream = new ByteArrayInputStream(filename.getBytes("UTF-8"));
        Object3D[] model = Loader.load3DS(stream, scale);
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

}
