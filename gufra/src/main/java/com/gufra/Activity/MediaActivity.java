package com.gufra.Activity;

import android.app.Activity;
import android.media.AudioFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gufra.View.AudioView;
import com.gufra.gufra.R;
import com.zlw.main.recorderlib.RecordManager;
import com.zlw.main.recorderlib.recorder.RecordConfig;
import com.zlw.main.recorderlib.recorder.RecordHelper;
import com.zlw.main.recorderlib.recorder.listener.RecordDataListener;
import com.zlw.main.recorderlib.recorder.listener.RecordFftDataListener;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;
import com.zlw.main.recorderlib.recorder.listener.RecordSoundSizeListener;
import com.zlw.main.recorderlib.recorder.listener.RecordStateListener;

import java.io.File;

/**
 * An example for media (recording &&　video)
 *
 */
public class MediaActivity extends Activity {
    private String TAG = "MediaActivity";
    private static final String[] STYLE_DATA = new String[]{"STYLE_ALL", "STYLE_NOTHING", "STYLE_WAVE", "STYLE_HOLLOW_LUMP"};
    Button mStartBtn;
    Button mStopBtn;
    TextView mSoundSizeTxt;
    AudioView mWaveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        //初始化
        RecordManager.getInstance().init(getApplication(),true);
//        修改录音格式(默认:WAV)
        RecordManager.getInstance().changeFormat(RecordConfig.RecordFormat.WAV);
        //修改录音配置
        RecordManager.getInstance().changeRecordConfig(RecordManager.getInstance().getRecordConfig().setSampleRate(16000));
        RecordManager.getInstance().changeRecordConfig(RecordManager.getInstance().getRecordConfig().setEncodingConfig(AudioFormat.ENCODING_PCM_8BIT));

        //修改录音文件存放位置（默认sdcard/Record）
//        RecordManager.getInstance().changeRecordDir(recordDir);
        //录音结果监听
        RecordManager.getInstance().setRecordResultListener(new RecordResultListener() {
            @Override
            public void onResult(File result) {
                Log.d(TAG,"result："+result);
            }
        });
        //录音状态监听
        RecordManager.getInstance().setRecordStateListener(new RecordStateListener() {
            @Override
            public void onStateChange(RecordHelper.RecordState state) {
                Log.d(TAG,"状态："+state);
            }

            @Override
            public void onError(String error) {
                Log.e(TAG,"error:"+error);
            }
        });
        //声音大小监听
        RecordManager.getInstance().setRecordSoundSizeListener(new RecordSoundSizeListener() {
            @Override
            public void onSoundSize(int soundSize) {
                Log.d(TAG,"录音大小："+soundSize);
                mSoundSizeTxt.setText("录音大小:"+soundSize);
            }
        });
        //音频数据监听
        RecordManager.getInstance().setRecordDataListener(new RecordDataListener() {
            @Override
            public void onData(byte[] data) {
                Log.d(TAG,"音频数据"+data.toString());

            }
        });
        //音频可视化数据监听
        RecordManager.getInstance().setRecordFftDataListener(new RecordFftDataListener() {
            @Override
            public void onFftData(byte[] data) {
                Log.d(TAG,"可视化数据："+data);
                mWaveView.setWaveData(data);
            }
        });
        initView();
        initAudioView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        RecordManager.getInstance().pause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        RecordManager.getInstance().stop();
    }
    private void initView() {
        mStartBtn = (Button)findViewById(R.id.btn_startRecord);
        mStopBtn = (Button)findViewById(R.id.btn_stopRecord);
        mSoundSizeTxt = (TextView)findViewById(R.id.txt_sound_size);
        mWaveView = (AudioView)findViewById(R.id.view_wave);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MediaActivity.this, "开始录音", Toast.LENGTH_LONG).show();
                RecordManager.getInstance().start();
            }
        });
        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MediaActivity.this, "停止录音", Toast.LENGTH_LONG).show();
                RecordManager.getInstance().stop();
            }
        });
    }

    private void initAudioView() {
        mWaveView.setStyle(AudioView.ShowStyle.getStyle(STYLE_DATA[2]), mWaveView.getUpStyle());
    }
}
