package com.ar.master.ar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;



public class Launcher extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.ar.master.MESSAGE";
    private GLSurfaceView glView;
    private CameraView cameraView;

    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, RequestPermissionsActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "Request");
            startActivity(intent);
            // Should we show an explanation?
            /*if (ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block this thread waiting for the user's response! After the user sees the explanation, try again to request the permission.
            }else{
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }*/
        }
        glView = new GLSurfaceView( this );// Now let's create an OpenGL surface.
        glView.setEGLConfigChooser( 8, 8, 8, 8, 16, 0 );
        glView.getHolder().setFormat( PixelFormat.TRANSLUCENT );// To see the camera preview, the OpenGL surface has to be created translucently.
        glView.setRenderer( new GLClearRenderer() );// The renderer will be implemented in a separate class, GLView, which I'll show next.
        setContentView( glView );// Now set this as the main view.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraView = new CameraView(this);// Now also create a view which contains the camera preview...
            addContentView(cameraView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));// ...and add it, wrapping the full screen size.
        }


    }
}

