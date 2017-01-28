package com.mobile.tracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Act_Login extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AlertDialog.Builder builder=new AlertDialog.Builder(Act_Login.this);
		builder.setTitle("Password Prompt");
		builder.setMessage("Enter Password to Uninstall");
		final EditText input=new EditText(getApplicationContext());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		input.setLayoutParams(lp);
//		input.setHint("Password");
		builder.setView(input);
		final String pass=input.getText().toString();
		final String mypass="mypassword";;
		builder.setPositiveButton("UNINSTALL", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(mypass.equals(pass)) {
					Toast.makeText(getBaseContext(), "Password matched", Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(getBaseContext(), "Password not matched", Toast.LENGTH_LONG).show();
					
				}
//				else if(pass.equals(mypass)) {
//					Toast.makeText(getBaseContext(), "Password not matched", Toast.LENGTH_LONG).show();
//				}
				
			}
		});
		builder.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				
			}
		});
		AlertDialog alertdialog=builder.create();
		alertdialog.show();
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
