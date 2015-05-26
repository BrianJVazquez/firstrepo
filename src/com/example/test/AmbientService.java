package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class AmbientService extends Service {

	MediaPlayer ambientAudioPlayer;
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	
	@Override
	public void onCreate(){
		ambientAudioPlayer = MediaPlayer.create(this, R.raw.ambient);
		ambientAudioPlayer.setLooping(true);
	}
	
	@Override
	public void onStart(Intent intent, int startid){
		ambientAudioPlayer.start();
	}
	
	@Override
	public void onDestroy(){
		ambientAudioPlayer.stop();
	}

}
