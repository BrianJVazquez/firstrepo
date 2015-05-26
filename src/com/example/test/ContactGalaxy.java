package com.example.test;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ContactGalaxy extends ActionBarActivity implements OnClickListener{

	Button listButton, milkyWayButton, andromedaButton, spiralButton, homeButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		addButtonInstance();
		addButtonClickListener();
		
	}
	
	private void addButtonInstance(){
		listButton = (Button)findViewById(R.id.listRulers);
		milkyWayButton = (Button)findViewById(R.id.addMilkyWay);
		andromedaButton = (Button)findViewById(R.id.addAndromeda);
		spiralButton = (Button)findViewById(R.id.addSpiral);
		homeButton = (Button)findViewById(R.id.returnHome);
	}
	
	private void addButtonClickListener(){
		listButton.setOnClickListener(this);
		milkyWayButton.setOnClickListener(this);
		andromedaButton.setOnClickListener(this);
		spiralButton.setOnClickListener(this);
		homeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		int id = v.getId();
		if(id == R.id.listRulers){
			listGalaxyRulers();
		}
		else if(id == R.id.addMilkyWay){
			addGalaxyViceroy("Viceroy Pavel Chekov");
		}
		else if(id == R.id.addAndromeda){
			addGalaxyViceroy("Viceroy Hikary Sulu");
		}
		else if(id == R.id.addSpiral){
			addGalaxyViceroy("Viceroy Leonard McCoy");
		}
		else if(id == R.id.returnHome){
			finish();
		}
	}

	protected void addGalaxyViceroy(String electedViceroy) {
		
		//creates content values to be inserted in the DB table
		ContentValues viceroyContact = new ContentValues();
		
		viceroyContact.put(RawContacts.ACCOUNT_NAME, electedViceroy);
		viceroyContact.put(RawContacts.ACCOUNT_TYPE, electedViceroy);
		Uri newUri = getContentResolver().insert(RawContacts.CONTENT_URI, viceroyContact);
		long rawContactsId = ContentUris.parseId(newUri);
		viceroyContact.clear();
		viceroyContact.put(Data.RAW_CONTACT_ID, rawContactsId);
		viceroyContact.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		viceroyContact.put(StructuredName.DISPLAY_NAME, electedViceroy);
		getContentResolver().insert(Data.CONTENT_URI, viceroyContact);
		Toast.makeText(this, electedViceroy + " has been elected", Toast.LENGTH_LONG).show();
	}

	protected void listGalaxyRulers() {
		Cursor rulerCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		
		//rulerCursor position is at -1, must .moveToNext() first
		while(rulerCursor.moveToNext()){
			String rulerName = rulerCursor.getString(rulerCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
			Toast.makeText(this, rulerName, Toast.LENGTH_LONG).show();
		}
	}

}
