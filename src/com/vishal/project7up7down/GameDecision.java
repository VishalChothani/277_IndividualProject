package com.vishal.project7up7down;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDecision extends ActionBarActivity {

	
	final String MyPREFERENCES = "MyPrefs";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_decision);
		
		final SharedPreferences sharedpreferences = getSharedPreferences(
				MyPREFERENCES, MODE_PRIVATE);
             
		final Editor editor = sharedpreferences.edit();
		
		final Intent gameIntent = new Intent(this,GameShake.class);
	        
	    String username = sharedpreferences.getString("username", "");
		String score = sharedpreferences.getString("score", "");
		
		
		
		System.out.println("1Username: "+username);
		System.out.println("1score: "+score);
	        
		TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
		
		nameTextView.setText(username);
		scoreTextView.setText(score);
	        
		Button upButton = (Button) findViewById(R.id.upButton);
		Button sevenButton = (Button) findViewById(R.id.sevenButton);
		Button downButton = (Button) findViewById(R.id.downButton);
		
		upButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putString("decision", "up");
				editor.commit();
				startActivity(gameIntent);
			}
		});
		
		sevenButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putString("decision", "seven");
				editor.commit();
				startActivity(gameIntent);
			}
		});

		downButton.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putString("decision", "down");
				editor.commit();
				startActivity(gameIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_decision, menu);
		return true;
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
		return super.onOptionsItemSelected(item);
	}
}
