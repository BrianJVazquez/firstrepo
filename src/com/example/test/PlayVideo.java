package com.example.test;

import android.app.Activity;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class PlayVideo extends Activity {

	VideoView videoPlayer;
	Uri planetFlyOverUri;
	MediaController videoTransport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
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
	
}
