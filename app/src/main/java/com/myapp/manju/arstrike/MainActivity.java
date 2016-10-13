package com.myapp.manju.arstrike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static String TAG="MainActivity";
    JavaCameraView javaCameraView;
    Mat mRGBA,imageGray,imageCanny;
    BaseLoaderCallback nLoaderCallback= new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status)
            {
                case BaseLoaderCallback.SUCCESS:{
                    javaCameraView.enableView();
                    break;
                }
                default:{
                    super.onManagerConnected(status);
                    break;
                }
            }

            super.onManagerConnected(status);
        }
    };

    static {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        javaCameraView=(JavaCameraView)findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected  void onPause()
    {super.onPause();
        if(javaCameraView!=null)
        {
            javaCameraView.disableView();
        }
    }

    @Override
    protected  void onDestroy()
    {
        super.onDestroy();
        if(javaCameraView!=null)
        {
            javaCameraView.disableView();
        }

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(OpenCVLoader.initDebug())
        {
            Log.i(TAG,"OpenCV loaded");
            nLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else
        {
            Log.i(TAG,"OpenCV not loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9,this,nLoaderCallback);
        }

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRGBA=new Mat(height,width, CvType.CV_8UC4);
        imageGray=new Mat(height,width, CvType.CV_8UC1);
        imageCanny=new Mat(height,width, CvType.CV_8UC1);


    }

    @Override
    public void onCameraViewStopped() {
        mRGBA.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRGBA=inputFrame.rgba();
        Imgproc.cvtColor(mRGBA,imageGray,Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(imageGray,imageCanny,50,150);
        return imageCanny;
    }
}
