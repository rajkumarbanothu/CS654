package com.mobile.tracker;

import org.xml.sax.DTDHandler;

import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	
	private DataManipulator dh;
	TelephonyManager telephonyMgr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dh=new DataManipulator(getBaseContext());
		String det=dh.getRegisteredData();
//		Toast.makeText(getBaseContext(), det, Toast.LENGTH_LONG).show();
		if(!det.equals("")) {
//		String[] detailstr=det.split("-");
//		String registerstatus=detailstr[4];
		setContentView(R.layout.activity_welcome);
//		finish();
		}
		else {
		setContentView(R.layout.activity_main);
		telephonyMgr=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

		Button button=(Button)findViewById(R.id.register);
//		Context context=getBaseContext();
//		Intent i=new Intent(context,UnInstallService.class);
//		context.startService(i);
		}
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		View num1=(TextView)findViewById(R.id.firstnumber);
		View num2=(TextView)findViewById(R.id.secondnumber);
		View num3=(TextView)findViewById(R.id.thirdnumber);
		Button button=(Button)findViewById(R.id.register);
		String number1=((TextView) num1).getText().toString();
		String number2=((TextView) num2).getText().toString();
		String number3=((TextView) num3).getText().toString();
		String form1="^([7-9][0-9]{9})$";
		
		if(v.getId()==R.id.register) {
			if(number1 == null || number1.equals("")) {
				Toast.makeText(getApplicationContext(), "Enter phonenumber1", Toast.LENGTH_LONG).show();
			}
			else if(number2 == null || number2.equals("")) {
				Toast.makeText(getApplicationContext(), "Enter phonenumber2", Toast.LENGTH_LONG).show();
			}
			else if(number3 == null || number3.equals("")) {
				Toast.makeText(getApplicationContext(), "Enter phonenumber3", Toast.LENGTH_LONG).show();
			}
			else if(!number1.matches(form1)) {
				Toast.makeText(getApplicationContext(), "valid mobile number1", Toast.LENGTH_LONG).show();
			}
			else if(!number2.matches(form1)) {
				Toast.makeText(getApplicationContext(), "valid mobile number2", Toast.LENGTH_LONG).show();
			}
			else if(!number3.matches(form1)) {
				Toast.makeText(getApplicationContext(), "valid mobile number3", Toast.LENGTH_LONG).show();
			}
			else if(number1.length()==10 && number2.length()==10 && number3.length()==10) {
//				String numone=prefix.concat(number1);
//				String numtwo=prefix.concat(number2);
//				String numthree=prefix.concat(number3);
//				Toast.makeText(getApplicationContext(), "nums"+number1+number2+number3, Toast.LENGTH_LONG).show();
				dh=new DataManipulator(this);
				String ssno=(String)telephonyMgr.getSimSerialNumber();
				String regstatus="yes";
				this.dh.insert(number1, number2, number3, ssno, regstatus);
//				String det=dh.getRegisteredData();
				Toast.makeText(getApplicationContext(), "Registered Successfully.", Toast.LENGTH_LONG).show();
				Intent i=new Intent(getApplicationContext(),welcome.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getApplicationContext().startActivity(i);
//				finish();
				
			}
		}
		
  }
	
}
