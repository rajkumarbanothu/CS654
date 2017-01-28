package com.mobile.tracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AutoMessageSender extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Toast.makeText(getBaseContext(), "automsg class", Toast.LENGTH_LONG).show();
		SmsManager smsMgr=SmsManager.getDefault();
		TelephonyManager telephonyMgr=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String ssno=telephonyMgr.getSimSerialNumber();
		String imei=telephonyMgr.getDeviceId();
		String netop=telephonyMgr.getNetworkOperatorName();
		GsmCellLocation location=(GsmCellLocation)telephonyMgr.getCellLocation();
		int cid=location.getCid();
		int lac=location.getLac();
		String cellid=Integer.toString(cid);
		String locareacode=Integer.toString(lac);
		String num=telephonyMgr.getLine1Number();
		String msg="PHONE NO : "+num+"  IMEI NO : "+imei+"  SIM SERIAL NO : "+ssno+"  NETWORK PROVIDER : "+netop+
				"  LOCATION AREA CODE : "+locareacode+"  CELL ID : "+cellid;
		DataManipulator dm=new DataManipulator(getBaseContext());
		String str=dm.getRegisteredData();
		String[] nums=str.split("-");
		String reg_fn=nums[0];
		String reg_sn=nums[1];
		String reg_tn=nums[2];
		String reg_fnum="+91"+reg_fn;
		String reg_snum="+91"+reg_sn;
		String reg_tnum="+91"+reg_tn;
		smsMgr.sendTextMessage(reg_fnum, null, msg, null, null);
		smsMgr.sendTextMessage(reg_snum, null, msg, null, null);
		smsMgr.sendTextMessage(reg_tnum, null, msg, null, null);
		Toast.makeText(getBaseContext(), "message of details sent to reg nums", Toast.LENGTH_LONG).show();
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
