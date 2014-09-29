package com.vishal.project7up7down;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends ActionBarActivity {

	DataHandler1 handler;
	
	public static final String MyPREFERENCES = "MyPrefs" ;
	
	SharedPreferences sharedpreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        
        final Intent intent = new Intent(this, GameStartActivity.class);
        
		
		final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		final EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
		final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		final EditText rePasswordEditText = (EditText) findViewById(R.id.rePasswordEditText);
		
		Button signUpButton = (Button) findViewById(R.id.signUpButton);
		
		
		signUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String getUsername  = usernameEditText.getText().toString();
				String getEmail  = emailEditText.getText().toString();
				String getPassword  = passwordEditText.getText().toString();
				String getrePassword  = rePasswordEditText.getText().toString();
				
				handler = new DataHandler1(getBaseContext());
				handler.open();
				long id = handler.insertData(getUsername, getEmail, getPassword, getrePassword);								
				Toast.makeText(getBaseContext(), "DATA Inserted Successfully", Toast.LENGTH_LONG).show();
				handler.close();
				
				Editor editor = sharedpreferences.edit();
				
				editor.putString("username", getUsername);
				editor.putString("score", "5");
				editor.commit();
				
				Bundle extras = new Bundle();
				extras.putString("USERNAME", getUsername);
				extras.putString("SCORE", "5");
				
				intent.putExtras(extras);
				
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
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
