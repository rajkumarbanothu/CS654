package com.mobile.tracker;

import android.app.Application;

public class MyVariableClass extends Application {
	private static boolean isStolen=false;
	public void setVariable(boolean val) {
		this.isStolen=val;
	}
	public boolean getVariable() {
		return isStolen;
	}

}
