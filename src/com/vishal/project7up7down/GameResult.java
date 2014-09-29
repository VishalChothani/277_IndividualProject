package com.vishal.project7up7down;

import java.util.Random;

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
import android.widget.ImageView;
import android.widget.TextView;

public class GameResult extends ActionBarActivity {

	DataHandler1 handler;
	
	Intent playAgainIntent;
	
	public static final String MyPREFERENCES = "MyPrefs" ;	
	SharedPreferences sharedpreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_result);
		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		
		playAgainIntent = new Intent(this,GameDecision.class);
		
		
		
		TextView resultOutputTextView = (TextView) findViewById(R.id.resultOutputTextView);
		TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		
		Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
		Button exitButton = (Button) findViewById(R.id.exitButton);
		
		playAgainButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(playAgainIntent);
			}
		});
		
		exitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameResult.this.finish();
			}
		});
		
				
		
		String currentUser = sharedpreferences.getString("username", "");
		String currentScoreString = sharedpreferences.getString("score", "");
		
		int currentScore = Integer.parseInt(currentScoreString);
		
		System.out.println("CurrentUser: "+currentUser);
		
		System.out.println("CurrentScore: "+currentScore);
		
		int total = getRandomNumber();
		
		
		
//		handler = new DataHandler1(getBaseContext());
//		handler.open();
		
		String decision = sharedpreferences.getString("decision", "");
		
		if(decision.equals("up") && total > 7)
		{
			resultOutputTextView.setText("Won");
			currentScore++;			  
			// Database +1
		}
		else if(decision.equals("down") && total < 7)
		{
			resultOutputTextView.setText("Won");
			currentScore++;
			// Database +1
		}
		else if(decision.equals("seven") && total == 7)
		{
			resultOutputTextView.setText("Won");
			currentScore = currentScore + 5;
			// Database +5
		}
		else if(decision.equals("seven") && total != 7)
		{
			resultOutputTextView.setText("Lost");
			currentScore = currentScore - 2;
			// Database -2
		}
		else if((decision.equals("up") || decision.equals("down")) && total == 7)
		{
			resultOutputTextView.setText("Lost");
			currentScore = currentScore - 2;
			// Database -2
		}
		else
		{
			resultOutputTextView.setText("Lost");
			currentScore--;
			// Database -1
		}
//		
//		currentScoreString = currentScore+"";
//		handler.updateData(currentUser, currentScoreString);
//		handler.close();

//		Editor editor = sharedpreferences.edit();
//		
//		editor.putString("username", currentUser);
//		editor.putString("score", currentScoreString);
//		editor.commit();
				
	}

	private int getRandomNumber() {
		// TODO Auto-generated method stub
		ImageView dice_image1 = (ImageView) findViewById(R.id.firstDiceImageView);
		ImageView dice_image2 = (ImageView) findViewById(R.id.secondDiceImageView);
		
		Random random = new Random();
		int dice1 = random.nextInt(6) + 1;
		int dice2 = random.nextInt(6) + 1;
		int total = dice1 + dice2;
		
		if(dice1==1)
		{
			dice_image1.setImageResource(R.drawable.image1);
		}
		else if(dice1==2)
		{
			dice_image1.setImageResource(R.drawable.image2);
		}
		else if(dice1==3)
		{
			dice_image1.setImageResource(R.drawable.image3);
		}
		else if(dice1==4)
		{
			dice_image1.setImageResource(R.drawable.image4);
		}
		else if(dice1==5)
		{
			dice_image1.setImageResource(R.drawable.image5);
		}
		else 
		{
			dice_image1.setImageResource(R.drawable.image6);
		}
		
		if(dice2==1)
		{
			dice_image2.setImageResource(R.drawable.image1);
		}
		else if(dice2==2)
		{
			dice_image2.setImageResource(R.drawable.image2);
		}
		else if(dice2==3)
		{
			dice_image2.setImageResource(R.drawable.image3);
		}
		else if(dice2==4)
		{
			dice_image2.setImageResource(R.drawable.image4);
		}
		else if(dice2==5)
		{
			dice_image2.setImageResource(R.drawable.image5);
		}
		else 
		{
			dice_image1.setImageResource(R.drawable.image6);
		}
		
		System.out.println("Dice1: "+dice1);
		System.out.println("Dice2: "+dice2);
		
		return total;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_result, menu);
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
