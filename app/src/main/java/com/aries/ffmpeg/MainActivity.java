package com.aries.ffmpeg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onFFmpegClick(View view) {
        switch (view.getId()) {
            case R.id.ff_play:
                startActivity(new Intent(this, PlayActivity.class));
                break;
            case R.id.ff_cut:
                startActivity(new Intent(this, ClipActivity.class));
                break;
            default:
                break;
        }
    }
}
