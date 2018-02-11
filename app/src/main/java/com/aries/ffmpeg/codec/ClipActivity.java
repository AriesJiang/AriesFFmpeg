package com.aries.ffmpeg.codec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aries.ffmpeg.FFmpegApplication;
import com.aries.ffmpeg.R;

public class ClipActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private EditText mCutDuration;
    private EditText mCutPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip);
        mButton = (Button) findViewById(R.id.button);
        mCutDuration = (EditText) findViewById(R.id.et_cutduration);
        mCutPoint = (EditText)findViewById(R.id.et_cutpoint);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new VideoClip().clipVideo(
                FFmpegApplication.getAppContext().getVideoPath(),
                Integer.parseInt(mCutPoint.getText().toString())*1000*1000,
                Integer.parseInt(mCutDuration.getText().toString())*1000*1000);
    }
}
