package com.mobile.tracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallStateReceiver extends BroadcastReceiver {

	public static final String pref_file="myfile";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
//		SharedPreferences settings=context.getSharedPreferences(pref_file, 0);
//		Boolean status=settings.getBoolean("isStolen", false);
		MyVariableClass clas=new MyVariableClass();
		boolean isStolen=clas.getVariable();
		DataManipulator dm=new DataManipulator(context);
		if(true) {
		Toast.makeText(context, "phone stolen", Toast.LENGTH_LONG).show();
		String details=dm.getRegisteredData();
		String[] str=details.split("-");
		String reg_fn=str[0];
		String reg_sn=str[1];
		String reg_tn=str[2];
		String reg_ssno=str[3];
		String reg_fnum="+91"+reg_fn;
		String reg_snum="+91"+reg_sn;
		String reg_tnum="+91"+reg_tn;
		String state;
		TelephonyManager telephonyMgr=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String cur_ssno=telephonyMgr.getSimSerialNumber();
		if(reg_ssno.equals(cur_ssno) && isStolen) {
			state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
			if(state==null) {
				//outgoing call
				String out_num=intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
				String strng="outgoing num"+out_num;
				Toast.makeText(context, strng, Toast.LENGTH_LONG).show();
				SmsManager smsMgr=SmsManager.getDefault();
				Calendar c=Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date time=c.getTime();
				String cur_time=sdf.format(time);
				String detail="OUT GOING NUMBER : "+out_num+" TIME : "+cur_time;
				Toast.makeText(context, detail, Toast.LENGTH_LONG).show();
				smsMgr.sendTextMessage(reg_fnum, null, detail, null, null);
				smsMgr.sendTextMessage(reg_snum, null, detail, null, null);
				smsMgr.sendTextMessage(reg_tnum, null, detail, null, null);
			}
//			else {
//				//same simcard present
//			}
			else if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
				String in_num=intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
				String strn="incoming num"+in_num;
				Toast.makeText(context, strn, Toast.LENGTH_LONG).show();
				SmsManager smsMgr=SmsManager.getDefault();
				Calendar c=Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date time=c.getTime();
				String cur_time=sdf.format(time);
				String detail="IN COMING NUMBER : "+in_num+"TIME : "+cur_time;
				smsMgr.sendTextMessage(reg_fnum, null, detail, null, null);
				smsMgr.sendTextMessage(reg_snum, null, detail, null, null);
				smsMgr.sendTextMessage(reg_tnum, null, detail, null, null);
			}
			else if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
				//in call activity
			}
		}
		}

	}

}
