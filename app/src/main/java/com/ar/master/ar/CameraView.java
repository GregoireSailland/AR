package com.ar.master.ar;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Master on 18.11.2017.
 */


class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private Camera camera;
    public CameraView( Context context ) {
        super( context );
        // We're implementing the Callback interface and want to get notified
        // about certain surface events.
        getHolder().addCallback( this );
        // We're changing the surface to a PUSH surface, meaning we're receiving
        // all buffer data from another component - the camera, in this case.
        getHolder().setType( SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS );
    }
    public void surfaceCreated( SurfaceHolder holder ) {
        camera = Camera.open();
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height ) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
        Camera.Size previewSize = previewSizes.get(previewSizes.size()-1);
        Camera.Parameters p = camera.getParameters();

        p.setPreviewSize( previewSize.width, previewSize.height );
        camera.setParameters( p );
        try{camera.setPreviewDisplay( holder );} catch(IOException e){e.printStackTrace();}
        camera.startPreview();
    }
    public void surfaceDestroyed( SurfaceHolder holder ) {
        // Once the surface gets destroyed, we stop the preview mode and release
        // the whole camera since we no longer need it.
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}