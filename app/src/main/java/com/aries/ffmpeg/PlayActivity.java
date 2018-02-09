package com.aries.ffmpeg;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class PlayActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        surfaceView = findViewById(R.id.ff_surface_view);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(PlayActivity.this);
        Button start = findViewById(R.id.ff_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("videoplayer", "----onClick----");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("videoplayer", Environment.getExternalStorageDirectory().getAbsolutePath() );
                        VideoPlayer.play(surfaceHolder.getSurface(), Environment.getExternalStorageDirectory() + File.separator + "DCIM/Camera/VID_20180203_120655.mp4");
                    }
                }).start();
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.v("videoplayer", "----surfaceCreated----");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
