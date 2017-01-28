package com.mobile.tracker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MySmsReceiver extends BroadcastReceiver {

//	public static final String pref_name="MyPrefFile";
//	protected static final int REQUEST_ENABLE = 0;
//	public static final String pref_file="myfile";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
//		MediaPlayer mPlay=observer.myPlay;
		DataManipulator dm=new DataManipulator(context);
		String reg_details=dm.getRegisteredData();
		String[] str=reg_details.split("-");
		String reg_fn=str[0];
		String reg_sn=str[1];
		String reg_tn=str[2];
		String reg_fnum="+91"+reg_fn;
		String reg_snum="+91"+reg_sn;
		String reg_tnum="+91"+reg_tn;
		String reg_ssno=str[3];
		Toast.makeText(context, "br recevr ", Toast.LENGTH_LONG).show();
		MediaPlayer myPlayer=new MediaPlayer();
		TelephonyManager telephonyMgr=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String cur_ssno=telephonyMgr.getSimSerialNumber(); 
		if(reg_ssno.equals(cur_ssno)) {
			Object[] pdus=(Object[])intent.getExtras().get("pdus");
			SmsMessage shortMsg=SmsMessage.createFromPdu((byte[])pdus[0]);
			String in_msg_num=shortMsg.getOriginatingAddress();
			String in_msg_body=shortMsg.getDisplayMessageBody();
			Toast.makeText(context, "msg frm : "+in_msg_num, Toast.LENGTH_LONG).show();
			MyVariableClass mc=(MyVariableClass)context.getApplicationContext();
			if((in_msg_num.equals(reg_fnum) || in_msg_num.equals(reg_snum) || in_msg_num.equals(reg_tnum)) && in_msg_body.equals("STOLEN")) {
//				SharedPreferences settings=context.getSharedPreferences(pref_file,0);
//				SharedPreferences.Editor editor=settings.edit();
//				editor.putBoolean("isStolen", true);
//				editor.commit();
				mc.setVariable(true);
				
			}
			boolean status=mc.getVariable();
			if((in_msg_num.equals(reg_fnum) || in_msg_num.equals(reg_snum) || in_msg_num.equals(reg_tnum)) && in_msg_body.equals("LOCK MOBILE")) {
				Intent myintent=new Intent(context,LockActivity.class);
				myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(myintent);
			}
			else if((in_msg_num.equals(reg_fnum) || in_msg_num.equals(reg_snum) || in_msg_num.equals(reg_tnum)) && in_msg_body.equals("SIREN ON")) {
				Toast.makeText(context, "starting sirenactivity", Toast.LENGTH_LONG).show();
				Intent myintenttwo=new Intent(context,SirenActivity.class);
				myintenttwo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(myintenttwo);
			}
			else if((in_msg_num.equals(reg_fnum) || in_msg_num.equals(reg_snum) || in_msg_num.equals(reg_tnum)) && in_msg_body.equals("SIREN OFF")) {
				
				if(SettingContentObserver.myPlay.isPlaying()) {
					Toast.makeText(context, "mplayer playing", Toast.LENGTH_LONG).show();
					SettingContentObserver.myPlay.stop();
				}
			}
			if(mc.getVariable()==true) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar c=Calendar.getInstance();
				Date datentimeobj=c.getTime();
				String datentime=sdf.format(datentimeobj);
				SmsManager smsMgr=SmsManager.getDefault();
				String msg="SMS FROM : "+in_msg_num+" SMS : "+in_msg_body+" TIME : "+datentime;
				smsMgr.sendTextMessage(reg_fnum, null, msg, null, null);
				smsMgr.sendTextMessage(reg_snum, null, msg, null, null);
				smsMgr.sendTextMessage(reg_tnum, null, msg, null, null);
			}
		}
		else {
			//other sim present
		}
	}

}
