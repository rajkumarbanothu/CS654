package com.mobile.tracker;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class SimStateListener extends BroadcastReceiver {
	
	static int mSimState = -1;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "Boot completed.",Toast.LENGTH_LONG).show();
		TelephonyManager telephonyMgr=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		DataManipulator dm=new DataManipulator(context);
		int simState=telephonyMgr.getSimState();
		String sim=Integer.toString(simState);
		if(simState!=mSimState) {
			mSimState=simState;
			switch(simState) {
				case TelephonyManager.SIM_STATE_ABSENT:
//					Log.i("SimStateListener", "sim state absent");
//					System.out.println("No sim card available");
//					Toast.makeText(context, "Sim State Absent",Toast.LENGTH_LONG).show();
					break;
				case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
//					Log.i("SimStateListener", "sim state network locked");
//					System.out.println("sim state network locked "+simState);
//					Toast.makeText(context, "sim state network locked",Toast.LENGTH_LONG).show();
					break;
				case TelephonyManager.SIM_STATE_PIN_REQUIRED:
//					Log.i("SimStateListener", "sim state pin required");
//					System.out.println("sim stete pin required"+simState);
//					Toast.makeText(context, "sim state pin required",Toast.LENGTH_LONG).show();
					break;
				case TelephonyManager.SIM_STATE_PUK_REQUIRED:
//					Log.i("SimStateListener", "sim state puk required");
//					System.out.println("sim state puk required"+simState);
//					Toast.makeText(context, "sim state puk required",Toast.LENGTH_LONG).show();
					break;
				case TelephonyManager.SIM_STATE_UNKNOWN:
//					Log.i("SimStateListener", "sim state unknown");
//					System.out.println("sim state unknown"+simState);
//					Toast.makeText(context, "sim state unknown",Toast.LENGTH_LONG).show();
					break;
				case TelephonyManager.SIM_STATE_READY:
//					Log.i("SimStateListener", "sim state ready");
//					System.out.println("sim state ready"+simState);
					Toast.makeText(context, "sim state ready",Toast.LENGTH_LONG).show();
					try {
						String cur_ssno=telephonyMgr.getSimSerialNumber();
						String reg_details=dm.getRegisteredData();
						String[] str=reg_details.split("-");
						String reg_ssno=str[3];
//						Log.i("SubscriberId", "SubscriberId"+cur_ssno);
						if(cur_ssno.equals(reg_ssno)) {
//							Log.i("SimStateListener","Simcard is same");
//							Toast.makeText(context, "same sim present",Toast.LENGTH_LONG).show();
							
						}
						else {
							Log.i("SimStateListener", "Simcard is changed, file a complent");
							Toast.makeText(context, "sim card changed call police",Toast.LENGTH_LONG).show();
							Intent myintent=new Intent(context,AutoMessageSender.class);
							myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(myintent);
							Toast.makeText(context, "message sending activity starting", Toast.LENGTH_LONG).show();
							if(mobileDataEnabled(context)) {
								Log.i("internet", "Phone is connected");
//								Intent simChangedService=new Intent(context,SimChangedService.class);
//								simChangedService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//								context.startService(simChangedService);
								Toast.makeText(context, "Connected to internet",Toast.LENGTH_LONG).show();
								Intent mylocservice=new Intent(context,MyLocationService.class);
								mylocservice.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								context.startActivity(mylocservice);
								
							}
							else {
								Log.i("internet", "Phone is NOT connected");
								Toast.makeText(context, "No internet connection",Toast.LENGTH_LONG).show();
								Toast.makeText(context, "enabling data connection",Toast.LENGTH_LONG).show();
								setMobileDataEnabled(context,true);
								if(mobileDataEnabled(context)) {
									Toast.makeText(context, "enabled connection",Toast.LENGTH_LONG).show();
									Toast.makeText(context, "calling mylocationservice",Toast.LENGTH_LONG).show();
									Log.i("internet", "Mobile Data Enabled");
									Intent mylocservice=new Intent(context,MyLocationService.class);
									mylocservice.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									context.startService(mylocservice);
								}
							}
						}
						
					}
					catch(Exception e) {
						Log.i("SubscriberId", "Subscriber id is null");
						Log.i("SubscriberId",e.getMessage());
					}
					break;
			}
		}
		else {
			Toast.makeText(context, "mSimStae not equal to simState",Toast.LENGTH_LONG).show();
		}
	}
	
	private void setMobileDataEnabled(Context context, boolean enabled) {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Class conmanClass = null;
        try {
            conmanClass = Class.forName(conman.getClass().getName());
            final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            final Object iConnectivityManager = iConnectivityManagerField.get(conman);
            final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);
            setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
	
	
	private boolean mobileDataEnabled(Context context) {
        boolean mobileDataEnabled = false; // Assume disabled
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // Make the method callable
            // get the setting for "mobile data"
            mobileDataEnabled = (Boolean)method.invoke(cm);
        } catch (Exception e) {
            // Some problem accessible private API
            // TODO do whatever error handling you want here
        }
        return mobileDataEnabled;
    }
	

}
