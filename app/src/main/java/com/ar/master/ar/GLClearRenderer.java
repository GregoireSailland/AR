package com.ar.master.ar;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Master on 18.11.2017.
 */
class GLClearRenderer implements GLSurfaceView.Renderer {
    public void onDrawFrame( GL10 gl ) {
        // This method is called per frame, as the name suggests.
        // For demonstration purposes, I simply clear the screen with a random translucent gray.
        /*float c = 1.0f / 256 * ( System.currentTimeMillis() % 256 );
        gl.glClearColor( c, c, c, 0.5f );
        gl.glClear( GL10.GL_COLOR_BUFFER_BIT );*/
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, javax.microedition.khronos.egl.EGLConfig eglConfig) {

    }

    public void onSurfaceChanged(GL10 gl, int width, int height ) {
        // This is called whenever the dimensions of the surface have changed.
        // We need to adapt this change for the GL viewport.
        gl.glViewport( 0, 0, width, height );
    }
}