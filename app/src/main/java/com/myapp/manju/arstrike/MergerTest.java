package com.myapp.manju.arstrike;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.InputStream;

public class MergerTest extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    OpenGLES20Activity rendererObject;
    private MyGLView mGLView;
    private Stage stage;


    //private GLSurfaceView glview;
    private MyGLRenderer mygl;
    private CameraPreview cameraview;

    JavaCameraView javaCameraView;
    Mat mRGBA, imageGray, imageCanny;
    BaseLoaderCallback nLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case BaseLoaderCallback.SUCCESS: {
                    javaCameraView.enableView();
                    break;
                }
                default: {
                    super.onManagerConnected(status);
                    break;
                }
            }

            super.onManagerConnected(status);
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private void WorkingOnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);


        setContentView(R.layout.mergertest);

        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);

        //2
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mergertest);

        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);

        //3
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mergertest);

        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);

        mGLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        addContentView(mGLView, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

        //4
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        //mGLView.setZOrderOnTop(true);
        mGLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mGLView.getHolder().setFormat(PixelFormat.RGBA_8888);
        mGLView.setRenderer(new ClearRenderer());

        setContentView(mGLView);

        setContentView(R.layout.mergertest);


        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);

        //addContentView( mGLView, new ActionBar.LayoutParams( ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT ) );

        //5
        super.onCreate(savedInstanceState);

        // When working with the camera, it's useful to stick to one orientation.
        // setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );

        // Next, we disable the application's title bar...
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ...and the notification bar. That way, we can use the full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        mGLView.setZOrderOnTop(true);
        mGLView.getHolder().setFormat(PixelFormat.RGBA_8888);

        mGLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        //setContentView(mGLView);
        setContentView(R.layout.mergertest);

        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
        addContentView(mGLView, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // When working with the camera, it's useful to stick to one orientation.
        // setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );

        // Next, we disable the application's title bar...
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ...and the notification bar. That way, we can use the full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        mGLView.setZOrderOnTop(true);
        mGLView.getHolder().setFormat(PixelFormat.RGBA_8888);

        mGLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        //setContentView(mGLView);
        CreateScene();

        setContentView(R.layout.mergertest);

        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);


        addContentView(mGLView, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        // ByTutorial();

        // When working with the camera, it's useful to stick to one orientation.
        //setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );

        // Next, we disable the application's title bar...
        // requestWindowFeature( Window.FEATURE_NO_TITLE );
        // ...and the notification bar. That way, we can use the full screen.
        // getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //       WindowManager.LayoutParams.FLAG_FULLSCREEN );

        // Now let's create an OpenGL surface.
        //mGLView = new GLSurfaceView( this );
        // To see the camera preview, the OpenGL surface has to be created translucently.
        // See link above.
        //mGLView.setEGLConfigChooser( 8, 8, 8, 8, 16, 0 );
        //mGLView.getHolder().setFormat( PixelFormat.TRANSLUCENT );
        // The renderer will be implemented in a separate class, GLView, which I'll show next.
        //mGLView.setRenderer( new ClearRenderer() );
        // Now set this as the main view.
        //setContentView( mGLView );

        // Now also create a view which contains the camera preview...
        //cameraView = new CameraView( this );
        //javaCameraView=(JavaCameraView)findViewById(R.id.java_camera_view);
        //javaCameraView.setVisibility(SurfaceView.VISIBLE);
        //javaCameraView.setCvCameraViewListener(this);

        // ...and add it, wrapping the full screen size.
        //addContentView( javaCameraView, new ActionBar.LayoutParams( ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT ) );

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void CreateScene() {
        InputStream is;
        try {
            is = getAssets().open("altair.3ds");
            int size = is.available();
            byte[] buffer = new byte[size];

            float scale = 0.1f;
            SimpleVector position = new SimpleVector();

            position.x = 10;
            position.y = 10;
            position.z = 10;

            mGLView.AddModeltoScene(is, position, scale);

            is.read(buffer);
            is.close();


        } catch (Exception ex) {
            if (ex == null) {

            }
        }
    }

    private void ByTutorial() {

        // When working with the camera, it's useful to stick to one orientation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Next, we disable the application's title bar...
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ...and the notification bar. That way, we can use the full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Now let's create an OpenGL surface.
        //glview = new GLSurfaceView(this);
        // To see the camera preview, the OpenGL surface has to be created translucently.
        // See link above.
        //glview.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        //glview.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        // The renderer will be implemented in a separate class, GLView, which I'll show next.
        //glview.setRenderer( new ClearRenderer() );
        // Now set this as the main view.
        //setContentView( glview );

        //javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        //javaCameraView.setVisibility(SurfaceView.VISIBLE);
        //javaCameraView.setCvCameraViewListener(this);
        //addContentView(glview, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

    }

    private void SetUp() {
        //try{
        //  cameraview = new CameraPreview(this.getApplicationContext(),findViewById(R.id.surface_camera));
        //}
        //catch(Exception e){
        //  e.printStackTrace();
        //}
        // Translucent window 8888 pixel format and depth buffer
        //glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        // GLEngine is a class I design to interact with JPCT and with all the basic function needed,
        // create a world, render it, OnDrawFrame event etc.
        //glEngine = new GLEngine(getResources());
        //glSurfaceView.setRenderer(glEngine);

        //game = new Game(glEngine, (ImageView) findViewById(R.id.animation_screen), getResources(), this
        //      .getBaseContext());

        // Use a surface format with an Alpha channel:
        //glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        // Start game
        //game.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (javaCameraView != null) {
            javaCameraView.disableView();
        }

        //mGLView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (javaCameraView != null) {
            javaCameraView.disableView();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()) {
            //Log.i(TAG, "OpenCV loaded");
            nLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            // Log.i(TAG, "OpenCV not loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, this, nLoaderCallback);
        }

        //mGLView.onResume();

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRGBA = new Mat(height, width, CvType.CV_8UC4);
        imageGray = new Mat(height, width, CvType.CV_8UC1);
        imageCanny = new Mat(height, width, CvType.CV_8UC1);


    }

    @Override
    public void onCameraViewStopped() {
        mRGBA.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRGBA = inputFrame.rgba();
        Imgproc.cvtColor(mRGBA, imageGray, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(imageGray, imageCanny, 50, 150);
        return mRGBA;
    }




}
