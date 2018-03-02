package com.aries.ffmpeg.frame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aries.ffmpeg.R;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSE_FILE = 10;
    private String videoUrl;
    private TextView textViewfile;
    private Button buttonSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        buttonSelect = findViewById(R.id.frame_select);
        textViewfile = findViewById(R.id.frame_file_name);
        buttonSelect.setOnClickListener(this);
    }

    /**
     * 选择文件
     */
    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, CHOOSE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_FILE:
                if (resultCode == RESULT_OK) {
                    videoUrl = UriUtils.getPath(FrameActivity.this, data.getData());
                    textViewfile.setText(videoUrl);
                    new VideoFrameCounter().clipVideo(videoUrl, 0);
                    Log.d("EditActivity", "CHOOSE_FILE=" + videoUrl);
                    break;
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frame_select:
                chooseFile();
                break;
            default:
                break;
        }
    }
}
