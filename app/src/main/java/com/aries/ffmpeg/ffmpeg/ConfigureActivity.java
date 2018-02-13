package com.aries.ffmpeg.ffmpeg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.aries.ffmpeg.R;


/**
 * 最简单的基于FFmpeg的Helloworld例子-安卓
 * Simplest FFmpeg Android Helloworld
 *
 * 雷霄骅 Lei Xiaohua
 * leixiaohua1020@126.com
 * 中国传媒大学/数字电视技术
 * Communication University of China / Digital TV Technology
 * http://blog.csdn.net/leixiaohua1020
 *
 *
 * 本程序是移植FFmpeg到安卓平台的最简单程序。它可以打印出FFmpeg类库的下列信息：
 *  Protocol:  FFmpeg类库支持的协议
 *  AVFormat:  FFmpeg类库支持的封装格式
 *  AVCodec:   FFmpeg类库支持的编解码器
 *  AVFilter:  FFmpeg类库支持的滤镜
 *  Configure: FFmpeg类库的配置信息
 *
 * This is the simplest program based on FFmpeg in Android. It can show following
 * informations about FFmpeg library:
 *  Protocol:  Protocols supported by FFmpeg.
 *  AVFormat:  Container format supported by FFmpeg.
 *  AVCodec:   Encoder/Decoder supported by FFmpeg.
 *  AVFilter:  Filters supported by FFmpeg.
 *  Configure: configure information of FFmpeg.
 *
 */

public class ConfigureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        final TextView libinfoText = (TextView) findViewById(R.id.text_libinfo);
        libinfoText.setMovementMethod(ScrollingMovementMethod.getInstance());

        libinfoText.setText(FFmpeg.configurationinfo());

        Button configurationButton = (Button) this.findViewById(R.id.button_configuration);
        Button urlprotocolButton = (Button) this.findViewById(R.id.button_urlprotocol);
        Button avformatButton = (Button) this.findViewById(R.id.button_avformat);
        Button avcodecButton = (Button) this.findViewById(R.id.button_avcodec);
        Button avfilterButton = (Button) this.findViewById(R.id.button_avfilter);

        urlprotocolButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0){
                libinfoText.setText(FFmpeg.urlprotocolinfo());
            }
        });

        avformatButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0){
                libinfoText.setText(FFmpeg.avformatinfo());
            }
        });

        avcodecButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0){
                libinfoText.setText(FFmpeg.avcodecinfo());
            }
        });

        avfilterButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0){
                libinfoText.setText(FFmpeg.avfilterinfo());
            }
        });

        configurationButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0){
                libinfoText.setText(FFmpeg.configurationinfo());
            }
        });
    }
}
