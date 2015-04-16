package com.example.test;

import com.example.test.Galaxy;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//This class is pushed through test repository

public class MainActivity extends ActionBarActivity {
	
	static final int INFO_REQUEST = 0;
	static Galaxy milkyWay = new Galaxy("Milky Way", 511, 97);
	
	TextView nameData, solarData, habitData, colonyData, popData, fleetData, shipsData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		createDefaultGalaxy();
		createUiTextViews();
		transferDataValues();
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
		return super.onOptionsItemSelected(item);
	}
}
