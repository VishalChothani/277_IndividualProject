package com.vishal.project7up7down;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	protected static final String USERNAME = null;
	protected static final String SCORE = null;
	DataHandler1 handler;
	boolean flag = false;
	
	public static final String MyPREFERENCES = "MyPrefs" ;
	
	SharedPreferences sharedpreferences;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        
        final Intent intent = new Intent(this, GameStartActivity.class);
        final Intent intentSignUp = new Intent(this, SignUpActivity.class);
        
        TextView signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        Button signInButton = (Button) findViewById(R.id.signInButton);
        
        final EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);        
        
        signInButton.setOnClickListener(new View.OnClickListener() {
            
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String emailET = emailEditText.getText().toString();
				String passwordET = passwordEditText.getText().toString();
				
				String email = "";
				String password = "";
				String username="";
				String score = "";
				
				handler = new DataHandler1(getBaseContext());
				handler.open();
				Cursor cursor = handler.getData();
				if(cursor.moveToFirst())
				{
					do
					{
						email = cursor.getString(1);
						password = cursor.getString(2);		
						username = cursor.getString(0);
						score = cursor.getString(4);
						Toast.makeText(getBaseContext(), "Email: "+email+"Password: "+password, Toast.LENGTH_LONG).show();
						if(email.equals(emailET) && password.equals(passwordET))
						{							
							flag = true;							
							Bundle extras = new Bundle();
							extras.putString("USERNAME", username);
							extras.putString("SCORE", score);
							
							Editor editor = sharedpreferences.edit();
							
							editor.putString("username", username);
							editor.putString("score", score);
							editor.commit();
							
//							String username1 = sharedpreferences.getString("username", "");
//							String score1 = sharedpreferences.getString("score", "");
//							
//							System.out.println("11Username: "+username1);
//							System.out.println("11score: "+score1);
//							
							intent.putExtras(extras);
							
							startActivity(intent);
						}
					}while(cursor.moveToNext());
				}
				if(flag==false)
				{
					Toast.makeText(getBaseContext(), "Wrong credentials. Try Again", Toast.LENGTH_LONG).show();
				}
				handler.close();				
			}
		});
        
        signUpTextView.setOnClickListener(new View.OnClickListener() {
        
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(intentSignUp);				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
