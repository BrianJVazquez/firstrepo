package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class PlayVideo extends Activity implements OnClickListener{

	VideoView videoPlayer;
	Uri planetFlyOverUri;
	MediaController videoTransport;
	ImageView soundOn, soundOff;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		soundOn = (ImageView)findViewById(R.id.playAudio);
		soundOn.setOnClickListener(this);
		soundOff = (ImageView)findViewById(R.id.stopAudio);
		soundOff.setOnClickListener(this);
		
		videoPlayer = (VideoView)findViewById(R.id.videoPlayer);
		planetFlyOverUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.flyover);
		videoPlayer.setVideoURI(planetFlyOverUri);
		videoTransport = new MediaController(this);
		videoTransport.setAnchorView(videoPlayer);
		videoPlayer.setMediaController(videoTransport);
		videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.setLooping(true);
			}
		});
		videoPlayer.start();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if(id == R.id.playAudio){
			startService(new Intent(this,AmbientService.class));
		}
		else if(id == R.id.stopAudio){
			stopService(new Intent(this,AmbientService.class));
		}
	}
	
}
