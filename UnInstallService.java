package com.mobile.tracker;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.Toast;

public class UnInstallService extends IntentService {
	
	public static final String PACKAGE_INSTALLER = "com.android.packageinstaller";
	public static final String PACKAGE_INSTALLER_UNINSTALL_ACTIVITY = "com.android.packageinstaller.UninstallerActivity";
	ActivityManager mActivityManager;
	Timer alarmTimer  = new Timer();
	public UnInstallService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public UnInstallService() {
		super("mystring");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
		alarmTimer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	            mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	            ComponentName topActivity = mActivityManager.getRunningTasks(1).get(0).topActivity;

	            final String packageName = topActivity.getPackageName();
	            Toast.makeText(getApplicationContext(), "pack: "+packageName, Toast.LENGTH_LONG).show();
	            String className = topActivity.getClassName();
	            if (PACKAGE_INSTALLER.equals(packageName) && PACKAGE_INSTALLER_UNINSTALL_ACTIVITY.equals(className)) {

	                //Here I need to apply one condition where package name received to be matched with my package name. But I am not sure how to fetch package name of selected application for uninstalling 
	                //To Cancel Existing UninstallerActivity and redirect user to home.
	                Intent homeIntent = new Intent();
	                homeIntent.setAction(Intent.ACTION_MAIN);
	                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	                homeIntent.addCategory(Intent.CATEGORY_HOME);
	                startActivity(homeIntent);

	                //To open my activity
	                Intent loginActivity = new Intent(UnInstallService.this, Act_Login.class);
//	                startActivity(loginActivity); 
//	            	Toast.makeText(getBaseContext(), "Uninstall service received", Toast.LENGTH_SHORT).show();
//	            	Intent i=new Intent(getBaseContext(),myToastMessage.class);
//	            	loginActivity.putExtra(Constants.KEY_IS_FROM_SERVICE, true);
                    loginActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(loginActivity);
//	            	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	            	getBaseContext().startActivity(i);
	            	
	            	

	            }
	        }
	    }, 0, 100);

	}

}
