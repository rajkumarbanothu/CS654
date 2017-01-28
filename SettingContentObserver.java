package com.mobile.tracker;

import java.io.IOException;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SettingContentObserver extends ContentObserver {
	final Context context;
	int currentvol;
	int maxvol;
	AudioManager audio;
	public static MediaPlayer myPlay;
	public SettingContentObserver(Context c,Handler handler) {
		super(handler);
		context=c;
		audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		myPlay=new MediaPlayer();
	}
	public void player() {
		Uri myuri=Uri.parse("android.resource://com.mobile.tracker/"+R.raw.song2);
		if(audio.getRingerMode()==audio.RINGER_MODE_SILENT) {		
			Toast.makeText(context, "silent mode", Toast.LENGTH_LONG).show();
			audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			currentvol=audio.getStreamVolume(AudioManager.STREAM_ALARM);
			maxvol=audio.getStreamMaxVolume(AudioManager.STREAM_ALARM);
			audio.setStreamVolume(AudioManager.STREAM_ALARM, maxvol, AudioManager.FLAG_SHOW_UI);
			myPlay.setAudioStreamType(AudioManager.STREAM_ALARM);
//			Uri myuri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song2);
			try {
				myPlay.setDataSource(context, myuri);
				myPlay.prepare();
				myPlay.start();
			} catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("Excep", "mediaplayer in sirenactivity");
			}
		}
		else if(audio.getRingerMode()==audio.RINGER_MODE_VIBRATE) {		
			Toast.makeText(context, "vibrate mode", Toast.LENGTH_LONG).show();
			audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			currentvol=audio.getStreamVolume(AudioManager.STREAM_ALARM);
			maxvol=audio.getStreamMaxVolume(AudioManager.STREAM_ALARM);
			audio.setStreamVolume(AudioManager.STREAM_ALARM, maxvol, AudioManager.FLAG_SHOW_UI);
			myPlay.setAudioStreamType(AudioManager.STREAM_ALARM);
//			Uri myuri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song2);
			try {
				myPlay.setDataSource(context, myuri);
				myPlay.prepare();
				myPlay.start();
			} catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("Excep", "mediaplayer in sirenactivity");
			}
		}
		else if(audio.getRingerMode()==audio.RINGER_MODE_NORMAL) {		
			Toast.makeText(context, "normal mode", Toast.LENGTH_LONG).show();
			audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			currentvol=audio.getStreamVolume(AudioManager.STREAM_ALARM);
			maxvol=audio.getStreamMaxVolume(AudioManager.STREAM_ALARM);
			audio.setStreamVolume(AudioManager.STREAM_ALARM, maxvol, AudioManager.FLAG_SHOW_UI);
			myPlay.setAudioStreamType(AudioManager.STREAM_ALARM);
//			Uri myuri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song2);
			try {
				myPlay.setDataSource(context, myuri);
				myPlay.prepare();
				myPlay.start();
			} catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("Excep", "mediaplayer in sirenactivity");
			}
		}
		else {
			//default
		}
	}
	public void stopPlayer() {
		myPlay.stop();
		myPlay.release();
	}
	
	public void onChange(boolean selfChange) {	
		super.onChange(selfChange);
		Toast.makeText(context, "Change detected", Toast.LENGTH_LONG).show();
	     int currentVolume = audio.getStreamVolume(AudioManager.STREAM_ALARM);
	     audio.setStreamVolume(AudioManager.STREAM_ALARM, audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.FLAG_SHOW_UI);
	     Toast.makeText(context, "volume maximised", Toast.LENGTH_LONG).show();
		
	}
	

}
