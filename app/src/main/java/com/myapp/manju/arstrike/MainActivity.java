package com.myapp.manju.arstrike;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.net.Uri;
import android.opengl.GLSurfaceView;
>>>>>>> 997a79aa48541a381f83a663861685acb77770a3
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
<<<<<<< HEAD
import android.view.View;
=======
import android.view.WindowManager;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
>>>>>>> 997a79aa48541a381f83a663861685acb77770a3

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static com.myapp.manju.arstrike.R.id.my_stage;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    OpenGLES20Activity rendererObject;
    private GLSurfaceView mGLView;
    private Stage stage;

    private static String TAG = "MainActivity";
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

    static {


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);


        setContentView(R.layout.activity_main);

        javaCameraView=(JavaCameraView)findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);




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
            Log.i(TAG, "OpenCV loaded");
            nLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.i(TAG, "OpenCV not loaded");
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
        return imageCanny;
    }

<<<<<<< HEAD

    public void onRenderClick(View v)
    {
        if(v.getId()==R.id.render_button)
        {
            //Intent i = new Intent(MainActivity.this, Renderer.class);
            Intent i = new Intent(MainActivity.this, EngineActivity.class);
            startActivity(i);
        }
    }

    public void onEngineClick(View v)
    {
        if(v.getId()==R.id.engine_button)
        {
            Intent i = new Intent(MainActivity.this, EngineActivity.class);
            startActivity(i);
        }
    }

=======
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
>>>>>>> 997a79aa48541a381f83a663861685acb77770a3
}
