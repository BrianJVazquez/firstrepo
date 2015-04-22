package com.example.test;

import com.example.test.Galaxy;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//This class is pushed through test repository 
//main activity for galaxy app

public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	static final int INFO_REQUEST = 0;
	static Galaxy milkyWay = new Galaxy("Milky Way", 511, 97);
	ImageView animImageView;
	Animation spaceShipAnim, lightShipAnim;
	ImageButton imageButtonOne, imageButtonTwo, imageButtonThree;
	SoundPool buttonSamples;
	AudioAttributes attribute;
	SparseIntArray buttonSampleArray;
	
	TextView nameData, solarData, habitData, colonyData, popData, fleetData, shipsData;
	
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		createDefaultGalaxy();
		createUiTextViews();
		transferDataValues();
		
		spaceShipAnim = AnimationUtils.loadAnimation(this, R.anim.anim_andromeda);
		lightShipAnim = AnimationUtils.loadAnimation(this, R.anim.anim_lightshipset);
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			//setting audio attributes ready for play
			attribute = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(
					AudioAttributes.CONTENT_TYPE_MUSIC).build();
			buttonSamples = new SoundPool.Builder().setAudioAttributes(attribute).setMaxStreams(3).build();
		}
		else{
			buttonSamples = new SoundPool(3, AudioManager.STREAM_MUSIC,100);
		}
		
		buttonSampleArray = new SparseIntArray(3);
		buttonSampleArray.append(1, buttonSamples.load(this, R.raw.buttonaudio, 1));
		buttonSampleArray.append(2, buttonSamples.load(this, R.raw.buttonaudio2, 1));
		buttonSampleArray.append(3, buttonSamples.load(this, R.raw.buttonaudio3, 1));
	}
	
	public void triggerSample(int sound, float pitch){
		AudioManager audioControl = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		float currentSoundVolume = audioControl.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maximumSoundVolume = audioControl.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volumeSet = currentSoundVolume / maximumSoundVolume;
		buttonSamples.play(buttonSampleArray.get(sound), volumeSet, volumeSet, 0, 0, pitch);
	}

	private void createDefaultGalaxy(){
		
		milkyWay.setGalaxyColonies(37579321);
		milkyWay.setGalaxyPopulation(1967387132);
		milkyWay.setGalaxyFleets(237);
		milkyWay.setGalaxyStarships(34769);
	}
	
	private void createUiTextViews() {
		// TODO Auto-generated method stub
		nameData = (TextView)findViewById(R.id.name);
		solarData = (TextView)findViewById(R.id.solar);
		habitData = (TextView)findViewById(R.id.habit);
		colonyData = (TextView)findViewById(R.id.colony);
		popData = (TextView)findViewById(R.id.pop);
		fleetData = (TextView)findViewById(R.id.fleet);
		shipsData = (TextView)findViewById(R.id.ships);
		
		//ImageView object
		animImageView =(ImageView)findViewById(R.id.animImageView);
		
		//ImageButtons
		imageButtonOne = (ImageButton)findViewById(R.id.galaxyOne);
		imageButtonTwo = (ImageButton)findViewById(R.id.galaxyTwo);
		imageButtonThree = (ImageButton)findViewById(R.id.galaxyThree);
		imageButtonOne.setOnClickListener(this);
		imageButtonTwo.setOnClickListener(this);
		imageButtonThree.setOnClickListener(this);
	}
	
	private void transferDataValues() {
		// TODO Auto-generated method stub
		nameData.setText(milkyWay.galaxyName);
		solarData.setText(String.valueOf(milkyWay.getGalaxySolarSystems()));
		colonyData.setText(String.valueOf(milkyWay.getGalaxyColonies()));
		popData.setText(String.valueOf(milkyWay.getGalaxyPopulation()));
		habitData.setText(String.valueOf(milkyWay.getGalaxyPlanets()));
		fleetData.setText(String.valueOf(milkyWay.getGalaxyFleets()));
		shipsData.setText(String.valueOf(milkyWay.getGalaxyStarships()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data){
		 super.onActivityResult(requestCode, resultCode, data);
		 if (requestCode == INFO_REQUEST) {
			 if(resultCode == RESULT_OK){
				 
				 String colony = data.getStringExtra("colonies");
				 String pop = data.getStringExtra("population");
				 String fleet = data.getStringExtra("fleet");
				 String ships = data.getStringExtra("ships");
				
				 if(colony != null){
					 milkyWay.setGalaxyColonies(Long.parseLong(colony));
					 colonyData.setText(colony);
				 }
				 if(ships != null){
					 milkyWay.setGalaxyStarships(Integer.parseInt(ships));
					 shipsData.setText(ships);
				 }
				 if(pop != null){
					 milkyWay.setGalaxyPopulation(Double.parseDouble(pop));
					 popData.setText(pop);
				 }
				 if(fleet != null){
					 milkyWay.setGalaxyFleets(Integer.parseInt(fleet));
					 fleetData.setText(fleet);
				 }
			 }
         }
	 }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		else if(id == R.id.edit_galaxy){
			Intent editIntent = new Intent(this,EditGalaxy.class);
			this.startActivityForResult(editIntent, INFO_REQUEST);
		}
		else if(id == R.id.play_video){
			Intent editIntent = new Intent(this, PlayVideo.class);
			this.startActivity(editIntent);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if(id == R.id.galaxyOne){
			animImageView.setBackgroundResource(R.drawable.imageviewwhitering);
			animImageView.setImageResource(R.drawable.anim_milkyway);
			animImageView.setAnimation(null);
			triggerSample(1,1.0f);
		}
		else if(id == R.id.galaxyTwo){
			animImageView.setImageResource(R.drawable.friendship);
			animImageView.setBackground(null);
			animImageView.startAnimation(spaceShipAnim);
			triggerSample(2,1.0f);
		}
		else if(id == R.id.galaxyThree){
			animImageView.setImageResource(R.drawable.anim_lightship);
			animImageView.setBackground(null);
			animImageView.startAnimation(lightShipAnim);
			triggerSample(3,1.0f);
		}
	}
	
}
