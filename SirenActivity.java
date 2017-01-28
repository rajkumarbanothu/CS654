package com.mobile.tracker;

import java.io.IOException;
import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SirenActivity extends Activity {

	int maxvol;
	int currentvol;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Toast.makeText(getBaseContext(), "oncreate", Toast.LENGTH_LONG).show();
		
		SettingContentObserver msco=new SettingContentObserver(getApplicationContext(),new Handler());
		this.getApplicationContext().getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, msco);
		msco.player();
		
//		AudioManager aMgr=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
//		MediaPlayer mPlayer=new MediaPlayer();
//		switch(aMgr.getRingerMode()) {
//		
//		case AudioManager.RINGER_MODE_SILENT :
//			Toast.makeText(getBaseContext(), "silent mode", Toast.LENGTH_LONG).show();
//			aMgr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//			currentvol=aMgr.getStreamVolume(AudioManager.STREAM_ALARM);
//			maxvol=aMgr.getStreamMaxVolume(AudioManager.STREAM_ALARM);
//			aMgr.setStreamVolume(AudioManager.STREAM_ALARM, maxvol, AudioManager.FLAG_SHOW_UI);
//			mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
//			Uri myuri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song2);
//			try {
//				mPlayer.setDataSource(getBaseContext(), myuri);
//				mPlayer.prepare();
//				mPlayer.start();
//			} catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				Log.v("Excep", "mediaplayer in sirenactivity");
//			}
//			break;
//		case AudioManager.RINGER_MODE_VIBRATE :
//			Toast.makeText(getBaseContext(), "vibration mode", Toast.LENGTH_LONG).show();
//			aMgr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//			currentvol=aMgr.getStreamVolume(AudioManager.STREAM_ALARM);
//			maxvol=aMgr.getStreamMaxVolume(AudioManager.STREAM_ALARM);
//			aMgr.setStreamVolume(AudioManager.STREAM_ALARM, maxvol, AudioManager.FLAG_SHOW_UI);
//			mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
//			myuri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song2);
//			try {
//				mPlayer.setDataSource(getBaseContext(), myuri);
//				mPlayer.prepare();
//				mPlayer.start();
//			} catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				Log.v("Excep", "mediaplayer in sirenactivity");
//			}
//			
//			break;
//		case AudioManager.RINGER_MODE_NORMAL :
//			Toast.makeText(getBaseContext(), "normal mode", Toast.LENGTH_LONG).show();
//			aMgr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//			currentvol=aMgr.getStreamVolume(AudioManager.STREAM_ALARM);
//			maxvol=aMgr.getStreamMaxVolume(AudioManager.STREAM_ALARM);
//			aMgr.setStreamVolume(AudioManager.STREAM_ALARM, maxvol, AudioManager.FLAG_SHOW_UI);
//			mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
//			myuri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song2);
//			try {
//				mPlayer.setDataSource(getBaseContext(), myuri);
//				mPlayer.prepare();
//				mPlayer.start();
//			} catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				Log.v("Excep", "mediaplayer in sirenactivity");
//			}
//			
//			break;
//		}
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
