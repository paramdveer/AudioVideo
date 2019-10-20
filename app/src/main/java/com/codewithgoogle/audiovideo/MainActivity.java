package com.codewithgoogle.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private Button btn, playbtn, pausebtn;
    private VideoView vw;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar, movebackforth;
    private AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.playPauseBtn);
        playbtn=(Button)findViewById(R.id.startmusic) ;
        pausebtn=(Button)findViewById(R.id.stopButton);
        btn.setOnClickListener(MainActivity.this);
        playbtn.setOnClickListener(MainActivity.this);
        pausebtn.setOnClickListener(MainActivity.this);
        vw=(VideoView)findViewById(R.id.videoView);
        mediaController=new MediaController(MainActivity.this);
        mediaPlayer=MediaPlayer.create(this, R.raw.tumsehi);
        seekbar=(SeekBar)findViewById(R.id.seekBarVolume);
        movebackforth=(SeekBar)findViewById(R.id.seekbarMove);

        audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);
        int maxvolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekbar.setMax(maxvolume);
        seekbar.setProgress(currentVolume);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    Toast.makeText(MainActivity.this, "User is interacting with the seek bar :"+progress, Toast.LENGTH_SHORT).show();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        movebackforth.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playPauseBtn:
                String s = btn.getText().toString();
                if (s.equals("Play")) {
                    Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demovideo);
                    vw.setVideoURI(video);
                    vw.setMediaController(mediaController);
                    mediaController.setAnchorView(vw);
                    vw.start();
                    btn.setText("Pause");
                } else {
                    vw.pause();
                    btn.setText("Play");
                }
                break;
            case R.id.startmusic:
                mediaPlayer.start();
                break;
            case R.id.stopButton:
                mediaPlayer.pause();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            Toast.makeText(this,"The progress is : "+progress, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
