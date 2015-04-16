package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditGalaxy extends Activity{

	EditText colonyEdit, popEdit, fleetEdit, shipsEdit;
	Button colonyButton, popButton, fleetButton, shipsButton;
	Intent returnIntent = new Intent();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		instantiateUI();
		
		colonyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String colony = colonyEdit.getText().toString();
				if(colony != null){
					returnIntent.putExtra("colonies",colony);
					setResult(RESULT_OK,returnIntent);
					//finish();
				}
			}
		});
		popButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String pop = popEdit.getText().toString();
				if(pop != null){
					returnIntent.putExtra("population",pop);
					setResult(RESULT_OK,returnIntent);
					//finish();
				}

			}
		});
		fleetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String fleet = fleetEdit.getText().toString();
				if(fleet != null){
					returnIntent.putExtra("fleet",fleet);
					setResult(RESULT_OK,returnIntent);
					//finish();
				}
				
			}
		});
		shipsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ships = shipsEdit.getText().toString();
				if(ships != null){
					returnIntent.putExtra("ships",ships);
					setResult(RESULT_OK,returnIntent);
					//finish();
				}
			}
		});
	}
	
	private void instantiateUI(){
		//EditText widgets
		colonyEdit = (EditText)findViewById(R.id.edit_colony);
		popEdit = (EditText)findViewById(R.id.edit_pop);
		fleetEdit = (EditText)findViewById(R.id.edit_fleet);
		shipsEdit = (EditText)findViewById(R.id.edit_ships);
		
		//Button widgets
		colonyButton = (Button)findViewById(R.id.submit_colony);
		popButton = (Button)findViewById(R.id.submit_pop);
		fleetButton = (Button)findViewById(R.id.submit_fleet);
		shipsButton = (Button)findViewById(R.id.submit_ships);
	}
	
}
